package com.testeautomacaoapi.requests;

import com.testeautomacaoapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class PostCreateRequest extends RequestRestBase {
    public PostCreateRequest() {
        requestService = "/users";
        method = Method.POST;
    }
    public void setJsonBody(Object object){
        this.jsonBody = object;
    }
}
