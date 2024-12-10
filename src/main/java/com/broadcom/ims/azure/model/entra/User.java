package com.broadcom.ims.azure.model.entra;

import lombok.Value;

@Value
public class User{
	String preferredLanguage;
	String displayName;
	String surname;
	String givenName;
	String createdDateTime;
	String id;
	String userType;
	String userPrincipalName;
}