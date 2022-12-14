package com.client.LazardoClient.Controller;

import java.util.List;

import com.client.LazardoClient.Model.BuyerCartProduct;
import com.client.LazardoClient.Model.BuyerClientDetails;
import com.client.LazardoClient.Model.BuyerPayment;
import com.client.LazardoClient.Service.BuyerClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyerClientController implements InterfaceController{
	
	@Autowired
	private BuyerClientService buyerClientService;
	

	@PostMapping("/cartproduct")
	public String cartProduct(@RequestBody List<BuyerCartProduct>  cartProduct) {
		return buyerClientService.BuyerPurchaseProduct(cartProduct);
	}
	
	@PutMapping("/payproduct")
	public String payProduct(@RequestBody BuyerPayment payment) {
	return	buyerClientService.BuyerPayProduct(payment);
	}
	
	@PutMapping("/buyerupdatedetails")
	public String updateBuyerDetails(@RequestBody BuyerClientDetails buyerClientDetails) {
		return buyerClientService.updateBuyerDetails(buyerClientDetails);
	}
	
	@DeleteMapping("/cartdeletepurchase")
	public String deleteCartPurchASE(@RequestBody BuyerCartProduct buyerCartProduct) {
		return buyerClientService.cartPurchaseDelete(buyerCartProduct);
	}
	

}
