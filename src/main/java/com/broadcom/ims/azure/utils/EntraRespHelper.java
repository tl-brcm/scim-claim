package com.broadcom.ims.azure.utils;

import com.broadcom.ims.azure.model.claims.ActionsItem;
import com.broadcom.ims.azure.model.claims.Claims;
import com.broadcom.ims.azure.model.claims.Data;
import com.broadcom.ims.azure.model.claims.UserClaimResponse;
import com.broadcom.ims.azure.model.sspuser.DisambiguateUserResp;

import java.util.Arrays;

public class EntraRespHelper {
    public static UserClaimResponse toUserClaimResopnse(DisambiguateUserResp disambiguateUserResp){
        return UserClaimResponse.builder().data(
                Data.builder().odataType("microsoft.graph.onTokenIssuanceStartResponseData")
                        .actions(Arrays.asList(
                                ActionsItem.builder().odataType("microsoft.graph.tokenIssuanceStart.provideClaimsForToken")
                                        .claims(
                                                Claims.builder()
                                                        .title(disambiguateUserResp.getUserAttributes().get("title"))
                                                        .dateOfBirth("01/01/2000")
                                                        .apiVersion("1.0.0")
                                                        .build()
                                        ).build()
                        )).build()
        ).build();
    }
}
