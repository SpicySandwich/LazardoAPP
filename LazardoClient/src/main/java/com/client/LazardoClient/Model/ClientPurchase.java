package com.client.LazardoClient.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientPurchase {
	
	private Integer purchaseclientid;
	private Integer purchaseproductid;
	private Product productList;

}