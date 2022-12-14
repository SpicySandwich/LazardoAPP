package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.BuyerClientDetails;
import com.client.LazardoClient.Model.SellerClientDetails;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ClientDetailsChanges {
	
	final String BUYER_DETAIL_UPDATE= "UPDATE tbclientdetails  cd "
			+ "JOIN( "
			+ "SELECT "
			+ "cd2.role AS clientrole, "
			+ "cd2.client_id AS clientid, "
			+ "cd2.client_firstname AS firstname, "
			+ "cd2.client_lastname AS lastname, "
			+ "cd2. client_email AS email "
			+ "FROM tbclientlogin cl2 "
			+ "LEFT JOIN tbclientdetails cd2 "
			+ "ON cl2.client_login_id = cd2.client_id "
			+ "WHERE cl2.client_username = #{loginUsername} AND cd2.role = 1 "
			+ ") clientinfo "
			+ "SET "
			+ "cd. client_email = COALESCE(#{email},clientinfo.email), "
			+ "cd.client_firstname = COALESCE(#{firstname},clientinfo.firstname), "
			+ "cd.client_lastname = COALESCE(#{lastname},clientinfo.lastname) "
			+ "WHERE cd.client_id = clientinfo.clientid AND cd.role = clientinfo.clientrole;";
	
	final String SELLER_DETAIL_UPDATE = "UPDATE tbclientdetails  cd "
			+ "JOIN( "
			+ "SELECT "
			+ "cd2.role AS clientrole, "
			+ "cd2.client_id AS clientid, "
			+ "cd2.client_firstname AS firstname, "
			+ "cd2.client_lastname AS lastname, "
			+ "cd2. client_email AS email "
			+ "FROM tbclientlogin cl2 "
			+ "LEFT JOIN tbclientdetails cd2 "
			+ "ON cl2.client_login_id = cd2.client_id "
			+ "WHERE cl2.client_username = #{loginUsername} AND cd2.role = 2 "
			+ ") clientinfo "
			+ "SET "
			+ "cd. client_email = COALESCE(#{email},clientinfo.email), "
			+ "cd.client_firstname = COALESCE(#{firstname},clientinfo.firstname), "
			+ "cd.client_lastname = COALESCE(#{lastname},clientinfo.lastname) "
			+ "WHERE cd.client_id = clientinfo.clientid AND cd.role = clientinfo.clientrole;";
	
	@Update(BUYER_DETAIL_UPDATE )
	Integer changeBuyerDetails(BuyerClientDetails buyerClientDetails);
	
	@Update(SELLER_DETAIL_UPDATE)
	Integer changesSellerDetails(SellerClientDetails sellerClientDetails);

}
