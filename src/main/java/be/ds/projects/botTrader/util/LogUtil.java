package be.ds.projects.botTrader.util;

import be.ds.projects.botTrader.model.Currency;
import be.ds.projects.botTrader.model.CurrencyValue;

/**
 * @author Steven de Cleene
 */
public class LogUtil {

    public static String getBuyLogMessage(final Long tickerTimestamp, final CurrencyValue tradeCurrency, final CurrencyValue cryptoCurrency,
                                          final double transferAmount) {
        return "[" + tickerTimestamp + "] BUY - " + tradeCurrency.getAmount() + " " + tradeCurrency.getCurrency().currency() +
                " -> " + transferAmount + " " + cryptoCurrency.getCurrency().currency();
    }

    public static String getSellLogMessage(final Long tickerTimestamp, final CurrencyValue tradeCurrency, final CurrencyValue cryptoCurrency,
                                           final double transferAmount) {
        return "[" + tickerTimestamp + "] SELL - " + cryptoCurrency.getAmount() + " " + cryptoCurrency.getCurrency().currency() +
                " -> " + transferAmount + " " + tradeCurrency.getCurrency().currency();
    }

    public static String getFinalTradeValueLogMessage(final double finalTradeValue, final Currency tradeCurrency) {
        return "FINAL - " + finalTradeValue + " " + tradeCurrency.currency();
    }

}
