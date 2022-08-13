package com.client.LazardoClient.Service;

import java.util.List;

import com.client.LazardoClient.DAO.ClientDetailsChanges;
import com.client.LazardoClient.DAO.SellerDeleteUpdateProductRepo;
import com.client.LazardoClient.DAO.SellerSellProductRepo;
import com.client.LazardoClient.DAO.SellerSignInRepo;
import com.client.LazardoClient.DAO.ValidationRepo;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Model.SellerChangesProduct;
import com.client.LazardoClient.Model.SellerClientDetails;
import com.client.LazardoClient.Model.SellerProduct;
import com.client.LazardoClient.Validation.CompileValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerClientService {
	
	@Autowired
	private SellerSellProductRepo sellerSellProductRepo;
	
	@Autowired
	private SellerSignInRepo sellerShowProduct;
	
	@Autowired
	private CompileValidation compileValidation;
	
	@Autowired
	private ClientDetailsChanges clientDetailsChanges;
	
	@Autowired
	private SellerDeleteUpdateProductRepo sellerDeleteUpdateProductRepo;
	
	//Seller Signin
	  public ClientLogin siginInClientsSeller(String username, String password) {
			
			return sellerShowProduct.sellerSignIn(username,password);
		}
	
	//Seller add product to sell
	public Integer sellProduct(List<SellerProduct> sellProduct) {
		Integer saveOrNot = sellerSellProductRepo.sellerClientAddProduct(sellProduct);
		return saveOrNot;
	}
	
	//Seller update details
	public String updateSellerDetails(SellerClientDetails sellerClientDetails) {
		compileValidation.checkSellerDetails(sellerClientDetails);
		clientDetailsChanges.changesSellerDetails(sellerClientDetails);
		return  "Successfully updated  data";
	}
	
	//Seller delete sell product
	public String deleteSellerProduct(SellerChangesProduct changesProduct) {
		compileValidation.checkProductDeleteSeller(changesProduct.getProductId(), changesProduct.getSellerEmail());
		sellerDeleteUpdateProductRepo.deleteSellerProduct(changesProduct);
		return "Succefully delete product " + changesProduct.getProductId();
	}
	
  

}
