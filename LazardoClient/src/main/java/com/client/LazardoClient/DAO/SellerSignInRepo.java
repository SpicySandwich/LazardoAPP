package com.client.LazardoClient.DAO;

import java.util.List;

import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Model.SellerProduct;
import com.client.LazardoClient.Model.SellerClientDetails;
import com.client.LazardoClient.Model.SellerHistoryTransaction;
import com.client.LazardoClient.Model.SellerTotalProductBalance;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SellerSignInRepo {
	

	
	final String SELECT_LOGIN = "SELECT client_login_id as loginId, client_username as username, client_password as password  FROM tbclientlogin cl LEFT JOIN tbclientdetails cd "
			+ "ON cd.client_id = cl.client_login_id WHERE cl.client_username = #{username} AND cl.client_password = #{password} AND cd.role = 2;";

	final String SELECT_CLIENT_DETAILS = "SELECT client_id as detailId, client_firstname as firstname, client_lastname as lastname, client_email as email, money_balance as balance, role as role "
                     + "FROM tbclientdetails WHERE client_id = #{loginId} AND role = 2 ";

	 final String SELECT_CLIENT_PRODUCT = "SELECT product_id as id, product_brand as brand, product_name as name, product_price as price, product_current_date as currentdate, "
					+ "product_expiration_date as expirationdate, product_comment as comment "
					+ "FROM tblazardoproduct "
					+ "WHERE sellerid = #{detailId} ;";
	 
	 final String SELECT_TRANSACTION_HISTORY = "SELECT  "
			 		+ "cspi.buyer_email_purchase AS buyerEmaill, "
			 		+ "cspi.buyer_purchase_item AS productName, "
			 		+ "cspi.product_price AS productPrice, "
			 		+ "cspi.transferbalance AS balanceTransfer, "
			 		+ "cspi.seller_email AS sellerEmail, "
			 		+ "cspi.paid_date AS  datePaid "
			 		+ "FROM tbclientsellerpaidinfo  cspi "
			 		+ "LEFT JOIN tbclientdetails cd "
			 		+ "ON cspi.seller_email = cd.client_email "
			 		+ "WHERE seller_email = #{email} AND cd.role = 2;";
	 
	 final String TOTAL_PRODUCT_PAID = "SELECT  "
			 		+ "SUM(cspi.product_price) AS totalPaidBalance "
			 		+ "FROM tbclientsellerpaidinfo  cspi "
			 		+ "LEFT JOIN tbclientdetails cd "
			 		+ "ON cspi.seller_email = cd.client_email "
			 		+ "WHERE seller_email = #{email} AND cd.role = 2;";
		
				@Select(SELECT_LOGIN)
			    @Results(value = {
				@Result(property = "loginId", column = "loginId"),
				@Result(property = "username", column = "username"),
				@Result(property = "password", column = "password"),
				@Result(property = "sellerDetails", column = "loginId",javaType = SellerClientDetails.class, one = @One(select = "selectClientDetail"))		
			})
			public ClientLogin sellerSignIn(@Param("username") String username, @Param("password")String password);
			
			@Select(SELECT_CLIENT_DETAILS)
			@Results(value = {
			@Result(property = "detailId", column = "detailId"),
			@Result(property = "firstname", column = "firstname"),
			@Result(property = "lastname", column = "lastname"),
			@Result(property = "email", column = "email"),
			@Result(property = "balance", column = "balance"),
			@Result(property = " role", column = " role"),
			@Result(property = "productList", column = "detailId", javaType = List.class, many = @Many(select = "selectProductList")),
			@Result(property = "transactionHistory", column = "email", javaType = List.class, many = @Many(select = "selectTransactionHistory")),
			@Result(property = "totalProductPaid", column = "email",javaType = SellerTotalProductBalance.class, one = @One(select = "selectTotalProductBalance"))
			})
			SellerClientDetails selectClientDetail();
			
			@Select(SELECT_CLIENT_PRODUCT)
			SellerProduct selectProductList();
			
			@Select(SELECT_TRANSACTION_HISTORY)
         List<SellerHistoryTransaction>   selectTransactionHistory();
			
		 @Select(TOTAL_PRODUCT_PAID)
		SellerTotalProductBalance selectTotalProductBalance();

}
