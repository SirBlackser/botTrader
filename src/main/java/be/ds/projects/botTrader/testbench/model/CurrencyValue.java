package be.ds.projects.botTrader.testbench.model;

/**
 * POJO linked to the Budget class that keeps track of the current balance of the specified currency.
 *
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
public class CurrencyValue {

    private Currency currency;

    private double amount;

    public CurrencyValue(final Currency currency, final double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void increaseAmount(final double amount) {
        this.amount += amount;
    }

    public void decreaseAmount(final double amount) {
        this.amount -= amount;
    }

}
