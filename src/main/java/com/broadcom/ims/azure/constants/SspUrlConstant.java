package com.broadcom.ims.azure.constants;

public class SspUrlConstant {
    public static String SSP_HOST = "https://ssp-215.demo-broadcom.com";
    private static String SSP_TOKEN_URI = "/default/oauth2/v1/token";

    private static String SSP_IDSTORE_USER_URI = "/default/idstore/v1/Users";


    public static String SSP_IDSTORE_USER_URL = SSP_HOST + SSP_IDSTORE_USER_URI;

    public static String SSP_TOKEN_URL = SSP_HOST + SSP_TOKEN_URI;
}
