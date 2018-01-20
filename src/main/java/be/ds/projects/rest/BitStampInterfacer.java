package be.ds.projects.rest;

import be.ds.projects.enums.CurrencyPair;
import be.ds.projects.model.Ticker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

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

}
