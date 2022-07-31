package com.client.LazardoClient.Controller;

import java.util.List;
import java.util.Optional;

import com.client.LazardoClient.DAO.ClientRepo;
import com.client.LazardoClient.Model.ClientDetails;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Model.Product;
import com.client.LazardoClient.Service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
//	@GetMapping("/{id}")
//	public List<Product> clientProductList(@PathVariable Integer id){
//		
//		Optional<ClientLogin> clientlogin = Optional.of(clientRepo.findClient(id));
//		
//		if (clientlogin.isPresent()) {
//			
//			return clientlogin.get().getClientDetails().getClientPurchases().getProductList();
//		}
//		return null;
//	}
	
	@GetMapping("/{id}")
	public ClientLogin clientProductList(@PathVariable Integer id){
		
		return clientService.findClient(id);

     }
	
	@GetMapping
	public List<ClientLogin> clientListData(){
		
		return clientService.listClient();
	}
	

}