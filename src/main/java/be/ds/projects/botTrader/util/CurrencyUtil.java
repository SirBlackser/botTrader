package be.ds.projects.botTrader.util;

import be.ds.projects.botTrader.model.CurrencyPair;
import be.ds.projects.botTrader.testbench.model.Currency;

/**
 * Utility class that gets the trade/crypto Currency from a CurrencyPair.
 *
 * @author Steven de Cleene
 */
public class CurrencyUtil {

    public static Currency getTradeCurrencyFromCurrencyPair(final CurrencyPair currencyPair) {
        return getCurrencyForCurrencyDescription(currencyPair.currencyPair().substring(3));
    }

    public static Currency getCryptoCurrencyFromCurrencyPair(final CurrencyPair currencyPair) {
        return getCurrencyForCurrencyDescription(currencyPair.currencyPair().substring(0, 3));
    }

    private static Currency getCurrencyForCurrencyDescription(final String currencyDescription) {
        for (Currency currency : Currency.values()) {
            if (currency.currency().equals(currencyDescription)) {
                return currency;
            }
        }
        return Currency.UNKNOWN;
    }

}
