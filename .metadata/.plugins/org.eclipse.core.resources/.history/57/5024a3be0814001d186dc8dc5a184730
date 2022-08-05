package com.client.LazardoClient.Service;


import java.util.List;
import java.util.stream.Stream;

import com.client.LazardoClient.DAO.BuyProductRepo;
import com.client.LazardoClient.DAO.SignInClientDataRepo;
import com.client.LazardoClient.DAO.SignUpClientRepo;
import com.client.LazardoClient.Model.ClientLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	
	@Autowired
	private SignInClientDataRepo signInRepo;
	
	@Autowired
	private SignUpClientRepo signUpClientRepo;
	
	@Autowired
	private BuyProductRepo buyProductRepo;
	
	public ClientLogin siginInClients(String username, String password) {
		
		return signInRepo.singInClient(username,password);
	}
	
	public ClientLogin signUpClient(ClientLogin clientLogin) {
		
		
		
		 clientLogin.getClientDetails().setLoginUsername(clientLogin.getUsername());

		 clientLogin.getClientDetails().getCartProduct().forEach(foo -> foo.setClientEmail(clientLogin.getClientDetails().getEmail()));

		if(
				signUpClientRepo.signUpClient(clientLogin) == true && 
				signUpClientRepo.insertClientDetails(clientLogin.getClientDetails()) == true) {
			
			buyProductRepo.addProductTOClient(clientLogin.getClientDetails().getCartProduct());
			
			return siginInClients(clientLogin.getUsername(),clientLogin.getPassword());
		}
		return null;
	}
	


}
