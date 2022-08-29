package com.client.LazardoClient.DTO;

import com.client.LazardoClient.Model.ClientLogin;
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
	
	public static ClientLoginDTO convertDto(ClientLogin clientLogin) {
		return ClientLoginDTO.builder()
				.loginIdDTO(clientLogin.getLoginId())
				.usernameDTO(clientLogin.getUsername())
				.reTypeUsernameDTO(clientLogin.getReTypeUsername())
				.passwordDTO(clientLogin.getPassword())
				.reTypePasswordDTO(clientLogin.getReTypePassword())
				.buyerDetailsDTO(BuyerClientDetailsDTO.convertDto(clientLogin.getBuyerDetails()))
				.sellerDetailsDTO(SellerClientDetailsDTO.convertDto(clientLogin.getSellerDetails()))
				.build();
	}


}


