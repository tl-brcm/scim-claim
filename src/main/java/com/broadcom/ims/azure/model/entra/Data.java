package com.broadcom.ims.azure.model.entra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Data{
	AuthenticationContext authenticationContext;
	String odataType;
	String tenantId;
	String customAuthenticationExtensionId;
	String authenticationEventListenerId;
}