package Model;

public class Currency {
    private String inputCyrrency;
    private String outputCyrrency;
    private double amount;

    public Currency(String inputCyrrency, String outputCyrrency, double amount) {
        this.inputCyrrency = inputCyrrency;
        this.outputCyrrency = outputCyrrency;
        this.amount = amount;
    }


    //
    public int convertCurrency(String inputCur, String outputCur, int amount) {

        return 0;
    }

    //getters and setters
    public String getInputCyrrency() {
        return inputCyrrency;
    }

    public void setInputCyrrency(String inputCyrrency) {
        this.inputCyrrency = inputCyrrency;
    }

    public String getOutputCyrrency() {
        return outputCyrrency;
    }

    public void setOutputCyrrency(String outputCyrrency) {
        this.outputCyrrency = outputCyrrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
