package com.client.LazardoClient.DAO;


import java.util.List;

import com.client.LazardoClient.Model.BuyerCartProduct;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BuyProductRepo {
	
	final String CART_PRODUCT =   "<script>"
			                                               +  "INSERT INTO  tbpurchasedetails (client_purchase_id, client_product_purchase_id) "
			                                               +  "VALUES" 
															+   "<foreach item='parameter_item' collection='cartProduct' open='' separator=',' close=''>" 
															+  "((SELECT client_id FROM tbclientdetails WHERE client_email = #{parameter_item.clientEmail}), "
															+   "#{parameter_item.cartProductID})" 
															+   "</foreach>"
															+  "</script>";
	
	  @Insert({CART_PRODUCT})
public Integer addProductTOClient(@Param("cartProduct") List<BuyerCartProduct>   cartProduct);

	
	

}
