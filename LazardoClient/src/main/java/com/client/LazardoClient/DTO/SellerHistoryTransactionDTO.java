package com.client.LazardoClient.DTO;

import java.time.LocalDateTime;

import com.client.LazardoClient.Deserializer.LocalDateTimeDeserializer;
import com.client.LazardoClient.Model.SellerHistoryTransaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

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
public class SellerHistoryTransactionDTO {
	
	private String buyerEmaillDTO;
	private String productNameDTO;
	private Double productPriceDTO;
	private Double balanceTransferDTO;
	private String sellerEmailDTO;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, lenient = OptBoolean.FALSE)
   @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime datePaidDTO;
	
	public static SellerHistoryTransactionDTO convertdDto(SellerHistoryTransaction sellerHistoryTransaction) {
		return SellerHistoryTransactionDTO.builder()
				.buyerEmaillDTO(sellerHistoryTransaction.getBuyerEmaill())
				.productNameDTO(sellerHistoryTransaction.getProductName())
				.productPriceDTO(sellerHistoryTransaction.getProductPrice())
				.balanceTransferDTO(sellerHistoryTransaction.getBalanceTransfer())
				.sellerEmailDTO(sellerHistoryTransaction.getSellerEmail())
				.build();
	}

}
