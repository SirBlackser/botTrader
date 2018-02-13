package be.ds.projects.botTrader.testbench.algorithm;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.DataPoint;
import be.ds.projects.botTrader.testbench.TestBench;
import be.ds.projects.botTrader.testbench.exception.TestBenchException;

/**
 * @author Steven de Cleene
 */
public class AlgorithmS01 extends TestBench {

    private static final Double INITIAL_BUDGET = 50.00;

    private DataCollection dataCollection;

    public AlgorithmS01(final DataCollection dataCollection) {
        super(dataCollection, INITIAL_BUDGET);
        this.dataCollection = dataCollection;
    }

    public void execute() throws TestBenchException {
        int count = 0;
        for (final DataPoint dataPoint : dataCollection.getDataPoints()) {
            if (count % 100 == 0) {
                buy(dataPoint.getTicker().getTimestamp());
            }
            if (count % 100 == 50) {
                sell(dataPoint.getTicker().getTimestamp());
            }
            count++;
        }
        visualizeAlgorithmResult();
    }

}
