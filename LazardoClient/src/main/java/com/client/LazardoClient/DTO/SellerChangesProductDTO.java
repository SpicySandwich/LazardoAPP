package com.client.LazardoClient.DTO;



import com.client.LazardoClient.Model.SellerChangesProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SellerChangesProductDTO {
	
	private String sellerEmailDTO;
	private Integer productIdDTO;
	
	public static SellerChangesProductDTO convertDto(SellerChangesProduct sellerChangesProduct) {
		return SellerChangesProductDTO.builder()
				.sellerEmailDTO(sellerChangesProduct.getSellerEmail())
				.productIdDTO(sellerChangesProduct.getProductId())
				.build();
	}

}
