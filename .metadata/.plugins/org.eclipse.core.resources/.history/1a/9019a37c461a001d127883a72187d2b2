package com.client.LazardoClient.DAO;

import java.util.List;

import com.client.LazardoClient.Model.Product;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SellerSellProductRepo {
	 
		final String SELL_PRODUCT =   "<script>"
                +  "INSERT INTO  tblazardoproduct (sellerid,product_brand,product_name,product_price,product_expiration_date,product_comment) "
                +  "VALUES" 
					+   "<foreach item='parameter_item' collection='sellProduct' open='' separator=',' close=''>" 
					+  "((SELECT client_id FROM tbclientdetails WHERE client_email = #{parameter_item.clientSellerEmail}), "
					+   "#{parameter_item.brand},#{parameter_item.name},#{parameter_item.price},"
					+ "#{parameter_item.expirationdate},#{parameter_item.comment})" 
					+   "</foreach>"
					+  "</script>";
	@Insert(SELL_PRODUCT)
	public Integer sellerClientAddProduct(@Param("sellProduct") List<Product> sellProduct);

}
