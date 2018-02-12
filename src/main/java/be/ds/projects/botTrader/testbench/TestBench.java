package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.Ticker;
import be.ds.projects.botTrader.testbench.exception.InsufficientCryptoBudgetException;
import be.ds.projects.botTrader.testbench.exception.InsufficientTradeBudgetException;
import be.ds.projects.botTrader.testbench.exception.TestBenchException;
import be.ds.projects.botTrader.testbench.model.Budget;
import be.ds.projects.botTrader.testbench.visualizer.ResultVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static be.ds.projects.botTrader.testbench.CommandVerifier.canBuy;
import static be.ds.projects.botTrader.testbench.CommandVerifier.canSell;
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
        this.resultVisualizer = new ResultVisualizer(dataCollection);
        this.budget = new Budget(dataCollection.getCurrencyPair(), initialBudget);
        this.buyTimestamps = new ArrayList<>();
        this.sellTimestamps = new ArrayList<>();
    }

    @Override
    public void buy(final Long tickerTimestamp) throws TestBenchException {
        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double cryptoTransferAmount = budget.getTradeCurrency().getAmount() / ticker.getLast();
        budget.getCryptoCurrency().increaseAmount(cryptoTransferAmount);
        budget.getTradeCurrency().setAmount(0.0);

        addBuyTimestamp(ticker);

        LOGGER.info(getBuyLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), budget.getTradeCurrency().getAmount(), cryptoTransferAmount));
    }

    @Override
    public void buy(final Long tickerTimestamp, final Double tradeCurrencyAmount) throws TestBenchException {
        if (!canBuy(budget, tradeCurrencyAmount)) {
            throw new InsufficientTradeBudgetException();
        }

        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double cryptoTransferAmount = tradeCurrencyAmount / ticker.getLast();
        budget.getCryptoCurrency().increaseAmount(cryptoTransferAmount);
        budget.getTradeCurrency().decreaseAmount(tradeCurrencyAmount);

        addBuyTimestamp(ticker);

        LOGGER.info(getBuyLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), tradeCurrencyAmount, cryptoTransferAmount));
    }

    @Override
    public void buy(final Long tickerTimestamp, final Float percentageAmount) throws TestBenchException {
        final Double tradeCurrencyAmount = budget.getTradeCurrency().getAmount() * percentageAmount;
        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double cryptoTransferAmount = tradeCurrencyAmount / ticker.getLast();
        budget.getCryptoCurrency().increaseAmount(cryptoTransferAmount);
        budget.getTradeCurrency().decreaseAmount(tradeCurrencyAmount);

        addBuyTimestamp(ticker);

        LOGGER.info(getBuyLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), tradeCurrencyAmount, cryptoTransferAmount));
    }

    @Override
    public void sell(final Long tickerTimestamp) throws TestBenchException {
        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double tradeTransferAmount = budget.getCryptoCurrency().getAmount() * ticker.getLast();
        budget.getTradeCurrency().increaseAmount(tradeTransferAmount);
        budget.getCryptoCurrency().setAmount(0.0);

        addSellTimestamp(ticker);

        LOGGER.info(getSellLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), budget.getCryptoCurrency().getAmount(), tradeTransferAmount));
    }

    @Override
    public void sell(final Long tickerTimestamp, final Double cryptoCurrencyAmount) throws TestBenchException {
        if (!canSell(budget, cryptoCurrencyAmount)) {
            throw new InsufficientCryptoBudgetException();
        }

        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double tradeTransferAmount = cryptoCurrencyAmount * ticker.getLast();
        budget.getTradeCurrency().increaseAmount(cryptoCurrencyAmount);
        budget.getCryptoCurrency().decreaseAmount(cryptoCurrencyAmount);

        addSellTimestamp(ticker);

        LOGGER.info(getSellLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), cryptoCurrencyAmount, tradeTransferAmount));
    }

    @Override
    public void sell(final Long tickerTimestamp, final Float percentageAmount) throws TestBenchException {
        final Double cryptoCurrencyAmount = budget.getCryptoCurrency().getAmount() * percentageAmount;
        final Ticker ticker = dataCollectionHandler.getTickerFromTimestamp(tickerTimestamp);

        final double tradeTransferAmount = cryptoCurrencyAmount * ticker.getLast();
        budget.getTradeCurrency().increaseAmount(cryptoCurrencyAmount);
        budget.getCryptoCurrency().decreaseAmount(cryptoCurrencyAmount);

        addSellTimestamp(ticker);

        LOGGER.info(getSellLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), cryptoCurrencyAmount, tradeTransferAmount));
    }

    @Override
    public void visualizeAlgorithmResult() {
        resultVisualizer.showAlgorithmResults(buyTimestamps, sellTimestamps);
    }

    private void addBuyTimestamp(final Ticker ticker) {
        buyTimestamps.add(ticker.getTimestamp());
    }

    private void addSellTimestamp(final Ticker ticker) {
        sellTimestamps.add(ticker.getTimestamp());
    }

}
