package com.broadcom.ims.azure.secrets;

import java.util.Base64;

public class SecretHelper {
    public static String getSecret(){
        // todo - secret need to be encoded somwhere
        return Base64.getEncoder().encodeToString("clientid:clientsecret".getBytes());
    }
}
