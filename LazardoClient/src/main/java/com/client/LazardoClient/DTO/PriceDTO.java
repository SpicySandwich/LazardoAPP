package com.client.LazardoClient.DTO;



import com.client.LazardoClient.Model.Price;

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
public class PriceDTO {
	
	private Double totalPriceDTO;
	
	public static PriceDTO convertDto(Price price) {
		return PriceDTO.builder().totalPriceDTO(price.getTotalPrice()).build();
	}

}
