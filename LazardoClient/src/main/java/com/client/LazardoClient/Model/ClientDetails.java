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
public class ClientDetails {
	
	private Integer detailId;
	private String firstname;
	private String lastname;
	private String email;
	private List<ClientPurchase>  clientPurchases;

}