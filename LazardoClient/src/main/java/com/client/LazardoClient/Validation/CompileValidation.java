package com.client.LazardoClient.Validation;

import com.client.LazardoClient.Model.AddBalance;
import com.client.LazardoClient.Model.BuyerClientDetails;
import com.client.LazardoClient.Model.ChangeClientRole;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.Model.SellerClientDetails;
import com.client.LazardoClient.Model.TransferBalance;
import com.client.LazardoClient.ModelException.InvalidException;
import com.client.LazardoClient.ModelException.NotNullException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompileValidation {
	
		@Autowired
		private ClientInputValidation clientInputValidation;
		
		@Autowired
		private ClientRepoValidation clientRepoValidation;
		
		
	
	  public ClientLogin signUpValidation(ClientLogin clientLogin) {
		  
		  if(clientLogin.getUsername().equals(clientInputValidation.checkUsernameNotNullSignUp(clientLogin.getReTypeUsername())) == false)
	             throw new InvalidException("Username do not match");
		  clientRepoValidation.usernameValidationSignUp(clientLogin.getReTypeUsername());
		  if (clientLogin.getPassword().equals(clientInputValidation.checkPasswordFormatSignUp(clientLogin.getReTypePassword())) == false)
			  throw new InvalidException("Password do not match");
			  return clientLogin;
				}
	  
		public BuyerClientDetails checkBuyerDetails(BuyerClientDetails buyerClientDetails) {
			
			
			if (buyerClientDetails.getFirstname().isEmpty() ||
				buyerClientDetails.getLastname().isEmpty() || 
				clientInputValidation.checkEmailFormatSignUp(buyerClientDetails.getEmail())
					) throw new NotNullException("Please fill up all the field");	
			return buyerClientDetails;
		
			}
	
		public SellerClientDetails checkSellerDetails(SellerClientDetails sellerClientDetails) {
			
			if (sellerClientDetails.getFirstname().isEmpty() ||
				sellerClientDetails.getLastname().isEmpty() || 
				clientInputValidation.checkEmailFormatSignUp(sellerClientDetails.getEmail())
					)throw new NotNullException("Please fill up all the field");	
			return sellerClientDetails;
				
			}
		
		public AddBalance checkaddedBalance(AddBalance addBalance) {
			clientInputValidation.checkDouble(addBalance.getBalance());
			clientInputValidation.checkEmailTrue(addBalance.getClientEmail());
			return addBalance;
		}
		
		public TransferBalance checkTransferBalance(TransferBalance transferBalance) {
			clientInputValidation.checkEmailTrue(transferBalance.getSenderEmail());
			clientInputValidation.checkEmailTrue(transferBalance.getReceiverEmail());
			clientInputValidation.checkDouble(transferBalance.getBalanceTransfer());
			return transferBalance;
		}
		
		public ChangeClientRole checkRole(ChangeClientRole changeClientRole) {
			clientInputValidation.checkRole(changeClientRole.getRoleChange());
			clientInputValidation.checkEmailTrue(changeClientRole.getClientEmail());
			return changeClientRole;
		}
		
		public SellerClientDetails checkSerClientDetails(SellerClientDetails sellerClientDetails) {
			clientInputValidation.checkEmailFalse(sellerClientDetails.getEmail());
			return sellerClientDetails;
		}
		
		public BuyerClientDetails checkBuyerClientDetails(BuyerClientDetails buyerClientDetails) {
			clientInputValidation.checkEmailFalse( buyerClientDetails.getEmail());
			return buyerClientDetails;
		}
		
		public void checkProductDeleteBuyer(Integer id,String email) {
			clientInputValidation.checkProductIDBuyer(id,clientInputValidation.checkEmailTrue(email));
		}
		
		public void checkProductDeleteSeller(Integer id,String email) {
			clientInputValidation.checkProductIDSellerr(id, clientInputValidation.checkEmailTrue(email));
		}
		
//		public static int getCount(int count) {
//		    return count ++;
//		}
//	//need Spring Security
//	  public boolean signInValidation(String username, String password) {
//		  Integer totalAttempt = 10;
//		  
//		  Integer totalAttempt2 = 1;
//		  
//		  do {
//			  
//			  String pass =  clientRepoValidation.passwordValidationSignIn(username);
//			 
//			  if (password.equals(pass)) {
//				  return  true; 
//			}else {
//				System.err.println("test1: " +getCount(totalAttempt2));
//				System.err.println("test2: " + totalAttempt);
//				totalAttempt--;
//					//throw new InvalidException("Login attempt " + totalAttempt +" for username " + username + ".");
//					
//			}
//			  
//			  
//		} while (totalAttempt > 0);
//		  System.err.println("test3: " +(getCount(totalAttempt2++)  ));
//			
////		  if(totalAttempt == 0)  throw new InvalidException("Reach the maximum attempt. The account is temporay unavailable");
//		return false;
//			
//			
//		  }
		  
}
