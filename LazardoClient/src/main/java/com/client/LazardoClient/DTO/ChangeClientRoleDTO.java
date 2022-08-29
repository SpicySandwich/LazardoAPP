package com.client.LazardoClient.DTO;

import com.client.LazardoClient.Model.ChangeClientRole;

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
public class ChangeClientRoleDTO {
	
	private Integer roleChangeDTO;
	private String clientEmailDTO;
	
	public static ChangeClientRoleDTO convertDto(ChangeClientRole changeClientRole) {
		return ChangeClientRoleDTO.builder()
				.roleChangeDTO(changeClientRole.getRoleChange())
				.clientEmailDTO(changeClientRole.getClientEmail())
				.build();
	}

}
