package com.testeautomacaoapi.utils;

import com.google.gson.*;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeneralUtils {
    public static String getNowDate(String mask) {
        DateFormat dateFormat = new SimpleDateFormat(mask);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getAllStackTrace(ITestResult result) {
        String allStackTrace = "";

        for (StackTraceElement stackTrace : result.getThrowable().getStackTrace()) {
            allStackTrace = allStackTrace + "<br>" + stackTrace.toString();
        }

        return allStackTrace;
    }

    public static String formatJson(Object object){
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        return gson.toJson(object);
    }
}
