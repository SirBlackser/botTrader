package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.testbench.model.Budget;

/**
 * @author Steven de Cleene
 */
public abstract class TestBench implements Command {

    private DataCollection dataCollection;

    private Budget budget;

    public TestBench(final DataCollection dataCollection, final double initialBudget) {
        this.dataCollection = dataCollection;
        this.budget = new Budget(dataCollection.getCurrencyPair(), initialBudget);
    }

    @Override
    public boolean buyMax() {
        return true;
    }

    @Override
    public boolean sellMax() {
        return true;
    }

}
