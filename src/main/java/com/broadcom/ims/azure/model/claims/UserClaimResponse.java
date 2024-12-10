package com.broadcom.ims.azure.model.claims;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserClaimResponse{
	Data data;
}