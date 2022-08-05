package com.client.LazardoClient.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ValidationRepo {
	
	 @Select("SELECT EXISTS(SELECT 1 FROM tblazardoproduct WHERE product_id = #{id})")
	boolean ifProductExist(@Param("id") Integer id);
	 
	 @Select("SELECT EXISTS(SELECT 1 FROM tbclientdetails WHERE client_email = #{email})")
	 boolean ifEmailExist(@Param("email") String  email);
	 
	 @Select("SELECT EXISTS(SELECT 1 FROM tbclientlogin WHERE client_username = #{user})")
	 boolean ifUsernameExist(@Param("user") String user);
	


}
