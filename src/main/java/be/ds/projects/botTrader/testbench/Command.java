package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.testbench.exception.TestBenchException;

/**
 * Interface describing all the actions one can do to buy and sell currencies in the simulation.
 *
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
public interface Command {

    void buy(final Long tickerTimestamp) throws TestBenchException;

    void buy(final Long tickerTimestamp, final Double tradeCurrencyAmount) throws TestBenchException;

    void buy(final Long tickerTimestamp, final Float percentageAmount) throws TestBenchException;

    void sell(final Long tickerTimestamp) throws TestBenchException;

    void sell(final Long tickerTimestamp, final Double cryptoCurrencyAmount) throws TestBenchException;

    void sell(final Long tickerTimestamp, final Float percentageAmount) throws TestBenchException;

    void visualizeAlgorithmResult();

}
