package com.broadcom.ims.azure.model.sspuser;

import lombok.Data;

@Data
@Deprecated
public class UserAttributes{
	private String userUniversalid;
	private String userLoginid;
	private String phoneNumber;
	private String givenName;
	private String title;
	private String familyName;
	private String email;
}