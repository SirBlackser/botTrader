package be.ds.projects.botTrader.bitstamp;

import be.ds.projects.botTrader.model.CurrencyPair;
import be.ds.projects.botTrader.model.OrderBook;
import be.ds.projects.botTrader.model.Ticker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;

import static be.ds.projects.botTrader.bitstamp.BitStampEndPoint.ORDER_BOOK;
import static be.ds.projects.botTrader.bitstamp.BitStampEndPoint.TICKER;

/**
 * Class that handles the communication with the Bitstamp API.
 *
 * @author Steven de Cleene
 */
public class BitStampInterfacer implements BitStampParameterKeys {

    public static Ticker ticker(final CurrencyPair currencyPair) throws Exception {
        final String jsonResponse = Unirest.get(TICKER.endpoint())
                .routeParam(ROUTE_PARAM_CURRENCY_PAIR, currencyPair.currencyPair())
                .asJson().getBody().toString();
        return new ObjectMapper().readValue(jsonResponse, Ticker.class);
    }

    public static OrderBook orderBook(final CurrencyPair currencyPair) throws Exception {
        final String jsonResponse = Unirest.get(ORDER_BOOK.endpoint())
                .routeParam("currency_pair", currencyPair.currencyPair())
                .asJson().getBody().toString();
        return new ObjectMapper().readValue(jsonResponse, OrderBook.class);
    }

}
