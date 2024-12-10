package com.broadcom.ims.azure.model.entra;

import lombok.Value;

@Value
public class EventProcessingRequest{
	Data data;
	String source;
	String type;
}