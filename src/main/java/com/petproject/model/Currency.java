package com.petproject.model;


import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Currency {

    private String fcurrency, scurrency;
    private int amount;

    public Currency() {}


    public void setFcurrency(String fcurrency) {
        this.fcurrency = fcurrency;
    }

    public void setScurrency(String scurrency) {
        this.scurrency = scurrency;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String callAPI(String key , String fcurrency, String scurrency){ {
        try{
            URL url = new URL("https://api.exchangeratesapi.io/v1/latest?access_key="+key+"&symbols="+fcurrency+","+scurrency );
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int resCode = con.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in  = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }else {
                System.out.println("Error Code: " + resCode);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    return "null";
    }

    public int convertCurrency(String res) {
        JSONObject obj = new JSONObject(res);
        JSONObject rates = obj.getJSONObject("rates");        
        double rate = rates.getDouble(scurrency);
        double result = amount * rate;
        System.out.println(amount + " " + fcurrency + " is equal to " + (int)result + " " + scurrency);
        return (int) result;
    }

    public String checkKeyAPI(String key){
        String res = callAPI(key, "USD", "EUR");
        if (res.equals("null")) {
            return "Invalid API key";
        }
        return "ok";
    }

}