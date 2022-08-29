package com.client.LazardoClient.DTO;


import com.client.LazardoClient.Model.BuyerClientPurchase;

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
public class BuyerClientPurchaseDTO {
	
	private Integer clientPurchaseIdDTO;
	private Integer purchaseclientidDTO;
	private Integer purchaseproductidDTO;
	private SellerProductDTO productListDTO;
	
	public static BuyerClientPurchaseDTO convertDto(BuyerClientPurchase buyerClientPurchase) {
		return BuyerClientPurchaseDTO.builder()
				.clientPurchaseIdDTO(buyerClientPurchase.getClientPurchaseId())
				.purchaseclientidDTO(buyerClientPurchase.getPurchaseclientid())
				.purchaseproductidDTO(buyerClientPurchase.getPurchaseproductid())
				.productListDTO(SellerProductDTO.convertDto(buyerClientPurchase.getProductList()))
				.build();
	}

}
