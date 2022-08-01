package com.client.LazardoClient.Model;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDetails {
	
	private Integer detailId;
	private String firstname;
	private String lastname;
	private String email;
	private Double balance;
	private List<ClientPurchase>  clientPurchases;
	private Price productTotalPrice;

}
