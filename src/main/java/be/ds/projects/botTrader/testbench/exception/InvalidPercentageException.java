package be.ds.projects.botTrader.testbench.exception;

/**
 * @author Steven de Cleene
 */
public class InvalidPercentageException extends TestBenchException {

    public InvalidPercentageException() {
        super("Percentage amount must be between 0 and 100 percent (inclusive)");
    }

}
