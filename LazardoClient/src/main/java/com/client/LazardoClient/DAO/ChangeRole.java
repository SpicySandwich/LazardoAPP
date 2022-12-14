package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.ChangeClientRole;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ChangeRole {
	
	final String CHANGE_ROLE = "UPDATE tbclientdetails  cd "
													+ "SET cd.role = #{roleChange} "
													+ "WHERE cd.client_email = #{clientEmail} ";
	
	@Update( CHANGE_ROLE)
	Integer changeClientRole(ChangeClientRole role);

}
