package com.resultier.pktrackit.utils;

public class AppConfigURL {
    public static String version = "v2.0";
    //    private static String BASE_URL = "https://project-surveyx-cammy92.c9users.io/api/" + version + "/";
//    private static String BASE_URL = "http://34.215.95.251/pk/api/" + version + "/";
    private static String BASE_URL = "http://96.72.79.146/api/" + version + "/";
    
    
    public static String LOGIN = BASE_URL + "user/login";
    public static String START_SURVEY = BASE_URL + "survey/start";
    public static String INIT_APPLICATION = BASE_URL + "init/application";
    public static String CONCLUSION_SURVEY = BASE_URL + "survey/response/conclusion";
    public static String CONCLUSION_SURVEY_2 = BASE_URL + "survey/response/conclusion2";
    public static String DAILY_SURVEY = BASE_URL + "survey/response/daily";
    public static String DAILY_SURVEY_2 = BASE_URL + "survey/response/daily2";
}