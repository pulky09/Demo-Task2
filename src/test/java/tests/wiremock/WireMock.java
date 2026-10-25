package tests.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMock {

    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(8089);
        wireMockServer.start();


        //Stub for create payment
        wireMockServer.stubFor(post(urlEqualTo("/payments"))
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
            {
              "paymentId": "PAY123",
              "status": "Created",
              "amount": 100.00
            }
            """)));

        //Stub for Authorize payment
        wireMockServer.stubFor(post(urlPathMatching("/payments/[^/]+/authorize"))
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
            {
              "paymentId": "PAY123",
              "status": "Authorized",
              "amount": 100.00
            }
            """)));

        //Stub for settle payment
        wireMockServer.stubFor(post(urlPathMatching("/payments/[^/]+/settle"))
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
            {
              "paymentId": "PAY123",
              "status": "Settled",
              "amount": 100.00
            }
            """)));

        //Stub for Refund payment
        wireMockServer.stubFor(post(urlPathMatching("/payments/[^/]+/refund"))
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
            {
              "paymentId": "PAY123",
              "status": "Refunded",
              "amount": 100.00
            }
            """)));



        System.out.println("wire mock running " + wireMockServer.isRunning());

        System.out.println("WireMock running on port 8089");
        System.out.println();
    }
}