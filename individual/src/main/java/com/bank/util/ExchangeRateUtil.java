package com.bank.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ExchangeRateUtil {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/20690ac5c9b1e26a3d4abbe9/latest/";

    public static double getExchangeRate(String currencyCodeIn, String currencyCodeOut) throws Exception {
        URL url = new URL(API_URL + currencyCodeIn);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        JSONObject json = new JSONObject(content.toString());
        JSONObject conversionRates = json.getJSONObject("conversion_rates");
        return conversionRates.getDouble(currencyCodeOut);
    }
}