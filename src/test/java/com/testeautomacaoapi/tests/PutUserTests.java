package com.testeautomacaoapi.tests;

import com.testeautomacaoapi.bases.TestBase;
import com.testeautomacaoapi.jsonObjects.UserObject;
import com.testeautomacaoapi.requests.PutUserRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class PutUserTests extends TestBase {
    @Test
    public void alterarNomeEJobUsuarioComSucesso(){

        //massa de dados
        PostCreateTests.criarUsuarioComSucesso();
        //endregion

        //region Arrange
        int idUser = PostCreateTests.getId();

        String name = "Teste Alteração";
        String job = "QA Alteração";

        int statusCodeEsperado = HttpStatus.SC_OK;
        //endregion

        //region Act
        UserObject userObject = new UserObject(name, job);

        PutUserRequest putUserRequest = new PutUserRequest(idUser);
        putUserRequest.setJsonBody(userObject);

        ValidatableResponse response = putUserRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        response.body("name", equalTo(name),
                "job", equalTo(job));
        //endregion
    }

    @Test
    public void alterarNomeEJobUsuarioPorIdInexistente(){

        //region Arrange
        int idUser = 45454545;

        String name = "Teste Alteração";
        String job = "QA Alteração";

        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;
        //endregion

        //region Act
        UserObject userObject = new UserObject(name, job);

        PutUserRequest putUserRequest = new PutUserRequest(idUser);
        putUserRequest.setJsonBody(userObject);

        ValidatableResponse response = putUserRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        //endregion
    }
}
