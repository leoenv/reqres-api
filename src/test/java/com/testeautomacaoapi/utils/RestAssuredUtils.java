package com.testeautomacaoapi.utils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.net.URI;
import java.util.Map;

public class RestAssuredUtils {
    public static Response executeRestRequest(String url,
                                              String requestService,
                                              Method method,
                                              Map<String,String> headers,
                                              Map<String,String> cookies,
                                              Map<String,String> queryParameters,
                                              Map<String,String> formParameters,
                                              Object jsonBody,
                                              File file,
                                              String fileVarName,
                                              String fileType){

        RequestSpecification requestSpecification = RestAssured.given();

        for (Map.Entry<String, String> header : headers.entrySet()){
            requestSpecification.headers(header.getKey(), header.getValue());
        }

        for (Map.Entry<String, String> cookie : cookies.entrySet()){
            requestSpecification.cookies(cookie.getKey(), cookie.getValue());
        }

        for (Map.Entry<String, String> parameter : queryParameters.entrySet()){
            requestSpecification.queryParams(parameter.getKey(), parameter.getValue());
        }

        for (Map.Entry<String, String> parameter : formParameters.entrySet()){
            requestSpecification.formParam(parameter.getKey(), parameter.getValue());
        }

        if(jsonBody !=null){
            requestSpecification.body(jsonBody);
        }

        if(file !=null){
            requestSpecification.multiPart(fileVarName, file, fileType);
        }

        return requestSpecification.request(method, URI.create(url+requestService));
    }
}