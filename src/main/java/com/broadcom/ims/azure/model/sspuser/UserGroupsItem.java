package com.broadcom.ims.azure.model.sspuser;

import lombok.Data;

@Data
public class UserGroupsItem{
	private String originId;
	private String name;
	private String dn;
	private String type;
}