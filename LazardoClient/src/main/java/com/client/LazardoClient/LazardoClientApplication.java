package com.client.LazardoClient;



import com.client.LazardoClient.Model.AddBalance;
import com.client.LazardoClient.Model.BuyerCartProduct;
import com.client.LazardoClient.Model.BuyerClientDetails;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Model.Price;
import com.client.LazardoClient.Model.SellerChangesProduct;
import com.client.LazardoClient.Model.SellerHistoryTransaction;
import com.client.LazardoClient.Model.BuyerClientPurchase;
import com.client.LazardoClient.Model.BuyerPayment;
import com.client.LazardoClient.Model.ChangeClientRole;
import com.client.LazardoClient.Model.SellerProduct;
import com.client.LazardoClient.Model.SellerTotalProductBalance;
import com.client.LazardoClient.Model.TransferBalance;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MappedTypes({
	BuyerClientDetails.class,
	ClientLogin.class,
	BuyerClientPurchase.class,
	SellerProduct.class,
	AddBalance.class,
	BuyerCartProduct.class,
	BuyerClientPurchase.class,
	BuyerPayment.class,
	ChangeClientRole.class,
	Price.class,
	SellerChangesProduct.class,
	SellerHistoryTransaction.class,
	SellerProduct.class,
	SellerTotalProductBalance.class,
	TransferBalance.class
	})
@MapperScan("com.client.LazardoClient.DAO")
public class LazardoClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LazardoClientApplication.class, args);
	}

}
