package com.client.LazardoClient.DTO;

import com.client.LazardoClient.Model.BuyerPayment;

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
public class BuyerPaymentDTO {
	
	private String clientEmailPaymentDTO;
	private Integer productIDPurchaseDTO;
	
	public static BuyerPaymentDTO convertDto(BuyerPayment buyerPayment) {
		return BuyerPaymentDTO.builder()
				.clientEmailPaymentDTO(buyerPayment.getClientEmailPayment())
				.productIDPurchaseDTO(buyerPayment.getProductIDPurchase())
				.build();
	}

}
