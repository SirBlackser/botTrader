package be.ds.poc;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v2")
public class Tester {

    int wallet = 100;
    int btcWallet = 0;

    //@RequestMapping("/ticker/{currency_pair}/")
    /*public JSONObject buyOrder() {

    }

    @RequestMapping("/buy/market/{currency_pair}/")
    public JSONObject sellOrder(@PathParam("currency_pair") String currency) {

    }*/

}
