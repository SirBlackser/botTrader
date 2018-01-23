package be.ds.poc;

import be.ds.projects.model.Answer;
import be.ds.projects.model.Transaction;
import org.apache.tomcat.jni.Time;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class Tester {

    //set starting wallet
    double wallet = 100;
    double btcWallet = 0;
    double rippleWallet = 0;
    double LtcWallet = 0;
    double BchWallet = 0;
    double ethWallet = 0;

    @PostMapping("/api/v2/buy/market/{currency_pair}/")
    public JSONObject buyOrder(@PathParam("currency_pair") String currency, @RequestBody Transaction transaction) {
        if(transaction.getPrice()*transaction.getAmount() > wallet) {
            Error error = new Error("not enough euro present on account");
            //TODO return error
            return null;
        } else {
            Answer answer = new Answer(Time.now(), Time.now(), 0, transaction.getPrice(), transaction.getAmount());
            switch (currency) {
                case "btceur" :
                    wallet -= (transaction.getAmount() * transaction.getPrice()) * 1.025;
                    btcWallet += transaction.getAmount();
                    break;
                case "xrpeur" :
                    wallet -= (transaction.getAmount() * transaction.getPrice()) * 1.025;
                    rippleWallet += transaction.getAmount();
                    break;
                case "ltceur" :
                    wallet -= (transaction.getAmount() * transaction.getPrice()) * 1.025;
                    LtcWallet += transaction.getAmount();
                    break;
                case "etheur" :
                    wallet -= (transaction.getAmount() * transaction.getPrice()) * 1.025;
                    ethWallet += transaction.getAmount();
                    break;
                case "bcheur" :
                    wallet -= (transaction.getAmount() * transaction.getPrice()) * 1.025;
                    BchWallet += transaction.getAmount();
                    break;
                default :
                    Error error = new Error("invalid currency pair");
                    break;
            }

            //TODO return json
            return null;
        }
    }

    @PostMapping("/api/v2/buy/market/{currency_pair}/")
    public JSONObject sellOrder(@PathParam("currency_pair") String currency, @RequestBody Transaction transaction) {
        Answer answer = new Answer(Time.now(), Time.now(), 1, transaction.getPrice(), transaction.getAmount());
        switch (currency) {
            case "btceur" :
                if(transaction.getAmount() > btcWallet) {
                    Error error = new Error("not enough bitcoin present on account");
                    //TODO return error
                    return null;
                } else {
                    wallet += (transaction.getAmount() * transaction.getPrice()) * 0.975;
                    btcWallet -= transaction.getAmount();
                    break;
                }
            case "xrpeur" :
                if(transaction.getAmount() > rippleWallet) {
                    Error error = new Error("not enough Ripple present on account");
                    //TODO return error
                    return null;
                } else {
                    wallet += (transaction.getAmount() * transaction.getPrice()) * 0.975;
                    rippleWallet -= transaction.getAmount();
                    break;
                }
            case "ltceur" :
                if(transaction.getAmount() > btcWallet) {
                    Error error = new Error("not enough Litecoin present on account");
                    //TODO return error
                    return null;
                } else {
                    wallet += (transaction.getAmount() * transaction.getPrice()) * 0.975;
                    LtcWallet -= transaction.getAmount();
                    break;
                }
            case "etheur" :
                if(transaction.getAmount() > btcWallet) {
                    Error error = new Error("not enough etherium present on account");
                    //TODO return error
                    return null;
                } else {
                    wallet += (transaction.getAmount() * transaction.getPrice()) * 0.975;
                    ethWallet -= transaction.getAmount();
                    break;
                }
            case "bcheur" :
                if(transaction.getAmount() > btcWallet) {
                    Error error = new Error("not enough bitCoinCash present on account");
                    //TODO return error
                    return null;
                } else {
                    wallet += (transaction.getAmount() * transaction.getPrice()) * 0.975;
                    BchWallet -= transaction.getAmount();
                    break;
                }
            default :
                Error error = new Error("invalid currency pair");
                break;
        }
        //TODO remove later?
        return null;
    }

    //TODO implement
    //@PostMapping(/api/v2/balance/)

    //TODO implement
    //@PostMapping(/api/v2/balance/{currency_pair}/)

}
