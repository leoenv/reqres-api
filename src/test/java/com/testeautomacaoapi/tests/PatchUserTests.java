package com.testeautomacaoapi.tests;

import com.testeautomacaoapi.bases.TestBase;
import com.testeautomacaoapi.jsonObjects.UserObject;
import com.testeautomacaoapi.requests.PatchUserRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class PatchUserTests extends TestBase {
    @Test
    public void alterarNomeUsuarioComSucesso(){

        //massa de dados
        PostCreateTests.criarUsuarioComSucesso();
        //endregion

        //region Arrange
        int idUser = PostCreateTests.getId();

        String name = "Teste Alteração";
        String job = "QA";

        int statusCodeEsperado = HttpStatus.SC_OK;
        //endregion

        //region Act
        UserObject userObject = new UserObject(name, job);

        PatchUserRequest patchUserRequest = new PatchUserRequest(idUser);
        patchUserRequest.setJsonBody(userObject);

        ValidatableResponse response = patchUserRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        response.body("name", equalTo(name));
        //endregion
    }

    @Test
    public void alterarNomeUsuarioComIdInexistente(){

        //region Arrange
        int idUser = 45454545;

        String name = "Teste Alteração";
        String job = "QA";

        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;
        //endregion

        //region Act
        UserObject userObject = new UserObject(name, job);

        PatchUserRequest patchUserRequest = new PatchUserRequest(idUser);
        patchUserRequest.setJsonBody(userObject);

        ValidatableResponse response = patchUserRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        response.body("name", equalTo(name));
        //endregion
    }
}
