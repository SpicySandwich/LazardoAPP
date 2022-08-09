package com.client.LazardoClient;



import com.client.LazardoClient.Model.BuyerClientDetails;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Model.BuyerClientPurchase;
import com.client.LazardoClient.Model.Product;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MappedTypes({BuyerClientDetails.class,ClientLogin.class,BuyerClientPurchase.class,Product.class})
@MapperScan("com.client.LazardoClient.DAO")
public class LazardoClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LazardoClientApplication.class, args);
	}

}
