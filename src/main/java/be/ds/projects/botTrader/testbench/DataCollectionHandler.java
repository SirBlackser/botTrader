package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.Ticker;
import be.ds.projects.botTrader.testbench.exception.InvalidTickerTimestampException;
import be.ds.projects.botTrader.testbench.exception.TestBenchException;

/**
 * @author Steven de Cleene
 */
public class DataCollectionHandler {

    private DataCollection dataCollection;

    private int currentDataPointIndex;

    public DataCollectionHandler(final DataCollection dataCollection) {
        this.dataCollection = dataCollection;
        this.currentDataPointIndex = 0;
    }

    public Ticker getTickerFromTimestamp(final Long tickerTimestamp) throws TestBenchException {
        for (int index = currentDataPointIndex; index < dataCollection.getDataPoints().size(); index++) {
            final Ticker ticker = dataCollection.getDataPoints().get(index).getTicker();
            if (tickerTimestamp.equals(ticker.getTimestamp())) {
                currentDataPointIndex = index;
                return ticker;
            }
        }
        throw new InvalidTickerTimestampException();
    }

}
