package com.client.LazardoClient.Controller;

import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Service.BuyerClientService;
import com.client.LazardoClient.Service.MainClientService;
import com.client.LazardoClient.Service.SellerClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController implements InterfaceController {
	
	@Autowired
	private BuyerClientService buyerClientService;
	
	@Autowired
	private SellerClientService sellerClientService;
	
	@Autowired
	private MainClientService mainClientService;
	
	@GetMapping( "/signin/{username}/{password}")
	public ClientLogin singInClient(@PathVariable String username, @PathVariable String password){
		 return mainClientService.siginInMainClient(username, password);

     }
	
	@PostMapping("/signup")
	public ClientLogin signUpClients(@RequestBody ClientLogin clientLogin) {
		return mainClientService.signUpClient(clientLogin);
	}
	

}
