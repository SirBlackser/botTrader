package be.ds.projects.botTrader.model;

/**
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
public enum CurrencyPair {

    BTC2EUR("btceur"),
    RIPPLE2EUR("xrpeur"),
    LITECOIN2EUR("ltceur"),
    ETH2EUR("etheur"),
    BITCOINCASH2EUR("bcheur");

    private final String currencyPair;

    CurrencyPair(final String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public String currencyPair() {
        return currencyPair;
    }

}
