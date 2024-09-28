package com.testeautomacaoapi.requests;

import com.testeautomacaoapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class PatchUserRequest extends RequestRestBase {
    public PatchUserRequest(int idUser) {
        requestService = "/users/" + idUser;
        method = Method.PATCH;
    }
    public void setJsonBody(Object object){
        this.jsonBody = object;
    }
}
