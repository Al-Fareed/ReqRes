package org.app;

import io.restassured.response.Response;
import org.app.builder.LoginRequestBuilder;
import org.app.client.LoginApiClient;
import org.app.models.request.LoginRequest;
import org.app.models.response.LoginResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {

    @Test
    void shouldLoginSuccessfully() {

        LoginRequest request =
                LoginRequestBuilder.loginWith(
                        "eve.holt@reqres.in",
                        "cityslicka"
                );

        Response response = LoginApiClient.login(request);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(loginResponse.getToken());

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("token")).isNotBlank();
    }
}
