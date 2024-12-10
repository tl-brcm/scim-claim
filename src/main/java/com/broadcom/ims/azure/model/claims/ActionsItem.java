package com.broadcom.ims.azure.model.claims;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ActionsItem{
	@JsonProperty("@odata.type")

	String odataType;
	Claims claims;
}