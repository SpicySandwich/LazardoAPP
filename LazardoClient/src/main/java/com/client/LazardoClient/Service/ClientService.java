package com.client.LazardoClient.Service;


import java.util.List;
import java.util.stream.Stream;

import com.client.LazardoClient.DAO.BuyProductRepo;
import com.client.LazardoClient.DAO.SellerShowProduct;
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
	
	@Autowired
	private SellerShowProduct sellerShowProduct;
	
	public ClientLogin siginInClientsBuyer(String username, String password) {
		
		return signInRepo.singInClient(username,password);
	}
	
	public ClientLogin siginInClientsSeller(String username, String password) {
		
		return sellerShowProduct.singInClient(username,password);
	}
	
	public ClientLogin signUpClient(ClientLogin clientLogin) {
		
		 clientLogin.getClientDetails().setLoginUsername(clientLogin.getUsername());

		if(signUpClientRepo.signUpClient(clientLogin) == true && 
				signUpClientRepo.insertClientDetails(clientLogin.getClientDetails()) == true) {
			
			if(clientLogin.getClientDetails().getRole() == 1) {
				return siginInClientsBuyer(clientLogin.getUsername(),clientLogin.getPassword());
			}else if(clientLogin.getClientDetails().getRole() == 2) {
				return siginInClientsSeller(clientLogin.getUsername(),clientLogin.getPassword());
			}
			
			
		}
		return null;
	}
	//insert product
//	 clientLogin.getClientDetails().getCartProduct().forEach(foo -> foo.setClientEmail(clientLogin.getClientDetails().getEmail()));
//	buyProductRepo.addProductTOClient(clientLogin.getClientDetails().getCartProduct());
	


}
