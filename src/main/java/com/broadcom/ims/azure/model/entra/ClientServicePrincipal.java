package com.broadcom.ims.azure.model.entra;

import lombok.Value;

@Value
public class ClientServicePrincipal{
	String appDisplayName;
	String displayName;
	String appId;
	String id;
}