package com.client.LazardoClient.Controller;

import java.util.List;

import com.client.LazardoClient.Model.SellerChangesProduct;
import com.client.LazardoClient.Model.SellerClientDetails;
import com.client.LazardoClient.Model.SellerProduct;
import com.client.LazardoClient.Service.SellerClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerClientController  implements InterfaceController{
	
	@Autowired
	private SellerClientService sellerClientService;
	
	
	@PostMapping("/sell")
	public String sellProduct(@RequestBody List<SellerProduct> products) {
		return sellerClientService.sellProduct(products);
		
	}
	@PutMapping("/sellerupdatedetail")
	public String updateSellerDetail(@RequestBody SellerClientDetails sellerClientDetails) {
		return sellerClientService.updateSellerDetails(sellerClientDetails);
	}
	
	@DeleteMapping("/sellerdeleteproduct")
	public String deleteSellProduct(@RequestBody SellerChangesProduct changesProduct) {
		return sellerClientService.deleteSellerProduct(changesProduct);
	}
	
	@PutMapping("/sellerupdateproduct")
	public String updateSellerProduct(@RequestBody SellerProduct sellerProduct) {
		return sellerClientService.updateSellerProduct(sellerProduct);
	}

}
