package com.testeautomacaoapi.requests;

import com.testeautomacaoapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetSingleUserRequest extends RequestRestBase {
    public GetSingleUserRequest(int idUsuario) {
        requestService = "/users/" + idUsuario;
        method = Method.GET;
    }
}
