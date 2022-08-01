package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.ClientDetails;
import com.client.LazardoClient.Model.ClientLogin;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

@Mapper
public interface SignUpClientRepo {
	
	@Insert("INSERT INTO tbclientlogin (client_login_id, client_username, client_password) "
			      + "VALUES ( #{loginId},#{username}, #{password} )"
			)
	@Results(value = {
			@Result(property = "loginId", column = "client_login_id"),
			@Result(property = "username", column = "client_username"),
			@Result(property = "password", column = "client_password"),
			@Result(property = "clientDetails", column = "client_login_id",javaType = ClientDetails.class, one = @One(select = "insertClientDetails"))
	})
	public Integer signInClient(ClientLogin clientLogin);
	
	@Insert("INSERT INTO tbclientdetails (client_id, client_firstname, client_lastname, client_email, money_balance) "
			      + "VALUES (("
			      + "SELECT client_login_id FROM tbclientlogin WHERE client_login_id = #{loginId}), "
			      + "#{firstname}, #{lastname}, #{email}, #{balance})")
	ClientDetails insertClientDetails();

}