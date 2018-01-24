package be.ds.poc;

import be.ds.projects.model.Answer;
import be.ds.projects.model.ErrorResponse;
import be.ds.projects.model.Transaction;
import org.apache.tomcat.jni.Time;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity buyOrder(@PathParam("currency_pair") String currency, @RequestBody Transaction transaction) {
        if(transaction.getPrice()*transaction.getAmount() > wallet) {
            ErrorResponse error = new ErrorResponse("not enough euro present on account");
            return new ResponseEntity(error, HttpStatus.OK);
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
                    ErrorResponse error = new ErrorResponse("invalid currency pair");
                    return new ResponseEntity(error, HttpStatus.OK);
            }
            return new ResponseEntity(answer, HttpStatus.OK);
        }
    }

    @PostMapping("/api/v2/buy/market/{currency_pair}/")
    public ResponseEntity sellOrder(@PathParam("currency_pair") String currency, @RequestBody Transaction transaction) {
        Answer answer = new Answer(Time.now(), Time.now(), 1, transaction.getPrice(), transaction.getAmount());
        switch (currency) {
            case "btceur" :
                if(transaction.getAmount() > btcWallet) {
                    ErrorResponse error = new ErrorResponse("not enough bitcoin present on account");
                    return new ResponseEntity(error, HttpStatus.OK);
                } else {
                    wallet += (transaction.getAmount() * transaction.getPrice()) * 0.975;
                    btcWallet -= transaction.getAmount();
                    return new ResponseEntity(answer, HttpStatus.OK);
                }
            case "xrpeur" :
                if(transaction.getAmount() > rippleWallet) {
                    ErrorResponse error = new ErrorResponse("not enough Ripple present on account");
                    return new ResponseEntity(error, HttpStatus.OK);
                } else {
                    wallet += (transaction.getAmount() * transaction.getPrice()) * 0.975;
                    rippleWallet -= transaction.getAmount();
                    return new ResponseEntity(answer, HttpStatus.OK);
                }
            case "ltceur" :
                if(transaction.getAmount() > btcWallet) {
                    ErrorResponse error = new ErrorResponse("not enough Litecoin present on account");
                    return new ResponseEntity(error, HttpStatus.OK);
                } else {
                    wallet += (transaction.getAmount() * transaction.getPrice()) * 0.975;
                    LtcWallet -= transaction.getAmount();
                    return new ResponseEntity(answer, HttpStatus.OK);
                }
            case "etheur" :
                if(transaction.getAmount() > btcWallet) {
                    ErrorResponse error = new ErrorResponse("not enough etherium present on account");
                    return new ResponseEntity(error, HttpStatus.OK);
                } else {
                    wallet += (transaction.getAmount() * transaction.getPrice()) * 0.975;
                    ethWallet -= transaction.getAmount();
                    return new ResponseEntity(answer, HttpStatus.OK);
                }
            case "bcheur" :
                if(transaction.getAmount() > btcWallet) {
                    ErrorResponse error = new ErrorResponse("not enough bitCoinCash present on account");
                    return new ResponseEntity(error, HttpStatus.OK);
                } else {
                    wallet += (transaction.getAmount() * transaction.getPrice()) * 0.975;
                    BchWallet -= transaction.getAmount();
                    return new ResponseEntity(answer, HttpStatus.OK);
                }
            default :
                ErrorResponse error = new ErrorResponse("invalid currency pair");
                return new ResponseEntity(error, HttpStatus.OK);
        }
    }

    //TODO implement
    //@PostMapping(/api/v2/balance/)

    //TODO implement
    //@PostMapping(/api/v2/balance/{currency_pair}/)

}
