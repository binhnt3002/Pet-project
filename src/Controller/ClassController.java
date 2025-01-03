package Controller;

import Model.Currency;
import View.ClassGUI;

import javax.swing.*;
import java.net.InetAddress;

public class ClassController implements CurrenyController {

    private final String api_key = "your_api_key";
    private ClassGUI gui;
    private Currency curl;
    public ClassController(){
       curl = new Currency();
    }

    public void setGui(ClassGUI gui) {
        this.gui = gui;
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
        String res = curl.callAPI(api_key, inputCur, outputCur, amount);
        return curl.convertCurrency(res);
    }

    @Override
    public void getIOCurrencies(String inputCur, String outputCur, int amount) {
        curl.setFcurrency(inputCur);
        curl.setScurrency(outputCur);
        curl.setAmount(amount);
    }


}
