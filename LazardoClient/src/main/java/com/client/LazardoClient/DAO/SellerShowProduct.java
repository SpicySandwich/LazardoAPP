package com.client.LazardoClient.DAO;

import java.util.List;

import com.client.LazardoClient.Model.ClientDetails;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Model.Product;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SellerShowProduct {
	
	final String SELECT_LOGIN = "SELECT client_login_id as loginId, client_username as username, client_password as password "
        	                                         + "FROM tbclientlogin WHERE client_username = #{username} AND client_password = #{password} ";

	final String SELECT_CLIENT_DETAILS = "SELECT client_id as detailId, client_firstname as firstname, client_lastname as lastname, client_email as email, money_balance as balance, role as role "
	                                                 + "FROM tbclientdetails WHERE client_id = #{loginId} AND role = 2 ";
	
	 final String SELECT_CLIENT_PRODUCT = "SELECT product_id as id, product_brand as brand, product_name as name, product_price as price, product_current_date as currentdate, "
				+ "product_expiration_date as expirationdate, product_comment as comment "
				+ "FROM tblazardoproduct "
				+ "WHERE sellerid = #{detailId} ;";
		
				@Select(SELECT_LOGIN)
			    @Results(value = {
				@Result(property = "loginId", column = "loginId"),
				@Result(property = "username", column = "username"),
				@Result(property = "password", column = "password"),
				@Result(property = "clientDetails", column = "loginId",javaType = ClientDetails.class, one = @One(select = "selectClientDetail"))		
			})
			public ClientLogin singInClient(@Param("username") String username, @Param("password")String password);
			
			@Select(SELECT_CLIENT_DETAILS)
			@Results(value = {
			@Result(property = "detailId", column = "detailId"),
			@Result(property = "firstname", column = "firstname"),
			@Result(property = "lastname", column = "lastname"),
			@Result(property = "email", column = "email"),
			@Result(property = "balance", column = "balance"),
			@Result(property = " role", column = " role"),
			@Result(property = "productList", column = "detailId", javaType = List.class, many = @Many(select = "selectProductList"))
			})
			ClientDetails selectClientDetail();
			
			@Select(SELECT_CLIENT_PRODUCT)
			Product selectProductList();

}
