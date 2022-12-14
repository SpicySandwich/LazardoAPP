package com.client.LazardoClient.Controller;

import com.client.LazardoClient.Model.AddBalance;
import com.client.LazardoClient.Model.ChangeClientRole;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Model.TransferBalance;
import com.client.LazardoClient.Service.MainClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController implements InterfaceController {
	

	
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
	
	
	@PutMapping("/addbalance")
	public String addBalance(@RequestBody AddBalance addBalance) {
		return mainClientService.addBalance(addBalance);
	}
	
	@PutMapping("/transferbalance")
		public String transferBalance(@RequestBody TransferBalance transferBalance) {
			return mainClientService.transferBalance(transferBalance);
		}
	
	@PutMapping("/role")
	public String changeRole(@RequestBody ChangeClientRole changeClientRole) {
		return mainClientService.changeClientRole(changeClientRole);
	}
	

}
