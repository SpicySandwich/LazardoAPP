package com.client.LazardoClient.DTO;


import com.client.LazardoClient.Model.SellerTotalProductBalance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SellerTotalProductBalanceDTO {
	
	private Double totalPaidBalanceDTO;
	
	public static SellerTotalProductBalanceDTO convertDto(SellerTotalProductBalance sellerTotalProductBalance) {
		return SellerTotalProductBalanceDTO.builder()
				.totalPaidBalanceDTO(sellerTotalProductBalance.getTotalPaidBalance())
				.build();
	}

}
