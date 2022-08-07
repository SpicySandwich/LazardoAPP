package com.client.LazardoClient.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerCartProduct {
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String clientEmail;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer cartProductID;
	
	

}
