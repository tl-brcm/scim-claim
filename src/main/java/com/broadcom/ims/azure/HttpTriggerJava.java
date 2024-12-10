package com.broadcom.ims.azure;

import java.util.*;


import com.broadcom.ims.azure.http.UserApiUniRestImpl;
import com.broadcom.ims.azure.model.entra.EventProcessingRequest;
import com.broadcom.ims.azure.utils.EntraRespHelper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerJava {
    private static final Logger logger = LoggerFactory.getLogger(HttpTriggerJava.class);

    /**
     * This function listens at endpoint "/api/HttpTriggerJava". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTriggerJava
     * 2. curl {your host}/api/HttpTriggerJava?name=HTTP%20Query
     */
    @FunctionName("HttpTriggerJava")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.FUNCTION)
            HttpRequestMessage<Optional<EventProcessingRequest>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        try{
            Optional<EventProcessingRequest> bodyOption = request.getBody();
            if (!bodyOption.isPresent()) {
                logger.error("Empty Json body is not accepted");
                return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                        .body("Invalid request: JSON body is missing")
                        .build();
            }

            val body = bodyOption.get();
            logger.debug("Received request: " + body);

            String userName = body.getData().getAuthenticationContext().getUser().getUserPrincipalName();
            if (userName==null){
                logger.error("Error in parsing user name from request body {}", body);
                return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                        .body("Error in parsing user name from request body")
                        .build();
            }


            // todo handle query check here
            UserApiUniRestImpl userApiUniRest = new UserApiUniRestImpl();
            val scimUserResp = userApiUniRest.getDisabiguateUserByUsername(userName);
            if (scimUserResp.getStatus()!= kong.unirest.HttpStatus.OK){
                logger.error("Not able to get a response from SSP. Http status code {}", scimUserResp.getStatus());
                return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(scimUserResp.getBody()).build();
            }
            if (scimUserResp.getBody().getUserAttributes()==null){
                logger.warn("User {} attribute is not found", userName);
                return request.createResponseBuilder(HttpStatus.NOT_FOUND).build();
            }

            ObjectMapper objectMapper = new ObjectMapper();
//            Unirest.config().setObjectMapper(new JacksonObjectMapper(objectMapper));
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Ignore null fields
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); // Ignore empty fields
            val userClaimResponse = EntraRespHelper.toUserClaimResopnse(scimUserResp.getBody());

            return request.createResponseBuilder(HttpStatus.OK)
                       .header("Content-Type", "application/json")
                    .body(objectMapper.writeValueAsString(userClaimResponse)).build();
        } catch (Exception e){
            context.getLogger().info("Error processing request." + e.getLocalizedMessage());
        }
        return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unhandled exception. Please check log").build();
    }
}
