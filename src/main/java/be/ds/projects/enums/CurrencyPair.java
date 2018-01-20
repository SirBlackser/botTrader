package be.ds.projects.enums;

public enum CurrencyPair {

    BTC2EUR("btceur"),
    RIPPLE2EUR("xrpeur"),
    LITECOIN2EUR("ltceur"),
    ETH2EUR("etheur"),
    BITCOINCASH2EUR("bcheur");

    private final String currencyPair;

    CurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public String currencyPair() {
        return currencyPair;
    }

}
