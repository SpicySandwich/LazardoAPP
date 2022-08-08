package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.BuyerPayment;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PayProductRepo {
	
	final String BUYER_PAYMENT ="UPDATE tbclientdetails cd2,tbpurchasedetails pd2 "
																	+ "JOIN( "
																	+ "SELECT  "
																	+ "cd.client_id, "
																	+ "cd.money_balance - lp.product_price  AS currentbalance, "
																	+ "pd.payment_status  AS ubpaidstatus, "
																	+ "pd.client_purchase_id AS clientid "
																	+ "FROM tbclientlogin cl  "
																	+ "LEFT JOIN tbclientdetails cd ON cd.client_id = cl.client_login_id "
																	+ "LEFT JOIN tbclientrole cr ON cr.client_role_id = cd.role "
																	+ "LEFT JOIN tbpurchasedetails pd ON pd.client_purchase_id =cd.client_id "
																	+ "LEFT JOIN tbpaymentstatus ps ON ps.status_id = pd.payment_status "
																	+ "LEFT JOIN tblazardoproduct lp  ON lp.product_id = pd.client_product_purchase_id "
																	+ "WHERE pd.payment_status = 1 AND cd.client_email = #{clientEmailPayment}  AND pd.purchase_id = #{productIDPurchase} "
																	+ "GROUP BY cd.client_id "
																	+ ") total "
																	+ "SET cd2.money_balance =  if(total.currentbalance >= 0, total.currentbalance, cd2.money_balance),  "
																	+ "	pd2.payment_status = if(total.ubpaidstatus = 1, 2, pd2.payment_status) "
																	+ "WHERE cd2.client_email = #{clientEmailPayment}  AND cd2.role = 1  AND pd2.client_purchase_id = clientid  AND pd2.purchase_id = #{productIDPurchase}; " ;
															
	final String PAYMENT_TO_SELLER = "UPDATE tbclientdetails cd2 "
																	+ "JOIN( "
																	+ "SELECT  "
																	+ "cd.client_id,  "
																	+ "cd.money_balance AS buyerbalance, "
																	+ "lp.sellerid AS seller, "
																	+ "lp.product_price AS price "
																	+ "FROM tbclientlogin cl "
																	+ "LEFT JOIN tbclientdetails cd ON cd.client_id = cl.client_login_id "
																	+ "LEFT JOIN tbclientrole cr ON cr.client_role_id = cd.role "
																	+ "LEFT JOIN tbpurchasedetails pd ON pd.client_purchase_id =cd.client_id "
																	+ "LEFT JOIN tbpaymentstatus ps ON ps.status_id = pd.payment_status "
																	+ "LEFT JOIN tblazardoproduct lp ON lp.product_id = pd.client_product_purchase_id "
																	+ "WHERE pd.payment_status = 1 AND cd.client_email = #{clientEmailPayment}  AND pd.purchase_id = #{productIDPurchase} "
																	+ ") total "
																	+ "SET cd2.money_balance = cd2.money_balance + total.price "
																	+ "WHERE cd2.client_id = total.seller;";
	
	@Insert(BUYER_PAYMENT)
	public boolean singleProductPaymentBuyer(BuyerPayment payment);
	
	@Update(PAYMENT_TO_SELLER)
	public boolean singleProductPaymentToSeller(BuyerPayment payment);

	
}
