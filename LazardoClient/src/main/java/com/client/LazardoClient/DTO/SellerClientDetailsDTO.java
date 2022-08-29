package com.client.LazardoClient.DTO;



import java.util.List;
import java.util.stream.Collectors;

import com.client.LazardoClient.Model.SellerClientDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class SellerClientDetailsDTO {
	
	private Integer detailIdDTO;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String loginUsernameDTO;
	
	private String firstnameDTO;
	private String lastnameDTO;
	private String emailDTO;
	private Double balanceDTO;
	
	@Builder.Default
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer roleDTO = 2;
	
	private List<SellerProductDTO> productListDTO;
	private List<SellerHistoryTransactionDTO> transactionHistoryDTO;
	
	private SellerTotalProductBalanceDTO totalProductPaidDTO;
	
	public static SellerClientDetailsDTO convertDto(SellerClientDetails sellerClientDetails) {
		return SellerClientDetailsDTO.builder()
				.loginUsernameDTO(sellerClientDetails.getLoginUsername())
				.firstnameDTO(sellerClientDetails.getFirstname())
				.lastnameDTO(sellerClientDetails.getLastname())
				.emailDTO(sellerClientDetails.getEmail())
				.balanceDTO(sellerClientDetails.getBalance())
				.roleDTO(sellerClientDetails.getRole())
				.productListDTO(sellerClientDetails.getProductList().stream()
						.map(SellerProductDTO::convertDto)
						.collect(Collectors.toList()))
				.transactionHistoryDTO(sellerClientDetails.getTransactionHistory().stream()
						.map(SellerHistoryTransactionDTO::convertdDto)
						.collect(Collectors.toList()))
				.totalProductPaidDTO(SellerTotalProductBalanceDTO.convertDto(sellerClientDetails.getTotalProductPaid()))
				.build();
				
	}


}
