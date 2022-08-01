package com.client.LazardoClient.Service;


import com.client.LazardoClient.DAO.SignInClientDataRepo;
import com.client.LazardoClient.DAO.SignUpClientRepo;
import com.client.LazardoClient.Model.ClientLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	
	@Autowired
	private SignInClientDataRepo clientRepo;
	
	@Autowired
	private SignUpClientRepo postClientRepo;
	
	public ClientLogin findClient(String username, String password) {
		
		return clientRepo.findClient(username,password);
	}
	

	public ClientLogin signUpClient(ClientLogin clientLogin) {
		postClientRepo.signInClient(clientLogin);
		return clientLogin;
	}

}
