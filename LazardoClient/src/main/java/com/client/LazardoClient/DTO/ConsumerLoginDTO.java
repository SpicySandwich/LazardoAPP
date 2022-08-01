package com.client.LazardoClient.DTO;

import com.client.LazardoClient.Model.ClientDetails;
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
@ToString
@Builder
public class ConsumerLoginDTO {
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer consumerLoginId;
	
	private String consumerUsername;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String reTypeUsername;
	
	private String consumerPassword;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String reTypePassword;
	
	private ClientDetails consumerClientDetails;

}