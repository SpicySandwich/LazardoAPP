package com.client.LazardoClient.DAO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ValidationRepo {
	
	@Select("SELECT EXISTS( "
			+ "SELECT 1 "
			+ "FROM tbclientlogin cl   "
			+ "LEFT JOIN tbclientdetails cd ON cd.client_id = cl.client_login_id "
			+ "LEFT JOIN tbclientrole cr ON cr.client_role_id = cd.role "
			+ "LEFT JOIN tbpurchasedetails pd ON pd.client_purchase_id =cd.client_id "
			+ "LEFT JOIN tbpaymentstatus ps ON ps.status_id = pd.payment_status "
			+ "LEFT JOIN tblazardoproduct lp  ON lp.product_id = pd.client_product_purchase_id "
			+ "WHERE pd.payment_status = 1 AND cd.client_email = #{buyerEmail}  AND pd.purchase_id = #{productPurchaseID} AND cd.role = 1 );")
	boolean ifBuyerCartProductUnpaidPayment(@Param("buyerEmail")String buyerEmail, @Param("productPurchaseID") Integer productPurchaseID);
	
	@Select("SELECT EXISTS( "
			+ "SELECT 1 "
			+ "FROM tblazardoproduct lp "
			+ "WHERE lp.product_id = #{productid} );")
	boolean ifProductAvailableCart(@Param("productid") Integer productid);
	
	 @Select("SELECT EXISTS( "
	 		+ "SELECT 1 "
	 		+ "FROM tbclientdetails cd "
	 		+ "LEFT JOIN tbpurchasedetails pd "
	 		+ "ON cd.client_id = pd.client_purchase_id "
	 		+ "WHERE cd.client_email = #{clientEmail} AND pd.payment_status = 1 AND pd.purchase_id = #{cartProductID} "
	 		+ ");")
	boolean ifProductExistBuyer(@Param("cartProductID") Integer cartProductID, @Param("clientEmail") String clientEmail);
	 
	 
	 @Select("SELECT EXISTS( "
	 		+ "SELECT 1 "
	 		+ "FROM tbclientdetails cd "
	 		+ "LEFT JOIN tblazardoproduct lp "
	 		+ "ON lp.sellerid = cd.client_id "
	 		+ "LEFT JOIN tbpurchasedetails pd "
	 		+ "ON lp.product_id = pd.client_purchase_id "
	 		+ "WHERE cd.client_email = #{clientEmail} AND lp.product_id = #{cartProductID} AND pd.client_purchase_id IS NULL OR pd.client_purchase_id ='' "
	 		+ ");")
		boolean ifProductExistSeller(@Param("cartProductID") Integer cartProductID, @Param("clientEmail") String clientEmail);
	 
	 @Select("SELECT EXISTS( "
		 		+ "SELECT 1 "
		 		+ "FROM tbclientdetails cd "
		 		+ "LEFT JOIN tblazardoproduct lp "
		 		+ "ON lp.sellerid = cd.client_id "
		 		+ "LEFT JOIN tbpurchasedetails pd "
		 		+ "ON lp.product_id = pd.client_purchase_id "
		 		+ "WHERE cd.client_email = #{clientEmail} AND lp.product_id = #{cartProductID}  "
		 		+ ");")
			boolean ifProductExistSellerProductUpdate(@Param("cartProductID") Integer cartProductID, @Param("clientEmail") String clientEmail);
	 
	 @Select("SELECT EXISTS(SELECT 1 FROM tbclientdetails WHERE client_email = #{email});")
	 boolean ifEmailExist(@Param("email") String  email);
	 
	 @Select("SELECT EXISTS(SELECT 1 FROM tbclientlogin WHERE client_username = #{user});")
	 boolean ifUsernameExist(@Param("user") String user);
	 
	 @Select("SELECT cl.client_password FROM tbclientlogin cl LEFT JOIN tbclientdetails cd "
	 	       	+ "ON cd.client_id = cl.client_login_id WHERE cl.client_username = #{user} ;")
	 String checkPassword( @Param("user") String user);
	 
	 @Select("SELECT cd.role FROM tbclientlogin cl LEFT JOIN tbclientdetails cd ON cd.client_id = cl.client_login_id "
	 		      + "WHERE cl.client_username = #{username} AND cl.client_password = #{password} ;")
	 Integer checkRole(@Param("username") String username,@Param("password") String pass);
	 
	 @Delete("DELETE FROM tbclientlogin WHERE client_username = #{user};")
	 boolean deleteIfNotSIgnUp(@Param("user") String user);
	 
	


}
