package com.client.LazardoClient.Controller;


import java.util.List;
import com.client.LazardoClient.Model.ClientLogin;
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

	@GetMapping("/{id}")
	public ClientLogin clientProductList(@PathVariable Integer id){
		
		return clientService.findClient(id);

     }
	
	@GetMapping
	public List<ClientLogin> clientListData(){
		
		return clientService.listClient();
	}
	

}
