package com.client.LazardoClient.DTO;

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

}
