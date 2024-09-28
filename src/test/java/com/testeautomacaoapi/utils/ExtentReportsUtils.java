package com.testeautomacaoapi.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.testeautomacaoapi.GlobalParameters;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.ITestResult;

import java.io.File;
import java.util.Map;

public class ExtentReportsUtils {
    public static ExtentReports EXTENT_REPORT = null;
    public static ExtentTest TEST;
    public static ExtentSparkReporter SPARK_REPORTER = null;
    static String reportName = GlobalParameters.REPORT_NAME + "_" + GeneralUtils.getNowDate("yyyy-MM-dd_HH-mm-ss");
    static String reportsPath = GlobalParameters.REPORT_PATH;
    static String fileName = reportName + ".html";
    static String fullReportFilePath = reportsPath + "/" + reportName + "/" + fileName;

    public static void createReport() {
        if (EXTENT_REPORT == null) {
            SPARK_REPORTER = new ExtentSparkReporter(fullReportFilePath);
            EXTENT_REPORT = new ExtentReports();
            EXTENT_REPORT.attachReporter(SPARK_REPORTER);
        }
    }
    public static void addTest(String testName, String testCategory) {
        TEST = EXTENT_REPORT.createTest(testName).assignCategory(testCategory.replace("Tests", ""));
    }
    public static void addRestTestInfo(String url,
                                       String requestService,
                                       String method,
                                       Map<String, String> headers,
                                       Map<String, String> cookies,
                                       Map<String, String> queryParameters,
                                       Map<String, String> formParameters,
                                       Object jsonBody,
                                       File file,
                                       String fileVarName,
                                       String fileType,
                                       Response response) {
        ExtentTest node = TEST.createNode("&#128640; Request - " + method + " - " + requestService);

        String allHeaders = "";
        String allCookies = "";
        String allParameters = "";
        String allResponseHeaders = "";
        String allFormParameters = "";

        for (Map.Entry<String, String> queryParameter : queryParameters.entrySet()) {
            allParameters = allParameters + "\n" + "<i>" + queryParameter.getKey() + "</i>" + " = " + queryParameter.getValue();
        }

        for (Map.Entry<String, String> formParameter : formParameters.entrySet()) {
            allFormParameters = allFormParameters + "\n" + "<i>" + formParameter.getKey() + "</i>" + " = " + formParameter.getValue();
        }

        for (Map.Entry<String, String> header : headers.entrySet()) {
            allHeaders = allHeaders + "\n" + "<i>" + header.getKey() + "</i>" + " = " + header.getValue();
        }

        for (Map.Entry<String, String> cookie : cookies.entrySet()) {
            allCookies = allCookies + "\n" + "<i>" + cookie.getKey() + "</i>" + " = " + cookie.getValue();
        }

        for (Header responseHeader : response.getHeaders().asList()) {
            allResponseHeaders = allResponseHeaders + "\n" + responseHeader.getName() + ": " + responseHeader.getValue();
        }

        node.log(Status.INFO, "<pre>" + "<b>URL: </b>" + url + "</pre>");
        node.log(Status.INFO, "<pre>" + "<b>REQUEST: </b>" + requestService + "</pre>");
        node.log(Status.INFO, "<pre>" + "<b>METHOD: </b>" + method + "</pre>");

        if (!allHeaders.equals("")) {
            node.log(Status.INFO, "<pre>" + "<b>HEADERS: </b>" + "\n" + allHeaders + "</pre>");
        }

        if (!allCookies.equals("")) {
            node.log(Status.INFO, "<pre>" + "<b>COOKIES: </b>" + "\n" + allCookies + "</pre>");
        }

        if (!allParameters.equals("")) {
            node.log(Status.INFO, "<pre>" + "<b>PARAMETERS: </b>" + "\n" + allParameters + "</pre>");
        }

        if (!allFormParameters.equals("")) {
            node.log(Status.INFO, "<pre>" + "<b>FORM PARAMETERS: </b>" + "\n" + allFormParameters + "</pre>");
        }

        if (jsonBody != null) {
            if (jsonBody instanceof String) {
                node.log(Status.INFO, "<pre>" + "<b>JSON BODY: </b>" + "\n" + jsonBody + "</pre>");
            } else {
                node.log(Status.INFO, "<pre>" + "<b>JSON BODY: </b>" + "\n" + GeneralUtils.formatJson(jsonBody) + "</pre>");
            }
        }

        if (file != null){
            node.log(Status.INFO, "<pre>" + "<b>MULTI PART: </b>" + "\n" + fileVarName + "</pre>");
            node.log(Status.INFO, "<pre>" + "<b>MULTI PART TYPE: </b>" + "\n" + fileType + "</pre>");
            node.log(Status.INFO, "<pre>" + "<b>MULTI PART FILE: </b>" + "\n" + file + "</pre>");
        }

        node.log(Status.INFO, "<pre>" + "<b>STATUS CODE: </b>" + response.statusCode() + "</pre>");
        node.log(Status.INFO, "<pre>" + "<b>RESPONSE HEADERS: </b>" + "\n" + allResponseHeaders + "</pre>");
        try {
            node.log(Status.INFO, "<pre>" + "<b>PAYLOAD: </b>" + "\n" + response.body().prettyPrint() + "</pre>");
        } catch (io.restassured.path.json.exception.JsonPathException e) {

        }
    }
    public static void addTestResult(ITestResult result) {
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                //se não for erro de asserção marca o teste como "error"
                if(!result.getThrowable().toString().contains("AssertionError")) {
                    TEST.log(Status.FAIL, "Test Result: " + Status.FAIL + "<pre>" + "Message: " + result.getThrowable().toString() + "</pre>" + "<pre>" + "Stack Trace: " + GeneralUtils.getAllStackTrace(result) + "</pre>");
                }else {
                    TEST.log(Status.FAIL, "Test Result: " + Status.FAIL + "<pre>" + "Message: " + result.getThrowable().toString() + "</pre>" + "<pre>" + "Stack Trace: " + GeneralUtils.getAllStackTrace(result) + "</pre>");
                }
                break;
            case ITestResult.SKIP:
                TEST.log(Status.SKIP, "Test Result: " + Status.SKIP + "<pre>" + "Message: " + result.getThrowable().toString() + "</pre>" + "<pre>" + "Stack Trace: " + GeneralUtils.getAllStackTrace(result) + "</pre>");
                break;
            default:
                TEST.log(Status.PASS, "Test Result: " + Status.PASS);
                break;
        }
    }
    public static void generateReport() {
        EXTENT_REPORT.flush();
    }
}