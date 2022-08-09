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
	 
	 @Select("SELECT cd.role FROM tbclientlogin cl LEFT JOIN tbclientdetails cd ON cd.client_id = cl.client_login_id "
	 		      + "WHERE cl.client_username = #{username} AND cl.client_password = #{password} ;")
	 Integer checkRole(@Param("username") String username,@Param("password") String pass);
	


}
