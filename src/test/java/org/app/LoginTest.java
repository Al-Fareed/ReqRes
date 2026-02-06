package org.app;

import io.restassured.response.Response;
import org.app.builder.LoginRequestBuilder;
import org.app.client.LoginApiClient;
import org.app.libs.listeners.ApiTestListener;
import org.app.models.request.LoginRequest;
import org.app.models.response.LoginResponse;
import org.app.models.response.UsersResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    void shouldLoginSuccessfully() {
        LoginRequest request =
                LoginRequestBuilder.loginWith(
                        "eve.holt@reqres.in",
                        "cityslicka"
                );
        Response response = LoginApiClient.login(request);
//        LoginResponse loginResponse = response.as(LoginResponse.class);
//        System.out.println(loginResponse.getToken());
//        System.out.println(response.prettyPrint());

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("token")).isNotBlank();
    }

    @Test
    void getUsersTest() {
        Response users = LoginApiClient.getUsers();
        System.out.println(users.prettyPrint());
        List<UsersResponse> usersList = users.jsonPath().getList("", UsersResponse.class);
        for (UsersResponse res:usersList){
            System.out.println(res.getEmail()+ " -- "+res.getName());
        }
    }
}
