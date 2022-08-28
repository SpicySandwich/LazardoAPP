package com.client.LazardoClient.DTO;



import java.util.Arrays;
import java.util.List;

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
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer roleDTO = 1;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<BuyerCartProductDTO>  cartProductDTO;
	
	private List<BuyerClientPurchaseDTO>  clientPurchasesDTO;
	private PriceDTO productTotalPriceDTO;
	
	//error in process
	public static BuyerClientDetailsDTO convertDto(BuyerClientDetails buyerClientDetails) {
		BuyerClientDetailsDTO
				.builder()
				.detailIdDTO(buyerClientDetails.getDetailId())
				.loginUsernameDTO(buyerClientDetails.getLoginUsername())
				.firstnameDTO(buyerClientDetails.getFirstname())
				.lastnameDTO(buyerClientDetails.getLastname()	)
				.emailDTO(buyerClientDetails.getEmail())
				.balanceDTO(buyerClientDetails.getBalance())
				.roleDTO(buyerClientDetails.getRole())
				.cartProductDTO(List.of())
				.clientPurchasesDTO(List.of())
				.productTotalPriceDTO(buyerClientDetails.getProductTotalPrice().getTotalPrice())
				.builder();
				
				
				
				
				return new BuyerClientDetailsDTO();
				
	}

}
