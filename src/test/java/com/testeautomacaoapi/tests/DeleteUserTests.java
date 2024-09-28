package com.testeautomacaoapi.tests;

import com.testeautomacaoapi.bases.TestBase;
import com.testeautomacaoapi.requests.DeleteUserRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class DeleteUserTests extends TestBase {
    @Test
    public void deletarUsuarioComSucesso(){

        //massa de dados
        PostCreateTests.criarUsuarioComSucesso();
        //endregion

        //region Arrange
        int idUser = PostCreateTests.getId();

        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;
        //endregion

        //region Act
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(idUser);
        ValidatableResponse response = deleteUserRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        //endregion
    }
    @Test
    public void deletarUsuarioComIdInexistente(){

        //region Arrange
        int idUser = 45454545;

        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;
        //endregion

        //region Act
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(idUser);
        ValidatableResponse response = deleteUserRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        //endregion
    }
}
