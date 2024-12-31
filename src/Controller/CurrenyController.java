package Controller;

public interface CurrenyController {
    public boolean checkInternetConnection(String url);
    public int callFunctionCurrencyConvert(String inputCur, String outputCur, int amount);
    public void getIOCurrencies(String inputCur, String outputCur, int amount);

}
