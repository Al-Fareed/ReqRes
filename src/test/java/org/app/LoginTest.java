package org.app;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.app.builder.LoginRequestBuilder;
import org.app.client.LoginApiClient;
import org.app.models.request.LoginRequest;
import org.app.models.response.UsersResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    @Epic("Req Res API login")
    @Feature("Login")
    @Story("User should be able to login")
    @Description("Verify if the user is able to login")
    @Tag("Failure")
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
    @Epic("Go rest")
    @Feature("Get Users")
    @Story("Login")
    @Description("Verify the get user api returns all the users")
    @Tag("smoke")
    void getUsersTest() {
        Response users = LoginApiClient.getUsers();
        System.out.println(users.prettyPrint());
        List<UsersResponse> usersList = users.jsonPath().getList("", UsersResponse.class);
        for (UsersResponse res:usersList){
            System.out.println(res.getEmail()+ " -- "+res.getName());
        }
    }
}
