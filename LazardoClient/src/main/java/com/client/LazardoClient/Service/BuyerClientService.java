package com.client.LazardoClient.Service;


import java.util.List;

import com.client.LazardoClient.DAO.BuyProductRepo;
import com.client.LazardoClient.DAO.PayProductRepo;
import com.client.LazardoClient.DAO.SignInClientDataRepo;
import com.client.LazardoClient.DAO.SignUpClientRepo;
import com.client.LazardoClient.DAO.ValidationRepo;
import com.client.LazardoClient.Model.BuyerCartProduct;
import com.client.LazardoClient.Model.BuyerPayment;
import com.client.LazardoClient.Model.ClientLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerClientService {
	
	@Autowired
	private SignInClientDataRepo signInRepo;
	
	@Autowired
	private SignUpClientRepo signUpClientRepo;
	
	@Autowired
	private BuyProductRepo buyProductRepo;
	
	@Autowired
	private SellerClientService sellerClientService;
	
	@Autowired
	private PayProductRepo payProductRepo;
	
	@Autowired
	private ValidationRepo validationRepo;
	
	
	public ClientLogin siginInClientsBuyer(String username, String password) {
		
		return signInRepo.singInClient(username,password);
	}
	
	public ClientLogin signUpClient(ClientLogin clientLogin) {
		
		 clientLogin.getClientDetails().setLoginUsername(clientLogin.getUsername());

		if(signUpClientRepo.signUpClient(clientLogin) == true && 
				signUpClientRepo.insertClientDetails(clientLogin.getClientDetails()) == true) {
			
			//Role 1  Buyer, 2 Seller
			if(clientLogin.getClientDetails().getRole() == 1) {
				return siginInClientsBuyer(clientLogin.getUsername(),clientLogin.getPassword());
			}else if(clientLogin.getClientDetails().getRole() == 2) {
				return sellerClientService.siginInClientsSeller(clientLogin.getUsername(),clientLogin.getPassword());
			}
			
			
		}
		return null;
	}

	//Buyer cart a product
	public Integer BuyerPurchaseProduct(List<BuyerCartProduct>   cartProduct) {
		
	//	 clientLogin.getClientDetails().getCartProduct().forEach(foo -> foo.setClientEmail(clientLogin.getClientDetails().getEmail()));
	Integer saveOrNot =	buyProductRepo.addProductTOClient(cartProduct);
		return saveOrNot;
		
	}
	//Buyer Pay the product
	public boolean BuyerPayProduct(BuyerPayment payment) {
		
		if (payProductRepo.singleProductPaymentBuyer(payment) == true) {
			payProductRepo.singleProductPaymentToSeller(payment);
			return true;
		}
		return false;
	}


}
