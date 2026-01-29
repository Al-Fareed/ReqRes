package org.app.client;

import io.restassured.response.Response;
import org.app.models.request.LoginRequest;
import org.app.spec.RequestSpecFactory;

import static io.restassured.RestAssured.given;

public class LoginApiClient {

    public static Response login(LoginRequest request) {
        return given()
                .spec(RequestSpecFactory.defaultSpec())
                .body(request)
                .post("/api/login");
    }
}
