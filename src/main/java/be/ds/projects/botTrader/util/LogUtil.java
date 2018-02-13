package be.ds.projects.botTrader.util;

import be.ds.projects.botTrader.testbench.model.CurrencyValue;

/**
 * Utility class that formats a proper trader log line.
 *
 * @author Steven de Cleene
 */
public class LogUtil {

    private static final String DECIMAL_FORMAT = "%.12f";

    public static String getBuyLogMessage(final Long tickerTimestamp, final CurrencyValue tradeCurrency, final CurrencyValue cryptoCurrency,
                                          final double tradeAmount, final double cryptoAmount) {
        return "[" + tickerTimestamp + "] BUY  - " + String.format(DECIMAL_FORMAT, tradeAmount) + " " + tradeCurrency.getCurrency().currency().toUpperCase() +
                " -> " + String.format(DECIMAL_FORMAT, cryptoAmount) + " " + cryptoCurrency.getCurrency().currency().toUpperCase();
    }

    public static String getSellLogMessage(final Long tickerTimestamp, final CurrencyValue tradeCurrency, final CurrencyValue cryptoCurrency,
                                           final double cryptoAmount, final double tradeAmount) {
        return "[" + tickerTimestamp + "] SELL - " + String.format(DECIMAL_FORMAT, cryptoAmount) + " " + cryptoCurrency.getCurrency().currency().toUpperCase() +
                " -> " + String.format(DECIMAL_FORMAT, tradeAmount) + " " + tradeCurrency.getCurrency().currency().toUpperCase();
    }

}
