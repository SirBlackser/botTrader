public enum currencyPairs {
    btcToEur("btceur"),
    rippleToEur("xrpeur"),
    liteCoinToEur("ltceur"),
    ethToEur("etheur"),
    bitCoinCashToEur("bcheur");


    private final String pair;

    private currencyPairs(String pair) {
        this.pair = pair;
    }

    public String toPair() {
        return pair;
    }
}
