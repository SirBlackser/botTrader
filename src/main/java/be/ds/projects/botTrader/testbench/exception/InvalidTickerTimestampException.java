package be.ds.projects.botTrader.testbench.exception;

/**
 * @author Steven de Cleene
 */
public class InvalidTickerTimestampException extends TestBenchException {

    public InvalidTickerTimestampException() {
        super("Specified timestamp doesn't match any Ticker in the data collection.");
    }

}
