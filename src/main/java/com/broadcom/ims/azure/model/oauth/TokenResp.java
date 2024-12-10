package com.broadcom.ims.azure.model.oauth;

import lombok.Data;

@Data
public class TokenResp{
	private String access_token;
	private String scope;
	private String token_type;
	private int expires_in;
}