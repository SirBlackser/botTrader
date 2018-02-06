package be.ds.projects.botTrader.testbench.exception;

/**
 * @author Steven de Cleene
 */
public class InsufficientTradeBudgetException extends TestBenchException {

    public InsufficientTradeBudgetException() {
        super("Insufficient trade-currency budget to make purchase.");
    }

}
