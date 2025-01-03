package Controller;

import Model.Currency;

import java.net.InetAddress;

public class ClassController implements CurrenyController {

    private final Currency curl;
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
        String api_key = "13ae1abfbd0bffea2ab1491af74770a1";
        String res = curl.callAPI(api_key, inputCur, outputCur);
        return curl.convertCurrency(res);
    }

    @Override
    public void getIOCurrencies(String inputCur, String outputCur, int amount) {
        curl.setFcurrency(inputCur);
        curl.setScurrency(outputCur);
        curl.setAmount(amount);
    }


}
