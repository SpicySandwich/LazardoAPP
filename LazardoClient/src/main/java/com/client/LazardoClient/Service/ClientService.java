package com.client.LazardoClient.Service;


import com.client.LazardoClient.DAO.SignInClientDataRepo;
import com.client.LazardoClient.DAO.SignUpClientRepo;
import com.client.LazardoClient.Model.ClientDetails;
import com.client.LazardoClient.Model.ClientLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	
	@Autowired
	private SignInClientDataRepo signInRepo;
	
	@Autowired
	private SignUpClientRepo signUpClientRepo;
	
	public ClientLogin siginInClients(String username, String password) {
		
		return signInRepo.singInClient(username,password);
	}
	

	public ClientLogin signUpClient(ClientLogin clientLogin) {
		
		 clientLogin.getClientDetails().setLoginUsername(clientLogin.getUsername());
	
		if(signUpClientRepo.signUpClient(clientLogin) == true) {
			signUpClientRepo.insertClientDetails(clientLogin.getClientDetails());
			return clientLogin;
		}
		return null;
	}
	


}
