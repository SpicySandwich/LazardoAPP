package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.BuyerPayment;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BuyerPayProductRepo {
	
	
	final String BUYER_PAYMENT ="UPDATE tbclientdetails cd2,tbpurchasedetails pd2 "
				+ "JOIN( "
				+ "SELECT  "
				+ "cd.client_id, "
				+ "cd.money_balance - lp.product_price  AS currentbalance, "
				+ "pd.payment_status  AS ubpaidstatus, "
				+ "pd.client_purchase_id AS clientid, "
				+ "cd.client_email AS buyeremail, "
				+ "pd.purchase_id AS puchaseid, "
				+ "cd.role AS sellerrole "
				+ "FROM tbclientlogin cl  "
				+ "LEFT JOIN tbclientdetails cd ON cd.client_id = cl.client_login_id "
				+ "LEFT JOIN tbclientrole cr ON cr.client_role_id = cd.role "
				+ "LEFT JOIN tbpurchasedetails pd ON pd.client_purchase_id =cd.client_id "
				+ "LEFT JOIN tbpaymentstatus ps ON ps.status_id = pd.payment_status "
				+ "LEFT JOIN tblazardoproduct lp  ON lp.product_id = pd.client_product_purchase_id "
				+ "WHERE pd.payment_status = 1 AND cd.client_email = #{clientEmailPayment}  AND pd.purchase_id = #{productIDPurchase} AND cd.role = 1  "
				+ "GROUP BY cd.client_id "
				+ ") total "
				+ "SET cd2.money_balance =  if(total.currentbalance >= 0, total.currentbalance, cd2.money_balance), "
				+ "	pd2.payment_status = if(total.ubpaidstatus = 1, 2, pd2.payment_status) "
				+ "WHERE cd2.client_email = total.buyeremail AND cd2.role = total.sellerrole  AND pd2.client_purchase_id = clientid  AND pd2.purchase_id = total.puchaseid;";
																												
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
				+ "WHERE pd.payment_status = 2 AND cd.client_email = #{clientEmailPayment}  AND pd.purchase_id = #{productIDPurchase} "
				+ ") total "
				+ "SET cd2.money_balance = cd2.money_balance + total.price "
				+ "WHERE cd2.client_id = total.seller;";
	
	final String INSERT_TO_TRANSACTION_HISTORY = " INSERT INTO tbclientsellerpaidinfo  (buyer_email_purchase, buyer_purchase_item, product_price, transferbalance, seller_email) "
				+ " (SELECT  "
				+ "cd.client_email , "
				+ "lp.product_name , "
				+ "lp.product_price , "
				+ "lp.product_price , "
				+ "cd3.client_email "
				+ "FROM tbclientlogin cl "
				+ "LEFT JOIN tbclientdetails cd ON cd.client_id = cl.client_login_id "
				+ "LEFT JOIN tbclientrole cr ON cr.client_role_id = cd.role "
				+ "LEFT JOIN tbpurchasedetails pd ON pd.client_purchase_id =cd.client_id "
				+ "LEFT JOIN tbpaymentstatus ps ON ps.status_id = pd.payment_status "
				+ "LEFT JOIN tblazardoproduct lp ON lp.product_id = pd.client_product_purchase_id "
				+ "JOIN tbclientdetails cd3 ON cd3.client_id = lp.sellerid "
				+ "WHERE pd.payment_status = 2 AND cd.client_email = #{clientEmailPayment} AND pd.purchase_id = #{productIDPurchase}  "
				+ ");";
	
	@Update(BUYER_PAYMENT)
	public boolean singleProductPaymentBuyer(BuyerPayment payment);
	
	@Update(PAYMENT_TO_SELLER)
	public boolean singleProductPaymentToSeller(BuyerPayment payment);
	
	@Insert(INSERT_TO_TRANSACTION_HISTORY)
	public boolean insertToHistory(BuyerPayment payment);

	
}
