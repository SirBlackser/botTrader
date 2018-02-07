package be.ds.projects.botTrader.util;

import be.ds.projects.botTrader.testbench.model.Currency;
import be.ds.projects.botTrader.testbench.model.CurrencyValue;

/**
 * Utility class that formats a proper trader log line.
 *
 * @author Steven de Cleene
 */
public class LogUtil {

    public static String getBuyLogMessage(final Long tickerTimestamp, final CurrencyValue tradeCurrency, final CurrencyValue cryptoCurrency,
                                          final double tradeAmount, final double cryptoAmount) {
        return "[" + tickerTimestamp + "] BUY - " + tradeAmount + " " + tradeCurrency.getCurrency().currency() +
                " -> " + cryptoAmount + " " + cryptoCurrency.getCurrency().currency();
    }

    public static String getSellLogMessage(final Long tickerTimestamp, final CurrencyValue tradeCurrency, final CurrencyValue cryptoCurrency,
                                           final double cryptoAmount, final double tradeAmount) {
        return "[" + tickerTimestamp + "] SELL - " + cryptoAmount + " " + cryptoCurrency.getCurrency().currency() +
                " -> " + tradeAmount + " " + tradeCurrency.getCurrency().currency();
    }

}
