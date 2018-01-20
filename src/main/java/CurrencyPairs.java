public enum CurrencyPairs {
    btcToEur("btceur"),
    rippleToEur("xrpeur"),
    liteCoinToEur("ltceur"),
    ethToEur("etheur"),
    bitCoinCashToEur("bcheur");


    private final String pair;

    private CurrencyPairs(String pair) {
        this.pair = pair;
    }

    public String toPair() {
        return pair;
    }
}
