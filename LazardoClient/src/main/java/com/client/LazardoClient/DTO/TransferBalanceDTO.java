package com.client.LazardoClient.DTO;

import com.client.LazardoClient.Model.TransferBalance;

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
public class TransferBalanceDTO {
	
	private String senderEmailDTO;
	private String receiverEmailDTO;
	private Double balanceTransferDTO;
	
	public static TransferBalanceDTO convertDto(TransferBalance transferBalance) {
		return TransferBalanceDTO.builder()
				.senderEmailDTO(transferBalance.getSenderEmail())
				.receiverEmailDTO(transferBalance.getReceiverEmail())
				.balanceTransferDTO(transferBalance.getBalanceTransfer())
				.build();
	}

}
