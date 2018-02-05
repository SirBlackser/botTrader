package be.ds.projects.botTrader.testbench.exception;

/**
 * Exception that gets thrown when trying to fetch a Ticker using an invalid timestamp.
 *
 * @author Steven de Cleene
 */
public class InvalidTickerTimestampException extends TestBenchException {

    public InvalidTickerTimestampException() {
        super("Specified timestamp doesn't match any Ticker in the data collection.");
    }

}
