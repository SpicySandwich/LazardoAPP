package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.BuyerCartProduct;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BuyerRemoveCart {
	
	final String DELETE_PURCHASE = "DELETE FROM tbpurchasedetails "
			+ "WHERE purchase_id = ( "
			+ "SELECT id FROM( "
			+ "SELECT pd.purchase_id AS id "
			+ "FROM tbpurchasedetails pd "
			+ "LEFT JOIN tbclientdetails cd "
			+ "ON cd.client_id = pd.client_purchase_id "
			+ "WHERE cd.client_email = #{clientEmail} AND pd.payment_status = 1 AND pd.purchase_id = #{cartProductID} "
			+ ") AS buyerdeletecart);";
	
	@Delete(DELETE_PURCHASE)
    Integer deleteCart(BuyerCartProduct cartProduct);

}
