package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.testbench.exception.InsufficientCryptoBudgetException;
import be.ds.projects.botTrader.testbench.exception.InsufficientTradeBudgetException;
import be.ds.projects.botTrader.testbench.exception.InvalidPercentageException;
import be.ds.projects.botTrader.testbench.model.Budget;

/**
 * @author Steven de Cleene
 */
public class CommandVerifier {

    public static void checkCanBuy(final Budget budget, final Double amount) throws InsufficientTradeBudgetException {
        if (amount <= budget.getTradeCurrency().getAmount()) {
            throw new InsufficientTradeBudgetException();
        }
    }

    public static void checkCanSell(final Budget budget, final Double amount) throws InsufficientCryptoBudgetException {
        if (amount <= budget.getCryptoCurrency().getAmount()) {
            throw new InsufficientCryptoBudgetException();
        }
    }

    public static void checkPercentageAmount(final Float percentageAmount) throws InvalidPercentageException {
        if (percentageAmount < 0.0 || percentageAmount > 100.0) {
            throw new InvalidPercentageException();
        }
    }

}
