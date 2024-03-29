package be.ds.projects.botTrader.testbench.model;

import be.ds.projects.botTrader.model.CurrencyPair;

import static be.ds.projects.botTrader.util.CurrencyUtil.getCryptoCurrencyFromCurrencyPair;
import static be.ds.projects.botTrader.util.CurrencyUtil.getTradeCurrencyFromCurrencyPair;

/**
 * POJO containing data about two currencies linked to one another.
 *
 * e.g. simulation BTCEUR Currency will link them seperately through this class in the TestBench
 *
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
public class CurrencyPairCurrencies {

    private Currency tradeCurrency;

    private Currency cryptoCurrency;

    public CurrencyPairCurrencies(final CurrencyPair currencyPair) {
        this.tradeCurrency = getTradeCurrencyFromCurrencyPair(currencyPair);
        this.cryptoCurrency = getCryptoCurrencyFromCurrencyPair(currencyPair);
    }

    public Currency getTradeCurrency() {
        return tradeCurrency;
    }

    public Currency getCryptoCurrency() {
        return cryptoCurrency;
    }

}
