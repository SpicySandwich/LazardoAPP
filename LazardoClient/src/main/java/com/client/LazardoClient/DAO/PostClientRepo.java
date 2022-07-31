package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.ClientLogin;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostClientRepo {
	
	@Insert("INSERT INTO tbclientlogin (client_username, client_password) "
			      + "VALUES ( #{username}, #{password} )")
	public Integer signInClient(ClientLogin clientLogin);

}
