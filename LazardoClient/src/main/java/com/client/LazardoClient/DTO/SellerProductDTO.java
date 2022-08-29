package com.client.LazardoClient.DTO;

import java.time.LocalDateTime;

import com.client.LazardoClient.Deserializer.LocalDateTimeDeserializer;
import com.client.LazardoClient.Model.SellerProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import org.springframework.format.annotation.DateTimeFormat;

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
public class SellerProductDTO {
	@JsonProperty(access = Access.WRITE_ONLY)
	private String clientSellerEmailDTO;
	
	private Integer idDTO;
	private String brandDTO;
	private String nameDTO;
	private Double priceDTO;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, lenient = OptBoolean.FALSE)
   @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime currentdateDTO;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, lenient = OptBoolean.FALSE)
   @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime expirationdateDTO;
	private String commentDTO;
	
	public static SellerProductDTO convertDto(SellerProduct sellerProduct) {
		return SellerProductDTO.builder()
				.clientSellerEmailDTO(sellerProduct.getClientSellerEmail())
				.idDTO(sellerProduct.getId())
				.brandDTO(sellerProduct.getBrand())
				.nameDTO(sellerProduct.getName())
				.currentdateDTO(sellerProduct.getCurrentdate())
				.expirationdateDTO(sellerProduct.getExpirationdate())
				.commentDTO(sellerProduct.getComment())
				.build();
	}

}
