package be.ds.projects.botTrader.util;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.DataPoint;
import be.ds.projects.botTrader.model.Ticker;

import java.util.Optional;

/**
 * Utility class that searching for data in a DataCollection
 *
 * TODO: getTickerFromDataCollectionBasedOnTimestamp needs to be optimized, multi-day simulations may have 4k+ datapoints
 *
 * @author Steven de Cleene
 */
public class DataCollectionUtil {

    //TODO: Needs to be optimized once it goes into verifier
    public static Optional<Ticker> getTickerFromDataCollectionBasedOnTimestamp(final DataCollection dataCollection, final Long tickerTimestmap) {
        for (DataPoint dataPoint : dataCollection.getDataPoints()) {
            if (tickerTimestmap.equals(dataPoint.getTicker().getTimestamp())) {
                return Optional.of(dataPoint.getTicker());
            }
        }
        return Optional.empty();
    }

}
