package com.client.LazardoClient.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class ConsumerPurchaseDTO {
	
	private Integer consumerClientPurchaseId;
	private Integer consumerPurchaseclientid;
	private Integer consumerPurchaseproductid;
	private ConsumerProductDTO consumerConsumerProductDTO;

}