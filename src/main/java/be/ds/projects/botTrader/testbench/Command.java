package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.exception.TestBenchException;

/**
 * @author Steven de Cleene
 */
public interface Command {

    void buy(final Long tickerTimestamp) throws TestBenchException;

    void sell(final Long tickerTimestamp) throws TestBenchException;

}
