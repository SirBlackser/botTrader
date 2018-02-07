package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.Ticker;
import be.ds.projects.botTrader.testbench.exception.InsufficientCryptoBudgetException;
import be.ds.projects.botTrader.testbench.exception.InsufficientTradeBudgetException;
import be.ds.projects.botTrader.testbench.exception.InvalidTickerTimestampException;
import be.ds.projects.botTrader.testbench.exception.TestBenchException;
import be.ds.projects.botTrader.testbench.model.Budget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static be.ds.projects.botTrader.util.DataCollectionUtil.getTickerFromDataCollectionBasedOnTimestamp;
import static be.ds.projects.botTrader.util.LogUtil.getBuyLogMessage;
import static be.ds.projects.botTrader.util.LogUtil.getSellLogMessage;

/**
 * Class that does the management of the commands and the budget. This way algorithms don't need to worry about
 * buy/sell commands and budget tracking.
 *
 * Implements all methods in the Command interface.
 *
 * TODO: Add a "verifier" class that verifies buy/sell actions (no cheating allowed)
 *
 * @author Steven de Cleene
 */
public abstract class TestBench implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger("testbench");

    private DataCollection dataCollection;

    private Budget budget;

    public TestBench(final DataCollection dataCollection, final double initialBudget) {
        this.dataCollection = dataCollection;
        this.budget = new Budget(dataCollection.getCurrencyPair(), initialBudget);
    }

    @Override
    public void buy(final Long tickerTimestamp) throws TestBenchException {
        final Optional<Ticker> ticker = getTickerFromDataCollectionBasedOnTimestamp(dataCollection, tickerTimestamp);
        if (!ticker.isPresent()) {
            throw new InvalidTickerTimestampException();
        }

        final double cryptoTransferAmount = budget.getTradeCurrency().getAmount() / ticker.get().getLast();
        budget.getCryptoCurrency().increaseAmount(cryptoTransferAmount);
        budget.getTradeCurrency().setAmount(0.0);

        LOGGER.info(getBuyLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), budget.getTradeCurrency().getAmount(), cryptoTransferAmount));
    }

    @Override
    public void buy(final Long tickerTimestamp, final Double tradeCurrencyAmount) throws TestBenchException {
        if (tradeCurrencyAmount > budget.getTradeCurrency().getAmount()) {
            throw new InsufficientTradeBudgetException();
        }

        final Optional<Ticker> ticker = getTickerFromDataCollectionBasedOnTimestamp(dataCollection, tickerTimestamp);
        if (!ticker.isPresent()) {
            throw new InvalidTickerTimestampException();
        }

        final double cryptoTransferAmount = tradeCurrencyAmount / ticker.get().getLast();
        budget.getCryptoCurrency().increaseAmount(cryptoTransferAmount);
        budget.getTradeCurrency().decreaseAmount(tradeCurrencyAmount);

        LOGGER.info(getBuyLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), tradeCurrencyAmount, cryptoTransferAmount));
    }

    @Override
    public void buy(final Long tickerTimestamp, final Float percentageAmount) throws TestBenchException {
        final Double tradeCurrencyAmount = budget.getTradeCurrency().getAmount() * percentageAmount;

        final Optional<Ticker> ticker = getTickerFromDataCollectionBasedOnTimestamp(dataCollection, tickerTimestamp);
        if (!ticker.isPresent()) {
            throw new InvalidTickerTimestampException();
        }

        final double cryptoTransferAmount = tradeCurrencyAmount / ticker.get().getLast();
        budget.getCryptoCurrency().increaseAmount(cryptoTransferAmount);
        budget.getTradeCurrency().decreaseAmount(tradeCurrencyAmount);

        LOGGER.info(getBuyLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), tradeCurrencyAmount, cryptoTransferAmount));
    }

    @Override
    public void sell(final Long tickerTimestamp) throws TestBenchException {
        final Optional<Ticker> ticker = getTickerFromDataCollectionBasedOnTimestamp(dataCollection, tickerTimestamp);
        if (!ticker.isPresent()) {
            throw new InvalidTickerTimestampException();
        }

        final double tradeTransferAmount = budget.getCryptoCurrency().getAmount() * ticker.get().getLast();
        budget.getTradeCurrency().increaseAmount(tradeTransferAmount);
        budget.getCryptoCurrency().setAmount(0.0);

        LOGGER.info(getSellLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), budget.getCryptoCurrency().getAmount(), tradeTransferAmount));
    }

    @Override
    public void sell(final Long tickerTimestamp, final Double cryptoCurrencyAmount) throws TestBenchException {
        if (cryptoCurrencyAmount > budget.getCryptoCurrency().getAmount()) {
            throw new InsufficientCryptoBudgetException();
        }

        final Optional<Ticker> ticker = getTickerFromDataCollectionBasedOnTimestamp(dataCollection, tickerTimestamp);
        if (!ticker.isPresent()) {
            throw new InvalidTickerTimestampException();
        }

        final double tradeTransferAmount = cryptoCurrencyAmount * ticker.get().getLast();
        budget.getTradeCurrency().increaseAmount(cryptoCurrencyAmount);
        budget.getCryptoCurrency().decreaseAmount(cryptoCurrencyAmount);

        LOGGER.info(getSellLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), cryptoCurrencyAmount, tradeTransferAmount));
    }

    @Override
    public void sell(final Long tickerTimestamp, final Float percentageAmount) throws TestBenchException {
        final Double cryptoCurrencyAmount = budget.getCryptoCurrency().getAmount() * percentageAmount;

        final Optional<Ticker> ticker = getTickerFromDataCollectionBasedOnTimestamp(dataCollection, tickerTimestamp);
        if (!ticker.isPresent()) {
            throw new InvalidTickerTimestampException();
        }

        final double tradeTransferAmount = cryptoCurrencyAmount * ticker.get().getLast();
        budget.getTradeCurrency().increaseAmount(cryptoCurrencyAmount);
        budget.getCryptoCurrency().decreaseAmount(cryptoCurrencyAmount);

        LOGGER.info(getSellLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), cryptoCurrencyAmount, tradeTransferAmount));
    }

}
