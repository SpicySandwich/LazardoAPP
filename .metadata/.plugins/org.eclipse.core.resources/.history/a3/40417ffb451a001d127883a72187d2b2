package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.Product;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SellerDeleteUpdateProductRepo {
	
	final String DELETE_PRODUCT_SELLER = "";
	
	final String UPDATE_PRODUCT_SELLER = "";
	
	@Delete(DELETE_PRODUCT_SELLER)
	public boolean deleteSellerProduct(Integer deleteID, String sellerEmail);
	
	@Update(UPDATE_PRODUCT_SELLER)
	public boolean updateSellerProduct(Product product);

}
