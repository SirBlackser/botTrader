package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.testbench.exception.TestBenchException;

/**
 * Interface describing all the actions one can do to buy and sell currencies in the simulation.
 *
 * TODO: Enable ability to buy a certain amount of a certain currency, not always 100%
 *
 * @author Steven de Cleene
 */
public interface Command {

    void buy(final Long tickerTimestamp) throws TestBenchException;

    void sell(final Long tickerTimestamp) throws TestBenchException;

}
