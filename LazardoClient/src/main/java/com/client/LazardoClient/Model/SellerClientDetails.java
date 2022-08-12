package com.client.LazardoClient.Model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data

public class SellerClientDetails {
	
	private Integer detailId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String loginUsername;
	
	private String firstname;
	private String lastname;
	private String email;
	private Double balance;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer role = 2;
	
	private List<SellerProduct> productList;
	private List<SellerHistoryTransaction> transactionHistory;
	
	private SellerTotalProductBalance totalProductPaid;
	
	


}