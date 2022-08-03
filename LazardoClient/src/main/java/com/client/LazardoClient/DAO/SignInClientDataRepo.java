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
	
	String SELECT_LOGIN = "SELECT client_login_id as loginId, client_username as username, client_password as password "
		                                	+ "FROM tbclientlogin WHERE client_username = #{username} AND client_password = #{password} ";
	
	String SELECT_CLIENT_DETAILS = "SELECT client_id as detailId, client_firstname as firstname, client_lastname as lastname, client_email as email, money_balance as balance "
                                            + "FROM tbclientdetails WHERE client_id = #{loginId}";
	
	String SELECT_CLIENT_PURCHASE = "SELECT purchase_id as clientPurchaseId, client_purchase_id as purchaseclientid, client_product_purchase_id as purchaseproductid "
                                            + "FROM tbpurchasedetails WHERE client_purchase_id = #{detailId}";
	
	String SELECT_CLIENT_PRODUCT = "SELECT product_id as id, product_brand as brand, product_name as name, product_price as price, product_current_date as currentdate, "
											+ "product_expiration_date as expirationdate, product_comment as comment "
											+ "FROM tblazardoproduct "
											+ "WHERE product_id = #{purchaseproductid}";
	
	String SELECT_TOTAL_PRODUCT_PRICE = "SELECT SUM(lp.product_price)  as totalPrice "
											+ "FROM tbclientlogin cl "
											+ "LEFT JOIN tbclientdetails cd "
											+ "ON cd.client_id = cl.client_login_id "
											+ "LEFT JOIN tbpurchasedetails pd "
											+ "ON pd.client_purchase_id =cd.client_id "
											+ "LEFT JOIN tblazardoproduct lp "
											+ "ON lp.product_id = pd.client_product_purchase_id "
											+ "WHERE cd.client_email = #{email};";
	
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
		@Result(property = "clientPurchases", column = "detailId", javaType = List.class, many = @Many(select = "selectPurchaseDetail")),
		@Result(property = "productTotalPrice", column = "email",javaType = Price.class, one = @One(select = "selectTotalPrice"))
		})
		ClientDetails selectClientDetail();
		
		@Select(SELECT_CLIENT_PURCHASE)
		@Results(value = {
		@Result(property = "clientPurchaseId", column = "clientPurchaseId"),
		@Result(property = "purchaseclientid", column = "purchaseclientid"),
		@Result(property = "purchaseproductid", column = "purchaseproductid"),
		@Result(property = "productList", column = "purchaseproductid",javaType = Product.class, one = @One(select = "selectProductListCLient"))
		})
		List<ClientPurchase>  selectPurchaseDetail();
		
		
		@Select(SELECT_CLIENT_PRODUCT)
		Product selectProductListCLient();
		
		@Select (SELECT_TOTAL_PRODUCT_PRICE )
		Price selectTotalPrice();
}
