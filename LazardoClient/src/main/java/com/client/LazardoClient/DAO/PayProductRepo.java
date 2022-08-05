package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.Payment;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayProductRepo {
	
	final String ALL_PRODUCT_PAYMENT ="UPDATE tbclientdetails "
																		+ "JOIN( "
																		+ "SELECT SUM(lp.product_price) AS totalprice, cd.money_balance AS balance "
																		+ "FROM tbclientlogin cl  "
																		+ "LEFT JOIN tbclientdetails cd ON cd.client_id = cl.client_login_id "
																		+ "LEFT JOIN tbpurchasedetails pd ON pd.client_purchase_id =cd.client_id "
																		+ "LEFT JOIN tblazardoproduct lp  ON lp.product_id = pd.client_product_purchase_id "
																		+ "GROUP BY lp.product_price "
																		+ ") total "
																		+ "SET money_balance =  total.totalprice - total.balance "
																		+ "WHERE client_email = #{clientEmailPayment}" ;
	
	final String MUTIPLE_PRODUCT_PAYMENT = "";
	
	@Insert("")
	public boolean payProductPurhcase(Payment payment);
	
	@Insert(ALL_PRODUCT_PAYMENT)
	public boolean payAllProductPurchase(Payment payment);

	
}
