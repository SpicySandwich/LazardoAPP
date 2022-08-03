package com.client.LazardoClient.DAO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BuyProductRepo {
	
	String CART_PRODUCT = "INSERT INTO  tbpurchasedetails (client_purchase_id, client_product_purchase_id) "
			                                  + "VALUES ((SELECT client_id FROM tbclientdetails WHERE client_email = #{clientEmail}), #{cartProductID});";
	
	@Insert(CART_PRODUCT)
	public Integer addProductTOClient(Integer id);
	
	

}
