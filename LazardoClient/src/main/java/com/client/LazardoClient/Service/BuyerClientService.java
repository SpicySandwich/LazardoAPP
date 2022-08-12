package com.client.LazardoClient.Service;


import java.util.List;

import com.client.LazardoClient.DAO.BuyerBuyProductRepo;
import com.client.LazardoClient.DAO.BuyerPayProductRepo;
import com.client.LazardoClient.DAO.BuyerSignInRepo;
import com.client.LazardoClient.DAO.ValidationRepo;
import com.client.LazardoClient.Model.BuyerCartProduct;
import com.client.LazardoClient.Model.BuyerPayment;
import com.client.LazardoClient.Model.ClientLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerClientService {
	
	@Autowired
	private BuyerBuyProductRepo buyProductRepo;
	
	@Autowired
	private BuyerPayProductRepo payProductRepo;
	
	@Autowired
	private ValidationRepo validationRepo;
	
	@Autowired
	private BuyerSignInRepo buyerSignInRepo;
	
	//Buyer Sign in
    public ClientLogin siginInClientsBuyer(String username, String password) {
		return buyerSignInRepo.buyerSignIn(username,password);
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
			payProductRepo.insertToHistory(payment);
			return true;
		}
		return false;
	}


}