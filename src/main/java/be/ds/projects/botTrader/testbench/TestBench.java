package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.Ticker;
import be.ds.projects.botTrader.testbench.exception.InvalidTickerTimestampException;
import be.ds.projects.botTrader.testbench.exception.TestBenchException;
import be.ds.projects.botTrader.testbench.model.Budget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static be.ds.projects.botTrader.util.DataCollectionUtil.getTickerFromDataCollectionBasedOnTimestamp;
import static be.ds.projects.botTrader.util.LogUtil.*;

/**
 * @author Steven de Cleene
 */
public abstract class TestBench implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger("testbench");

    //TODO: Move to seperate Verifier class (for when we do partial buys/sells)
    private DataCollection dataCollection;

    private Budget budget;

    private double lastBuyPrice = 0.0;

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

        lastBuyPrice = ticker.get().getLast();
        final double cryptoTransferAmount = budget.getTradeCurrency().getAmount() / ticker.get().getLast();
        budget.getCryptoCurrency().addAmount(cryptoTransferAmount);
        budget.getTradeCurrency().setAmount(0.0);

        LOGGER.info(getBuyLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), cryptoTransferAmount));
    }

    @Override
    public void sell(final Long tickerTimestamp) throws TestBenchException {
        final Optional<Ticker> ticker = getTickerFromDataCollectionBasedOnTimestamp(dataCollection, tickerTimestamp);
        if (!ticker.isPresent()) {
            throw new InvalidTickerTimestampException();
        }

        final double tradeTransferAmount = budget.getCryptoCurrency().getAmount() * ticker.get().getLast();
        budget.getTradeCurrency().addAmount(tradeTransferAmount);
        budget.getCryptoCurrency().setAmount(0.0);

        LOGGER.info(getSellLogMessage(tickerTimestamp, budget.getTradeCurrency(), budget.getCryptoCurrency(), tradeTransferAmount));
    }

    public double getFinalTradeValue() {
        final double finalTradeValue = budget.getFinalTradeValue(lastBuyPrice);
        LOGGER.info(getFinalTradeValueLogMessage(finalTradeValue, budget.getTradeCurrency().getCurrency()));
        return finalTradeValue;
    }

}
