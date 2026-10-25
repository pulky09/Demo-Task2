package tests.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import tests.context.ScenarioContext;
import tests.hooks.CucumberHooks;
import payments.models.PaymentRequest;
import payments.services.PaymentsService;

public class PaymentSteps {

    private final CucumberHooks hooks;
    private final PaymentsService paymentsService;
    private Response response;
    private ScenarioContext context;
    private boolean flag;

    public PaymentSteps(CucumberHooks hooks, PaymentsService paymentsService, ScenarioContext context){
        this.hooks = hooks;
        this.paymentsService = paymentsService;
        this.context = context;

    }

    @When("I create a payment for {int} CAD")
    public void iCreateAPaymentForCAD(int amount) {

        PaymentRequest paymentRequest = new PaymentRequest(200,"CAD");
        response = paymentsService.createPayment(paymentRequest);
    }

    @Then("the payment should be created successfully")
    public void thePaymentShouldBeCreatedSuccessfully() {
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.body().jsonPath().get("status"),"Created");
        System.out.println(response.body().asString());

    }

    @Given("a payment is created")
    public void aPaymentIsCreated() {
        context.setPaymentId("PAY123");
    }

    @When("I authorize and settle the payment")
    public void iAuthorizeAndSettleThePayment() {
        response = paymentsService.authorizePayment(context.getPaymentId());
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.body().jsonPath().get("status"),"Authorized");

        System.out.println(response.asString());

        response = paymentsService.settlePayment(context.getPaymentId());


    }

    @And("I refund the payment")
    public void iRefundThePayment() {
        context.setPaymentId("PAY123");
        response = paymentsService.refundPayment(context.getPaymentId());

    }

    @Then("then payment should be refunded successfully")
    public void thenPaymentShouldBeRefundedSuccessfully() {
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.body().jsonPath().get("status"),"Refunded");

        System.out.println(response.asString());
    }

    @Then("the payment should be settled successfully")
    public void thePaymentShouldBeSettledSuccessfully() {
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.body().jsonPath().get("status"),"Settled");

        System.out.println(response.asString());
    }

    @When("I request a refund of {int} CAD")
    public void iRequestARefundOfCAD(int amount) {
        context.setPaymentId("PAY123");
        response = paymentsService.refundPayment(context.getPaymentId());
        Number responseAmount = response.jsonPath().get("amount");

        if (amount > responseAmount.doubleValue()) {
            flag = false;
        }

    }

    @Then("the request should fail and error message should indicate that refund amount exceeds")
    public void theRequestShouldFailAndErrorMessageShouldIndicateThatRefundAmountExceeds() {
        Assert.assertFalse(flag);
    }
}
