package com.testeautomacaoapi.tests;

import com.testeautomacaoapi.bases.TestBase;
import com.testeautomacaoapi.jsonObjects.UserObject;
import com.testeautomacaoapi.requests.PostCreateRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class PostCreateTests extends TestBase {
    private static int id;
    @Test
    public static void criarUsuarioComSucesso() {

        //region Arrange
        String name = "Teste";
        String job = "QA";

        int statusCodeEsperado = HttpStatus.SC_CREATED;
        //endregion

        //region Act
        UserObject userObject = new UserObject(name, job);

        PostCreateRequest postCreateRequest = new PostCreateRequest();
        postCreateRequest.setJsonBody(userObject);

        ValidatableResponse response = postCreateRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        response.body("name", equalTo(name),
                "job", equalTo(job));
        //endregion

        id = Integer.valueOf(response.extract().path("id"));
    }
    public static int getId() {
        return id;
    }


    @Test
    public void criarUsuarioNomeEmBranco(){

        //region Arrange
        String name = "";
        String job = "QA";

        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        //endregion

        //region Act
        UserObject userObject = new UserObject(name, job);

        PostCreateRequest postCreateRequest = new PostCreateRequest();
        postCreateRequest.setJsonBody(userObject);

        ValidatableResponse response = postCreateRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        //endregion
    }

    @Test
    public void criarUsuarioJobValorNulo(){

        //region Arrange
        String name = "Teste";
        String job = null;

        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        //endregion

        //region Act
        UserObject userObject = new UserObject(name, job);

        PostCreateRequest postCreateRequest = new PostCreateRequest();
        postCreateRequest.setJsonBody(userObject);

        ValidatableResponse response = postCreateRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        //endregion
    }
}
