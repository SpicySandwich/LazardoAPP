package com.client.LazardoClient.Controller;

import com.client.LazardoClient.Service.SellerClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerClientController  implements InterfaceController{
	
	@Autowired
	private SellerClientService sellerClientService;

}
