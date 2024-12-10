package com.broadcom.ims.azure.model.sspuser;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class DisambiguateUserResp{
	private boolean accountLocked;
	private String userLoginIdAttrMappingName;
	private String isAuthoritativeForUserCredsLCM;
	private Object accountUnlockURL;
	private Map<String, String> userAttributes;
	private List<Object> userCredentials;
	private String userLockStatus;
	private String passwordResetURL;
	private String userId;
	private String accountStatus;
	private String userLoginId;
	private String userIdpOriginId;
	private List<UserGroupsItem> userGroups;
	private List<Object> jitMetadataGroups;
	private String isAuthoritativeForDetectingLockedState;
	private String idStoreConfigType;
	private String userDisabledStatus;
	private boolean accountDisabled;
	private List<Object> jitAssertionGroups;
	private String idStoreConfigId;
	private String idStoreConfigName;
	private String userDN;
	private String isAuthoritativeForIDLCM;
}