package be.ds.projects.botTrader.testbench.model;

/**
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
public enum Currency {

    EURO("eur"),
    DOLLAR("usd"),
    BITCOIN("btc"),
    RIPPLE("xrp"),
    LITECOIN("ltc"),
    ETHEREUM("eth"),
    BITCOINCASH("bch"),
    UNKNOWN("");

    private final String currency;

    Currency(final String currency) {
        this.currency = currency;
    }

    public String currency() {
        return currency;
    }

}
