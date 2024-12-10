package com.broadcom.ims.azure.http;

import com.broadcom.ims.azure.constants.SspUrlConstant;
import com.broadcom.ims.azure.model.oauth.TokenResp;
import com.broadcom.ims.azure.model.sspuser.DisambiguateUserResp;
import com.broadcom.ims.azure.secrets.SecretHelper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserApiUniRestImpl {
    Logger logger = LoggerFactory.getLogger(UserApiUniRestImpl.class);

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String AUTHORIZATION = "Authorization";
    public static final String APPLICATION_JSON = "application/json";
    public static final String BASIC = "Basic";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_CREDENTIALS = "client_credentials";
    public static final String SCOPE = "scope";
    public static final String URN_IAM_MYSCOPES = "urn:iam:myscopes";
    public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    public HttpResponse<DisambiguateUserResp> getDisabiguateUserByUsername(String userName){
        Unirest.config().verifySsl(false);

        // first get the access token - you can optimize this by using cache.
        String accessToken = getAccessTokenForTenantAdmin();
        // then call the URL to get the user info
        if (accessToken==null){
            logger.error("Not able to retrieve access token");
            return null;
        }

        HttpResponse<DisambiguateUserResp> response = Unirest.get(SspUrlConstant.SSP_IDSTORE_USER_URL+"/"+userName)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(AUTHORIZATION, "Bearer "+accessToken)
                .asObject(DisambiguateUserResp.class);

        return response;
    }

    public String getAccessTokenForTenantAdmin(){
        HttpResponse<TokenResp> response = Unirest.post(SspUrlConstant.SSP_TOKEN_URL)
                .header(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED)
                .header(AUTHORIZATION, "Basic "+ SecretHelper.getSecret())
                .field(GRANT_TYPE, CLIENT_CREDENTIALS)
                .field(SCOPE, URN_IAM_MYSCOPES)
                .asObject(TokenResp.class);
        return response.getStatus() == 200? response.getBody().getAccess_token():null;
    }
}
