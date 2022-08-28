package com.client.LazardoClient.DTO;

import com.client.LazardoClient.Model.BuyerCartProduct;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BuyerCartProductDTO {
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String clientEmailDTO;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer cartProductIDDTO;
	
	public static BuyerCartProductDTO convertDto(BuyerCartProduct buyerCartProduct) {
		return BuyerCartProductDTO
				.builder()
				.clientEmailDTO(buyerCartProduct.getClientEmail())
				.cartProductIDDTO(buyerCartProduct.getCartProductID())
				.build();
				
	}
}
