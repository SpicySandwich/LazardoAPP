package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.ClientDetails;
import com.client.LazardoClient.Model.ClientLogin;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SignUpClientRepo {
	
	String INSERT_SIGNIN = "INSERT INTO tbclientlogin (client_login_id, client_username, client_password) "
		                                    + "VALUES ( #{loginId},#{username}, #{password} )" ;
	
	String INSERT_CLIENT_DETAILS = "INSERT INTO tbclientdetails (client_id, client_firstname, client_lastname, client_email, money_balance) "
		                                   + "VALUES ((SELECT client_login_id FROM tbclientlogin WHERE client_login_id = #{loginId}), "
		                                   + "#{firstname}, #{lastname}, #{email}, #{balance})";
	
	@Insert(INSERT_SIGNIN)
	public boolean signInClient(ClientLogin clientLogin);
	
	@Insert(INSERT_CLIENT_DETAILS)
	public Integer insertClientDetails(ClientDetails clientDetails);

}
