package com.client.LazardoClient.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBalance {
	
	private Double balance;
	private String clientEmail;
	

}
