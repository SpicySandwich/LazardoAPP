package com.client.LazardoClient.Service;


import java.util.List;
import java.util.Optional;

import com.client.LazardoClient.DAO.BuyerBuyProductRepo;
import com.client.LazardoClient.DAO.BuyerPayProductRepo;
import com.client.LazardoClient.DAO.BuyerRemoveCart;
import com.client.LazardoClient.DAO.BuyerSignInRepo;
import com.client.LazardoClient.DAO.ClientDetailsChanges;
import com.client.LazardoClient.Model.BuyerCartProduct;
import com.client.LazardoClient.Model.BuyerClientDetails;
import com.client.LazardoClient.Model.BuyerPayment;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.ModelException.InvalidException;
import com.client.LazardoClient.Validation.CompileValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerClientService {
	
	@Autowired
	private BuyerBuyProductRepo buyProductRepo;
	
	@Autowired
	private BuyerPayProductRepo payProductRepo;
	
	@Autowired
	private BuyerSignInRepo buyerSignInRepo;
	
	@Autowired
	private CompileValidation compileValidation;
	
	@Autowired
	private ClientDetailsChanges clientDetailsChanges;
	
	@Autowired
	private BuyerRemoveCart buyerRemoveCart;
	
	//Buyer Sign in
    public ClientLogin siginInClientsBuyer(String username, String password) {
		return buyerSignInRepo.buyerSignIn(username,password);
	}
	
	//Buyer cart a product
	public String BuyerPurchaseProduct(List<BuyerCartProduct>   cartProduct) {
		compileValidation.checkBuyerPurchaseProduct(cartProduct);
	    buyProductRepo.addProductTOClient(cartProduct);
		return "Succefully purchase the product";
		
	}
	//Buyer Pay the product
	public String BuyerPayProduct(BuyerPayment payment) {
		compileValidation.checkBuyerPayingProduct(payment);
		
		return Optional.of(payProductRepo.singleProductPaymentBuyer(payment))
		.filter(Boolean::booleanValue)
		.map(pay -> {
			payProductRepo.singleProductPaymentToSeller(payment);
			payProductRepo.insertToHistory(payment);
			return "Successfully paid the product";
		}).<InvalidException>orElseThrow(() ->{
			throw new InvalidException("Unable to pay the product");
		});
		
	}
	//Buyer Update Details
	public String updateBuyerDetails(BuyerClientDetails buyerClientDetails) {
		compileValidation.checkBuyerClientDetails(buyerClientDetails);
		clientDetailsChanges.changeBuyerDetails(buyerClientDetails);
		return "Succefully updated ";
	}
	
	//Buyer delete cart purchase
	public String cartPurchaseDelete(BuyerCartProduct cartProduct) {
		compileValidation.checkProductDeleteBuyer(cartProduct.getCartProductID(),cartProduct.getClientEmail());
		buyerRemoveCart.deleteCart(cartProduct);
		return "Succefully deleted product " + cartProduct.getCartProductID();
		
	}


}
