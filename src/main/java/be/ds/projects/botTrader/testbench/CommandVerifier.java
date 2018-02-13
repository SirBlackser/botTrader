package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.testbench.model.Budget;

/**
 * @author Steven de Cleene
 */
public class CommandVerifier {

    public static boolean canBuy(final Budget budget, final Double amount) {
        return amount <= budget.getTradeCurrency().getAmount();
    }

    public static boolean canSell(final Budget budget, final Double amount) {
        return amount <= budget.getCryptoCurrency().getAmount();
    }

}
