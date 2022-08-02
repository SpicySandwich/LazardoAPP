package com.client.LazardoClient.Controller;



import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	

	@GetMapping( "/signin/{username}/{password}")
	public ClientLogin singInClient(@PathVariable String username, @PathVariable String password){
		
		return clientService.siginInClients(username,password);

     }
	
	@PostMapping("/signup")
	public ClientLogin signUpClients(@RequestBody ClientLogin clientLogin) {
		return clientService.signUpClient(clientLogin);
	}
	

}
