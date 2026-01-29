package org.app.builder;

import org.app.models.request.LoginRequest;

public class LoginRequestBuilder {
    public static LoginRequest loginWith(String email,String password){
        return new LoginRequest(email,password);
    }
}
