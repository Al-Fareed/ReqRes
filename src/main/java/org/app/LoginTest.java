package org.app;

import io.restassured.response.Response;
import org.app.builder.LoginRequestBuilder;
import org.app.client.LoginApiClient;
import org.app.models.request.LoginRequest;

public class LoginTest {

    @org.testng.annotations.Test
    void shouldLoginSuccessfully() {

        LoginRequest request =
                LoginRequestBuilder.loginWith(
                        "eve.holt@reqres.in",
                        "cityslicka"
                );

        Response response = LoginApiClient.login(request);
        System.out.println(response);
    }
}
