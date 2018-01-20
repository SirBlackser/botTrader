import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println(CurrencyPairs.btcToEur.toPair());
            URL url = new URL("https://www.bitstamp.net/api/v2/ticker/"+ CurrencyPairs.btcToEur.toPair() + "/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String test = "";
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println("output: " + output);
                test += output;
            }
            try {
                JSONObject jsonObject = (JSONObject) new JSONParser().parse(test);
                System.out.println(jsonObject.toString());
                Ticker ticker = new GsonBuilder().create().fromJson(jsonObject.toString(), Ticker.class);
                System.out.println("Ticker: " + ticker.toString());
            } catch (ParseException PE) {
                System.out.println("failed to parse String: " + PE.toString());
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}