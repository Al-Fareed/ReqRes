package org.app.client;

import io.restassured.response.Response;
import org.app.config.BaseConfig;
import org.app.libs.store.ApiContext;
import org.app.models.request.LoginRequest;
import org.app.spec.RequestSpecFactory;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginApiClient {

    public static Response login(LoginRequest request) {
        Response response = given()
                .spec(RequestSpecFactory.defaultSpec())
                .body(request)
                .post("/api/login");
        ApiContext.setResponse(response);
        return response;
    }

    public static Response getUsers() {
        Response response = given()
                .spec(RequestSpecFactory.goRestDefaultSpec())
                .get(BaseConfig.GO_REST_GET_USERS);
        ApiContext.setResponse(response);
        return response;
    }
}
