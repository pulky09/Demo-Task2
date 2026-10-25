package payments.services;

import io.restassured.response.Response;
import payments.models.PaymentRequest;

public class PaymentsService {

    private final APIClient apiClient = new APIClient();

    public Response createPayment(PaymentRequest request){
        return apiClient.createBaseSpec().body(request).post("/payments");
    }

    public Response authorizePayment(String paymentId){
        return apiClient.createBaseSpec().post("payments/{id}/authorize",paymentId);
    }

    public Response settlePayment(String paymentId){
        return apiClient.createBaseSpec().post("payments/{id}/settle",paymentId);
    }

    public Response refundPayment(String paymentId){
        return apiClient.createBaseSpec().post("payments/{id}/refund",paymentId);
    }

}
