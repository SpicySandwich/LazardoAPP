package com.client.LazardoClient.Controller;

import java.util.List;

import com.client.LazardoClient.Model.Product;
import com.client.LazardoClient.Service.SellerClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerClientController  implements InterfaceController{
	
	@Autowired
	private SellerClientService sellerClientService;
	
	
	@PostMapping("/sell")
	public Integer sellProduct(@RequestBody List<Product> products) {
		
		return sellerClientService.sellProduct(products);
		
	}

}
