package payments.models;

public class RefundRequest {

    private double amount;

    public RefundRequest(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return  amount;
    }
}
