package com.client.LazardoClient.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuyerPayment {
	
	private String clientEmailPayment;
	private Integer productIDPurchase;

}
