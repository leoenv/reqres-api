package com.testeautomacaoapi.requests;

import com.testeautomacaoapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class PutUserRequest extends RequestRestBase {
    public PutUserRequest(int idUser) {
        requestService = "/users/" + idUser;
        method = Method.PUT;
    }
    public void setJsonBody(Object object){
        this.jsonBody = object;
    }
}
