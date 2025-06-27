package com.petproject.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;

import com.petproject.model.Currency;

public class ClassController implements CurrenyController {

    private final Currency curl;
    private String api_key ;
    public ClassController(){
        curl = new Currency();
    }

    @Override
    public boolean checkInternetConnection(String url) {
        try {
            InetAddress addr = InetAddress.getByName(url);
            if (addr.isReachable(5000)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int callFunctionCurrencyConvert(String inputCur, String outputCur, int amount) {
        System.out.println(this.api_key);
        System.out.println(inputCur);
        System.out.println(outputCur);

        String res = curl.callAPI(this.api_key, inputCur, outputCur);
        return curl.convertCurrency(res);
    }

    @Override
    public void getIOCurrencies(String inputCur, String outputCur, int amount) {
        curl.setFcurrency(inputCur);
        curl.setScurrency(outputCur);
        curl.setAmount(amount);
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }


    public String checkKey(String k ){
        if(curl.checkKeyAPI(k).equals("ok")){
            try  {
                setApi_key(k);
                saveKey(k);
            } catch (Exception e) {
                e.printStackTrace();
                return "ok";
            }
        }
        return "Invalid API key";
    }

    public String saveKey(String api_key) throws IOException{
        FileWriter wf = new FileWriter("savekey.txt");
        wf.write(api_key);
        wf.close();
        return "success";
    }

    public void readAndSetKey() throws IOException {
        File f = new File("savekey.txt");
        if (f.exists()) {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            setApi_key(bfr.readLine());
            bfr.close();
        }
    }
}