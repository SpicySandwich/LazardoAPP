package com.client.LazardoClient.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class ClientLoginDTO {
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer loginIdDTO;
	
	private String usernameDTO;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String reTypeUsernameDTO;
	
	
	private String passwordDTO;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String reTypePasswordDTO;
	
	private BuyerClientDetailsDTO buyerDetailsDTO;
	
	private SellerClientDetailsDTO sellerDetailsDTO;


}


