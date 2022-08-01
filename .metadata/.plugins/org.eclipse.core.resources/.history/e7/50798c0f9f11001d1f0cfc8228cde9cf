package com.client.LazardoClient.Service;


import com.client.LazardoClient.DAO.LoginClientDataRepo;
import com.client.LazardoClient.DAO.PostClientRepo;
import com.client.LazardoClient.Model.ClientLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	
	@Autowired
	private LoginClientDataRepo clientRepo;
	
	@Autowired
	private PostClientRepo postClientRepo;
	
	public ClientLogin findClient(String username, String password) {
		
		return clientRepo.findClient(username,password);
	}
	

	public ClientLogin signUpClient(ClientLogin clientLogin) {
		postClientRepo.signInClient(clientLogin);
		return clientLogin;
	}

}
