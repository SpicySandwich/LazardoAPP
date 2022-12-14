package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.AddBalance;
import com.client.LazardoClient.Model.TransferBalance;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ClientBalance {
	
	final String ADD_BALANCE = "UPDATE tbclientdetails  cd "
				+ "JOIN ( "
				+ "SELECT cd2.money_balance AS currentbalance, cd2.client_email AS clientemail "
				+ "FROM tbclientdetails cd2 "
				+ "WHERE cd2.client_email = #{clientEmail} "
				+ ") balance "
				+ "SET cd.money_balance = balance.currentbalance + #{balance} "
				+ "WHERE cd.client_email = balance.clientemail AND balance.currentbalance  <= 0 ;";
	
	final String TRANSFER_BALANCE_PROCESS_1 = "UPDATE tbclientdetails  cd "
				+ "JOIN( "
				+ "SELECT cd2.client_email AS clientEmail, cd2.money_balance - #{balanceTransfer} AS transferbalance "
				+ "FROM tbclientlogin cl2 "
				+ "LEFT JOIN tbclientdetails cd2 "
				+ "ON cl2.client_login_id = cd2.client_id "
				+ "WHERE cd2.client_email  = #{senderEmail} "
				+ ") AS transfer "
				+ "SET cd.money_balance = if(transfer.transferbalance >= 0, transfer.transferbalance, cd.money_balance) "
				+ "WHERE cd.client_email  = transfer.clientEmail AND transfer.transferbalance >= 0 ; ";

	final String TRANSFER_BALANCE_PROCESS_2 = "UPDATE tbclientdetails  cd "
				+ "JOIN( "
				+ "SELECT cd2.client_email AS clientEmail, cd2.money_balance +  #{balanceTransfer} AS transferbalance "
				+ "FROM tbclientlogin cl2 "
				+ "LEFT JOIN tbclientdetails cd2 "
				+ "ON cl2.client_login_id = cd2.client_id "
				+ "WHERE cd2.client_email  = #{receiverEmail} "
				+ ") AS transfer2 "
				+ "SET cd.money_balance = if( transfer2.transferbalance >= 0, transfer2.transferbalance, cd.money_balance) "
				+ "WHERE cd.client_email  = transfer2.clientEmail  AND transfer2.transferbalance >= 0;";
	
	@Update(ADD_BALANCE)
	boolean addBalance( AddBalance addBalance);
	
	@Update(TRANSFER_BALANCE_PROCESS_1)
	boolean transferBalance1(TransferBalance transferBalance);
	
	@Update(TRANSFER_BALANCE_PROCESS_2)
	boolean transferBalance2(@Param("balanceTransfer") Double balanceTransfer, @Param("receiverEmail") String  receiverEmail);

}
