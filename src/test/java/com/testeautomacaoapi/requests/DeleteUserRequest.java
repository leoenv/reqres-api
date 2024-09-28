package com.testeautomacaoapi.requests;

import com.testeautomacaoapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class DeleteUserRequest extends RequestRestBase {
    public DeleteUserRequest(int idUser) {
        requestService = "/users/" + idUser;
        method = Method.DELETE;
    }
}
