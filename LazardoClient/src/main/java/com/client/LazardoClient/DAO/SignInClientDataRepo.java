package com.client.LazardoClient.DAO;

import java.util.List;

import com.client.LazardoClient.Model.ClientDetails;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Model.ClientPurchase;
import com.client.LazardoClient.Model.Price;
import com.client.LazardoClient.Model.Product;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SignInClientDataRepo {
	
			@Select("SELECT "
			+ "client_login_id as loginId, "
			+ "client_username as username, "
			+ "client_password as password "
			+ "FROM tbclientlogin "
			+ "WHERE client_username = #{username} AND client_password = #{password} ")
		@Results(value = {
			@Result(property = "loginId", column = "loginId"),
			@Result(property = "username", column = "username"),
			@Result(property = "password", column = "password"),
			@Result(property = "clientDetails", column = "loginId",javaType = ClientDetails.class, one = @One(select = "selectClientDetail"))		
			
		})
		public ClientLogin findClient(@Param("username") String username, @Param("password")String password);
		
		@Select("SELECT "
			+ "client_id as detailId, "
			+ "client_firstname as firstname, "
			+ "client_lastname as lastname, "
			+ "client_email as email, "
			+ "money_balance as balance "
			+ "FROM tbclientdetails "
			+ "WHERE client_id = #{loginId}")
		
		@Results(value = {
		@Result(property = "detailId", column = "detailId"),
		@Result(property = "firstname", column = "firstname"),
		@Result(property = "lastname", column = "lastname"),
		@Result(property = "email", column = "email"),
		@Result(property = "balance", column = "balance"),
		@Result(property = "clientPurchases", column = "detailId", javaType = List.class, many = @Many(select = "selectPurchaseDetail")),
		@Result(property = "productTotalPrice", column = "detailId",javaType = Price.class, one = @One(select = "selectTotalPrice"))
		})
		ClientDetails selectClientDetail();
		
		@Select("SELECT "
		+ "purchase_id as clientPurchaseId, "
		+ "client_purchase_id as purchaseclientid, "
		+ "client_product_purchase_id as purchaseproductid "
		+ "FROM tbpurchasedetails "
		+ "WHERE client_purchase_id = #{detailId}"
		)
		@Results(value = {
		@Result(property = "clientPurchaseId", column = "clientPurchaseId"),
		@Result(property = "purchaseclientid", column = "purchaseclientid"),
		@Result(property = "purchaseproductid", column = "purchaseproductid"),
		@Result(property = "productList", column = "purchaseproductid",javaType = Product.class, one = @One(select = "selectProductListCLient"))
		})
		List<ClientPurchase>  selectPurchaseDetail();
		
		
		@Select("SELECT "
		+ "product_id as id, "
		+ "product_brand as brand, "
		+ "product_name as name, "
		+ "product_price as price, "
		+ "product_current_date as currentdate, "
		+ "product_expiration_date as expirationdate, "
		+ "product_comment as comment "
		+ "FROM tblazardoproduct "
		+ "WHERE product_id = #{purchaseproductid}")
		Product selectProductListCLient();
		
		@Select ("SELECT SUM(product_price) AS totalPrice "
				+ "FROM tbpurchasedetails pd "
				+ "LEFT JOIN tblazardoproduct lp "
				+ "ON pd.client_purchase_id = lp.product_id "
				+ "WHERE pd.client_purchase_id = #{detailId}"
				)
		Price selectTotalPrice();
}