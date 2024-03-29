package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.Ticker;
import be.ds.projects.botTrader.testbench.exception.TestBenchException;
import be.ds.projects.botTrader.testbench.model.Budget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static be.ds.projects.botTrader.testbench.CommandVerifier.*;
import static be.ds.projects.botTrader.util.LogUtil.getBuyLogMessage;
import static be.ds.projects.botTrader.util.LogUtil.getSellLogMessage;

/**
 * Class that does the management of the commands and the budget. This way algorithms don't need to worry about
 * buy/sell commands and budget tracking.
 *
 * Implements all methods in the Command interface.
 *
 * @author Steven de Cleene
 */
public abstract class TestBench implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger("testbench");

    private DataCollectionHandler dataCollectionHandler;

    private ResultVisualizer resultVisualizer;

    private Budget budget;

    private List<Long> buyTimestamps;

    private List<Long> sellTimestamps;

    public TestBench(final DataCollection dataCollection, final double initialBudget) {
        this.dataCollectionHandler = new DataCollectionHandler(dataCollection);
        this.resultVisualizer = new ResultVisualizer();
        this.budget = new Budget(dataCollection.getCurrencyPair(), initialBudget);
        this.buyTimestamps = new ArrayList<>();
        this.sellTimestamps = new ArrayList<>();
    }

    /**
     * Buys crypto with all remainig trade currency amount
     *
     * @param tickerTimestamp Unix timestamp of buying.
     */
    @Override
    public void buy(final Long tickerTimestamp) throws TestBenchException {
        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double tradeCurrencyAmount = budget.getTradeCurrency().getAmount();
        final double cryptoTransferAmount = tradeCurrencyAmount / ticker.getLast();
        budget.getCryptoCurrency().increaseAmount(cryptoTransferAmount);
        budget.getTradeCurrency().setAmount(0.0);

        addBuyTimestamp(ticker);

        LOGGER.info(getBuyLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), tradeCurrencyAmount, cryptoTransferAmount));
    }

    /**
     * Buys a certain amount of crypto
     *
     * @param tickerTimestamp Unix timestamp of buying.
     * @param tradeCurrencyAmount Amount of trade currency to buy crypto with
     */
    @Override
    public void buy(final Long tickerTimestamp, final Double tradeCurrencyAmount) throws TestBenchException {
        checkCanBuy(budget, tradeCurrencyAmount);

        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double cryptoTransferAmount = tradeCurrencyAmount / ticker.getLast();
        budget.getCryptoCurrency().increaseAmount(cryptoTransferAmount);
        budget.getTradeCurrency().decreaseAmount(tradeCurrencyAmount);

        addBuyTimestamp(ticker);

        LOGGER.info(getBuyLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), tradeCurrencyAmount, cryptoTransferAmount));
    }

    /**
     * Buys an amount of crypto based on a percentage of the total trade currency held
     *
     * @param tickerTimestamp  Unix timestamp of buying.
     * @param percentageAmount Percentage of total trade amount to buy with.
     */
    @Override
    public void buy(final Long tickerTimestamp, final Float percentageAmount) throws TestBenchException {
        checkPercentageAmount(percentageAmount);

        final Double tradeCurrencyAmount = budget.getTradeCurrency().getAmount() * percentageAmount;
        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double cryptoTransferAmount = tradeCurrencyAmount / ticker.getLast();
        budget.getCryptoCurrency().increaseAmount(cryptoTransferAmount);
        budget.getTradeCurrency().decreaseAmount(tradeCurrencyAmount);

        addBuyTimestamp(ticker);

        LOGGER.info(getBuyLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), tradeCurrencyAmount, cryptoTransferAmount));
    }

    /**
     * Sells all remaining crypto
     *
     * @param tickerTimestamp Unix timestamp of selling.
     */
    @Override
    public void sell(final Long tickerTimestamp) throws TestBenchException {
        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double cryptoTransferAmount = budget.getCryptoCurrency().getAmount();
        final double tradeTransferAmount = budget.getCryptoCurrency().getAmount() * ticker.getLast();
        budget.getTradeCurrency().increaseAmount(tradeTransferAmount);
        budget.getCryptoCurrency().setAmount(0.0);

        addSellTimestamp(ticker);

        LOGGER.info(getSellLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), cryptoTransferAmount, tradeTransferAmount));
    }

    /**
     * Sells an amount of cryptocurrency.
     *
     * @param tickerTimestamp Unix timestamp of selling.
     * @param cryptoCurrencyAmount Amount of crypto to sell
     */
    @Override
    public void sell(final Long tickerTimestamp, final Double cryptoCurrencyAmount) throws TestBenchException {
        checkCanSell(budget, cryptoCurrencyAmount);

        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double tradeTransferAmount = cryptoCurrencyAmount * ticker.getLast();
        budget.getTradeCurrency().increaseAmount(cryptoCurrencyAmount);
        budget.getCryptoCurrency().decreaseAmount(cryptoCurrencyAmount);

        addSellTimestamp(ticker);

        LOGGER.info(getSellLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), cryptoCurrencyAmount, tradeTransferAmount));
    }

    /**
     * Sells an amount of cryptocurrency based on a percentage of the total crypto amount held.
     *
     * @param tickerTimestamp Unix timestamp of selling.
     * @param percentageAmount Percentage of total crypto amount to sell.
     */
    @Override
    public void sell(final Long tickerTimestamp, final Float percentageAmount) throws TestBenchException {
        checkPercentageAmount(percentageAmount);

        final Double cryptoCurrencyAmount = budget.getCryptoCurrency().getAmount() * percentageAmount;
        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double tradeTransferAmount = cryptoCurrencyAmount * ticker.getLast();
        budget.getTradeCurrency().increaseAmount(cryptoCurrencyAmount);
        budget.getCryptoCurrency().decreaseAmount(cryptoCurrencyAmount);

        addSellTimestamp(ticker);

        LOGGER.info(getSellLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), cryptoCurrencyAmount, tradeTransferAmount));
    }

    /**
     * Creates a chart with algorithm results and saves it to the charts folder.
     */
    @Override
    public void visualizeAlgorithmResult() {
        resultVisualizer.createAlgorithmResultChart(this.getClass(), dataCollectionHandler.getDataCollection(), buyTimestamps, sellTimestamps);
    }

    private void addBuyTimestamp(final Ticker ticker) {
        buyTimestamps.add(ticker.getTimestamp());
    }

    private void addSellTimestamp(final Ticker ticker) {
        sellTimestamps.add(ticker.getTimestamp());
    }

}
