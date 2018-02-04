package be.ds.projects.botTrader.model;

import be.ds.projects.botTrader.model.Currency;

/**
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

    public void addAmount(final double amount) {
        this.amount += amount;
    }

}
