package be.ds.projects;

import be.ds.projects.enums.CurrencyPair;
import be.ds.projects.rest.BitStampInterfacer;
import be.ds.projects.util.JavaToStringUtil;
import com.mashape.unirest.http.Unirest;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

public class Main {

    public static void main(String[] args) throws Exception {
        fixUnirestInvalidCookieHeader();

//        System.out.println(JavaToStringUtil.makePretty(BitStampInterfacer.ticker(CurrencyPair.BTC2EUR)));
//        System.out.println(JavaToStringUtil.makePretty(BitStampInterfacer.hourlyTicker(CurrencyPair.BTC2EUR)));
        System.out.println(JavaToStringUtil.makePretty(BitStampInterfacer.orderBook(CurrencyPair.BTC2EUR)));
    }

    private static void fixUnirestInvalidCookieHeader() {
        HttpClient httpClient = HttpClients.custom()
                .disableCookieManagement()
                .build();
        Unirest.setHttpClient(httpClient);
    }

}
