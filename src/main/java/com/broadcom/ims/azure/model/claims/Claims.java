package com.broadcom.ims.azure.model.claims;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Claims{
	String apiVersion;
	String dateOfBirth;
	String title;
	List<String> customRoles;
}