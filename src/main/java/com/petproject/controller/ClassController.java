package com.petproject.controller;

import java.net.InetAddress;

import com.petproject.model.Currency;

public class ClassController implements CurrenyController {

    private final Currency curl;
    private String api_key = "";
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
        String res = curl.callAPI(api_key, inputCur, outputCur);
        return curl.convertCurrency(res);
    }

    @Override
    public void getIOCurrencies(String inputCur, String outputCur, int amount) {
        curl.setFcurrency(inputCur);
        curl.setScurrency(outputCur);
        curl.setAmount(amount);
    }

    public void getAPIkey(String key){
        this.api_key = key;
    }

    public int api_keyCheck(){
        return api_key == "" ? 1 : 0;
    } 


}