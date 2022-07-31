package com.client.LazardoClient.Service;

import java.util.List;

import com.client.LazardoClient.DAO.ClientRepo;
import com.client.LazardoClient.Model.ClientLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepo clientRepo;
	
	public ClientLogin findClient(Integer id) {
		
		return clientRepo.findClient(id);
	}
	
	public List<ClientLogin> listClient(){
		
		return clientRepo.clientList();
	}

}
