package com.client.LazardoClient.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferBalance {
	
	private String senderEmail;
	private String receiverEmail;
	private Double balanceTransfer;

}
