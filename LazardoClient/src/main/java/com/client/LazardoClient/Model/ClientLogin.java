package com.client.LazardoClient.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientLogin {
	
	private Integer loginId;
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String reTypeUsername;
	private String password;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String reTypePassword;
	private ClientDetails clientDetails;

}


