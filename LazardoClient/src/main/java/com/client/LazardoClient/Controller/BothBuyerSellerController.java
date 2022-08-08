package com.client.LazardoClient.Controller;

import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Service.BuyerClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BothBuyerSellerController {
	
	@Autowired
	private BuyerClientService clientService;
	
//need for seller
	@GetMapping( "/signin/{username}/{password}")
	public ClientLogin singInClient(@PathVariable String username, @PathVariable String password){
		
		return clientService.siginInClientsBuyer(username,password);

     }
	
	@PostMapping("/signup")
	public ClientLogin signUpClients(@RequestBody ClientLogin clientLogin) {
		return clientService.signUpClient(clientLogin);
	}
	

}
