package be.ds.projects.botTrader.testbench.model;

import be.ds.projects.botTrader.model.CurrencyPair;

/**
 * Budget POJO that keeps track of the current balance in the simulation.
 *
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
public class Budget {

    private CurrencyValue tradeCurrency;

    private CurrencyValue cryptoCurrency;

    public Budget(final CurrencyPair currencyPair, final double initialTradeCurrencyAmount) {
        final CurrencyPairCurrencies currencyPairCurrencies = new CurrencyPairCurrencies(currencyPair);
        this.tradeCurrency = new CurrencyValue(currencyPairCurrencies.getTradeCurrency(), initialTradeCurrencyAmount);
        this.cryptoCurrency = new CurrencyValue(currencyPairCurrencies.getCryptoCurrency(), 0.0);
    }

    public CurrencyValue getTradeCurrency() {
        return tradeCurrency;
    }

    public void setTradeCurrency(CurrencyValue tradeCurrency) {
        this.tradeCurrency = tradeCurrency;
    }

    public CurrencyValue getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(CurrencyValue cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    /**
     * Gets the final result of the simulation.
     *
     * When there is money invested in crypto at the end of the simulation, add the price of purchase to the trade
     * balance.
     *
     * TODO: This does not account for the possibility to have purchased crypto at different times.
     *
     * @param lastBuyPrice Purchase price of the crypto.
     * @return Value describing final trade value after the simulation
     */
    public double getFinalTradeValue(final double lastBuyPrice) {
        double tradeAmount = tradeCurrency.getAmount();
        if (lastBuyPrice != 0.0) {
            tradeAmount += cryptoCurrency.getAmount() * lastBuyPrice;
        }
        return tradeAmount;
    }

}
