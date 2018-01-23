package be.ds.projects.rest;

import be.ds.projects.enums.CurrencyPair;
import be.ds.projects.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.List;

public class BitStampInterfacer {

    private static final String TICKER_ENDPOINT = "https://www.bitstamp.net/api/v2/ticker/{currency_pair}/";

    public static Ticker ticker(final CurrencyPair currencyPair) throws UnirestException, IOException {
        final String jsonResponse = Unirest.get(TICKER_ENDPOINT)
                .routeParam("currency_pair", currencyPair.currencyPair())
                .asJson().getBody().toString();
        return new ObjectMapper().readValue(jsonResponse, Ticker.class);
    }

    private static final String HOURLY_TICKER_ENDPOINT = "https://www.bitstamp.net/api/v2/ticker_hour/{currency_pair}/";

    public static Ticker hourlyTicker(final CurrencyPair currencyPair) throws UnirestException, IOException {
        final String jsonResponse = Unirest.get(HOURLY_TICKER_ENDPOINT)
                .routeParam("currency_pair", currencyPair.currencyPair())
                .asJson().getBody().toString();
        return new ObjectMapper().readValue(jsonResponse, Ticker.class);
    }

    private static final String ORDER_BOOK_ENDPOINT = "https://www.bitstamp.net/api/v2/order_book/{currency_pair}/";

    public static OrderBook orderBook(final CurrencyPair currencyPair) throws UnirestException, IOException {
        final String jsonResponse = Unirest.get(ORDER_BOOK_ENDPOINT)
                .routeParam("currency_pair", currencyPair.currencyPair())
                .asJson().getBody().toString();
        return new ObjectMapper().readValue(jsonResponse, OrderBook.class);
    }

    private static final String TRANSACTIONS_ENDPOINT = "https://www.bitstamp.net/api/v2/transactions/{currency_pair}/";

    public static List<Transaction> transactions(final CurrencyPair currencyPair) throws UnirestException, IOException {
        final String jsonResponse = Unirest.get(TRANSACTIONS_ENDPOINT)
                .routeParam("currency_pair", currencyPair.currencyPair())
                .asJson().getBody().toString();
        return new ObjectMapper().readValue(jsonResponse, new TypeReference<List<Transaction>>() {});
    }

    private static final String TRADING_PAIRS_INFO_ENDPOINT = "https://www.bitstamp.net/api/v2/trading-pairs-info/";

    public static List<TradingPairInfo> tradingPairInfos() throws UnirestException, IOException {
        final String jsonResponse = Unirest.get(TRADING_PAIRS_INFO_ENDPOINT)
                .asJson().getBody().toString();
        return new ObjectMapper().readValue(jsonResponse, new TypeReference<List<TradingPairInfo>>() {});
    }

    private static final String EURUSD_CONVERSTION_RATE_ENDPOINT = "https://www.bitstamp.net/api/eur_usd/";

    public static EuUsdConvertsionRate euUsdConversionRate() throws UnirestException, IOException {
        final String jsonResponse = Unirest.get(EURUSD_CONVERSTION_RATE_ENDPOINT)
                .asJson().getBody().toString();
        return new ObjectMapper().readValue(jsonResponse, EuUsdConvertsionRate.class);
    }

}
