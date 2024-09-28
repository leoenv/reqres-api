package com.testeautomacaoapi.tests;

import com.testeautomacaoapi.bases.TestBase;
import com.testeautomacaoapi.requests.GetSingleUserRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetSingleUserTests extends TestBase {
    @Test
    public void buscarUsuarioPorIdComSucesso(){

        //massa de dados
        PostCreateTests.criarUsuarioComSucesso();
        //endregion

        //region Arrange
        int idUsuario = PostCreateTests.getId();

        int statusCodeEsperado = HttpStatus.SC_OK;
        //endregion

        //region Act
        GetSingleUserRequest getSingleUserRequest = new GetSingleUserRequest(idUsuario);
        ValidatableResponse response = getSingleUserRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        response.body("id", equalTo(idUsuario));
        //endregion
    }

    @Test
    public void buscarUsuarioPorIdInexistente(){

        //region Arrange
        int idUsuario = 2234;

        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;
        //endregion

        //region Act
        GetSingleUserRequest getSingleUserRequest = new GetSingleUserRequest(idUsuario);
        ValidatableResponse response = getSingleUserRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        //endregion
    }
}
