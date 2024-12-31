package Controller;

import Model.Currency;
import View.ClassGUI;

import javax.swing.*;
import java.net.InetAddress;

public class ClassController implements CurrenyController {


    private ClassGUI gui;
    private Currency curl;
    public ClassController(){}

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
        return 0;
    }

    @Override
    public void getIOCurrencies(String inputCur, String outputCur, int amount) {
        curl = new Currency(inputCur, outputCur, amount);
        System.out.println( curl.getInputCyrrency() + " " + curl.getAmount() + " " + curl.getOutputCyrrency());
    }


}
