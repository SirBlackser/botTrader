package be.ds.projects.botTrader.testbench.exception;

/**
 * @author Steven de Cleene
 */
public class InsufficientCryptoBudgetException extends TestBenchException {

    public InsufficientCryptoBudgetException() {
        super("Insufficient crypto-currency budget to make purchase.");
    }

}
