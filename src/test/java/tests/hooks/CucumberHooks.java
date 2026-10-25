package tests.hooks;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class CucumberHooks {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void beforeAll(){/*
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo("/api/users")).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("""
                                    {
                                      "id": 1,
                                      "name": "John"
                                    }
                                    """)));
        System.out.println("Server started successfully"); */
    }

    @AfterAll
    public static void afterAll(){
        //wireMockServer.stop();
    }
}
