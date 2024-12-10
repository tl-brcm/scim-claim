package com.broadcom.ims.azure.model.claims;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Data{
	@JsonProperty("@odata.type")

	String odataType;
	List<ActionsItem> actions;
}