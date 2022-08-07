package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.BuyerPayment;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PayProductRepo {
	
	final String ALL_PRODUCT_PAYMENT ="UPDATE tbclientdetails cd2,tbpurchasedetails pd2 "
																		+ "JOIN( "
																		+ "SELECT  cd.client_id,  "
																		+ "		cd.money_balance - SUM(lp.product_price)  AS currentbalance,  "
																		+ "		pd.payment_status  AS ubpaidstatus, "
																		+ "		pd.client_purchase_id AS clientid "
																		+ "FROM tbclientlogin cl  "
																		+ "LEFT JOIN tbclientdetails cd ON cd.client_id = cl.client_login_id "
																		+ "LEFT JOIN tbclientrole cr ON cr.client_role_id = cd.role "
																		+ "LEFT JOIN tbpurchasedetails pd ON pd.client_purchase_id =cd.client_id "
																		+ "LEFT JOIN tbpaymentstatus ps ON ps.status_id = pd.payment_status "
																		+ "LEFT JOIN tblazardoproduct lp  ON lp.product_id = pd.client_product_purchase_id "
																		+ "WHERE ps.status_option = `UNPAID` AND cd.client_email = #{clientEmailPayment} AND cr.client_role_type = `BUYER`"
																		+ "GROUP BY cd.client_id"
																		+ ") total "
																		+ "SET cd2.money_balance =  if(total.currentbalance >= 0, total.currentbalance, cd2.money_balance),  "
																		+ "	pd2.payment_status = if(total.ubpaidstatus = 1, 2, pd2.payment_status) "
																		+ "WHERE cd2.client_email = #{clientEmailPayment} AND cd2.role = 1  AND pd2.client_purchase_id = clientid " ;
	
	final String SINGLE_PRODUCT_PAYMENT = "";
	
	@Insert("")
	public boolean singleProductPayment(BuyerPayment payment);
	
	@Update(ALL_PRODUCT_PAYMENT)
	public boolean payAllProductPayment(BuyerPayment payment);

	
}
