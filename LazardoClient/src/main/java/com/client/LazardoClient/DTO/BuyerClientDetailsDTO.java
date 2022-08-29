package com.client.LazardoClient.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.client.LazardoClient.Model.BuyerClientDetails;
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
public class BuyerClientDetailsDTO {
	
	private Integer detailIdDTO;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String loginUsernameDTO;
	
	private String firstnameDTO;
	private String lastnameDTO;
	private String emailDTO;
	private Double balanceDTO;
	
	@Builder.Default
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer roleDTO = 1;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<BuyerCartProductDTO>  cartProductDTO;
	
	private List<BuyerClientPurchaseDTO>  clientPurchasesDTO;
	private PriceDTO productTotalPriceDTO;
	

	public static BuyerClientDetailsDTO convertDto(BuyerClientDetails buyerClientDetails) {
	return BuyerClientDetailsDTO
				.builder()
				.detailIdDTO(buyerClientDetails.getDetailId())
				.loginUsernameDTO(buyerClientDetails.getLoginUsername())
				.firstnameDTO(buyerClientDetails.getFirstname())
				.lastnameDTO(buyerClientDetails.getLastname()	)
				.emailDTO(buyerClientDetails.getEmail())
				.balanceDTO(buyerClientDetails.getBalance())
				.roleDTO(buyerClientDetails.getRole())
				.cartProductDTO(buyerClientDetails.getCartProduct().stream()
						.map(BuyerCartProductDTO::convertDto)
						.collect(Collectors.toList()))
				.clientPurchasesDTO(buyerClientDetails.getClientPurchases().stream()
						.map(BuyerClientPurchaseDTO::convertDto)
						.collect(Collectors.toList()))
				.productTotalPriceDTO(PriceDTO.convertDto(buyerClientDetails.getProductTotalPrice()))
				.build();	
	}

}
