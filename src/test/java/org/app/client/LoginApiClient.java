package org.app.client;

import io.restassured.response.Response;
import org.app.config.BaseConfig;
import org.app.models.request.LoginRequest;
import org.app.spec.RequestSpecFactory;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginApiClient {

    public static Response login(LoginRequest request) {
        return given()
                .spec(RequestSpecFactory.defaultSpec())
                .body(request)
                .post("/api/login");
    }

    public static Response getUsers(){
        return given()
                .spec(RequestSpecFactory.goRestDefaultSpec())
                .get(BaseConfig.GO_REST_GET_USERS);
    }
}
