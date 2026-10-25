package payments.models;

public class PaymentRequest {

    private double amount;
    private String currency;

    public PaymentRequest(double amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    }

