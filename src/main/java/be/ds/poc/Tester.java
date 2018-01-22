package be.ds.poc;

import be.ds.projects.model.Answer;
import be.ds.projects.model.Transaction;
import org.apache.tomcat.jni.Time;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class Tester {

    int wallet = 100;
    int btcWallet = 0;

    @PostMapping("/api/v2/buy/market/{currency_pair}/")
    public JSONObject buyOrder(@PathParam("currency_pair") String currency, @RequestBody Transaction transaction) {
        Answer answer = new Answer(Time.now(), Time.now(), 0, transaction.getPrice(), transaction.getAmount());
        //TODO return in json
        return null;
    }

    @PostMapping("/api/v2/buy/market/{currency_pair}/")
    public JSONObject sellOrder(@PathParam("currency_pair") String currency, @RequestBody Transaction transaction) {
        Answer answer = new Answer(Time.now(), Time.now(), 1, transaction.getPrice(), transaction.getAmount());
        //TODO return in json
        return null;
    }

}
