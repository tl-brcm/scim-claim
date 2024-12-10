package com.broadcom.ims.azure.model.entra;

import lombok.Value;

@Value
public class AuthenticationContext{
	String protocol;
	ClientServicePrincipal clientServicePrincipal;
	Client client;
	String correlationId;
	ResourceServicePrincipal resourceServicePrincipal;
	User user;
}