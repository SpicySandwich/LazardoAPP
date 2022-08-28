package com.client.LazardoClient.DTO;



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

}
