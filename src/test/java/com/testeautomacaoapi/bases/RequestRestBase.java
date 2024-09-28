package com.testeautomacaoapi.bases;

import static io.restassured.RestAssured.*;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.DOUBLE;


import com.testeautomacaoapi.GlobalParameters;
import com.testeautomacaoapi.utils.ExtentReportsUtils;
import com.testeautomacaoapi.utils.RestAssuredUtils;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class RequestRestBase {
    protected String url = GlobalParameters.URL_DEFAULT;
    protected String requestService = null;
    protected Method method = null;
    protected Object jsonBody = null;
    public Map<String, String> headers = new HashMap<String, String>();
    public Map<String, String> cookies = new HashMap<String, String>();
    public Map<String, String> queryParameters = new HashMap<String, String>();
    public Map<String, String> formParameters = new HashMap<String, String>();
    protected File file = null;
    protected String fileVarName = null;
    protected String fileType = null;

    public RequestRestBase(){
        config = RestAssuredConfig.newConfig().jsonConfig(jsonConfig().numberReturnType(DOUBLE));
        enableLoggingOfRequestAndResponseIfValidationFails();
        headers.put("content-type", "application/json");
    }

    public ValidatableResponse executeRequest() {
        Response response = RestAssuredUtils.executeRestRequest(url, requestService, method, headers, cookies, queryParameters, formParameters, jsonBody, file, fileVarName, fileType);
        ExtentReportsUtils.addRestTestInfo(url,
                requestService,
                method.toString(),
                headers,
                cookies,
                queryParameters,
                formParameters,
                jsonBody,
                file,
                fileVarName,
                fileType,
                response);

        return response.then();
    }
}