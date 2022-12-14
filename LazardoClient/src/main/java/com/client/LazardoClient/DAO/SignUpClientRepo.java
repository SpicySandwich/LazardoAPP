package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.BuyerClientDetails;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Model.SellerClientDetails;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SignUpClientRepo {
	
	final String INSERT_SIGNUP = "INSERT INTO tbclientlogin (client_login_id, client_username, client_password) "
		                                    + "VALUES ( #{loginId},#{username}, #{password} ) ;" ;
	
	final String INSERT_CLIENT_DETAILS = "INSERT INTO tbclientdetails (client_id, client_firstname, client_lastname, client_email, money_balance,role) "
		                                   + "VALUES ((SELECT client_login_id FROM tbclientlogin WHERE client_username = #{loginUsername}), "
		                                   + "#{firstname}, #{lastname}, #{email}, #{balance},#{role}) ;";
	
	@Insert(INSERT_SIGNUP)
	public boolean signUpClient(ClientLogin clientLogin);
	
	@Insert(INSERT_CLIENT_DETAILS)
	public boolean  insertBuyerDetails(BuyerClientDetails clientDetails);
	
	@Insert(INSERT_CLIENT_DETAILS)
	public boolean  insertSellerDetails(SellerClientDetails clientDetails);

}
