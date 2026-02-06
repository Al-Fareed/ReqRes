package org.app.libs.store;

import io.restassured.response.Response;

public class ApiContext {
    private static final ThreadLocal<Response> RESPONSE =
            new ThreadLocal<>();

    public static void setResponse(Response response) {
        RESPONSE.remove();
        RESPONSE.set(response);
    }

    public static Response getResponse() {
        return RESPONSE.get();
    }
}
