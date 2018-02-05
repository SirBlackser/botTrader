package be.ds.projects.botTrader.bitstamp;

/**
 * All the endpoints referenced in the BitStampInterfacer.
 *
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
public enum BitStampEndPoint {

    TICKER("https://www.bitstamp.net/api/v2/ticker/{currency_pair}/"),
    ORDER_BOOK("https://www.bitstamp.net/api/v2/order_book/{currency_pair}/");

    private final String endpoint;

    BitStampEndPoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    public String endpoint() {
        return endpoint;
    }

}
