package com.client.LazardoClient.Service;

import java.util.List;

import com.client.LazardoClient.DAO.SellerSellProductRepo;
import com.client.LazardoClient.DAO.SellerShowProduct;
import com.client.LazardoClient.DAO.ValidationRepo;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerClientService {
	
	@Autowired
	private SellerShowProduct sellerShowProduct;
	
	@Autowired
	private SellerSellProductRepo sellerSellProductRepo;
	
	@Autowired
	private ValidationRepo validationRepo;
	
	
	public ClientLogin siginInClientsSeller(String username, String password) {
		
		return sellerShowProduct.singInClient(username,password);
	}
	
	//Seller add product to sell
	public Integer sellProduct(List<Product> sellProduct) {
		
		Integer saveOrNot = sellerSellProductRepo.sellerClientAddProduct(sellProduct);
		return saveOrNot;
	}

}
