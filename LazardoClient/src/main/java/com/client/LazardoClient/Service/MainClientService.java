package com.client.LazardoClient.Service;

import com.client.LazardoClient.DAO.BuyerSignInRepo;
import com.client.LazardoClient.DAO.SellerSignInRepo;
import com.client.LazardoClient.DAO.SignUpClientRepo;
import com.client.LazardoClient.DAO.ValidationRepo;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.ModelException.InvalidException;
import com.client.LazardoClient.Validation.ClientInputValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainClientService {
	
	@Autowired
	private SignUpClientRepo signUpClientRepo;
	
	@Autowired
	private BuyerClientService buyerClientService;
	
	@Autowired
	private SellerClientService sellerClientService;
	
	@Autowired
	private SellerSignInRepo sellerShowProduct;
	
	@Autowired
	private BuyerSignInRepo buyerSignInRepo;
	
	@Autowired
	private ValidationRepo validationRepo;
	
	@Autowired
	private ClientInputValidation clientInputValidation;
	
	 public ClientLogin siginInMainClient(String username, String password) {
		 
			 Integer role = validationRepo.checkRole(username, password);
			 if (role == 1) return buyerSignInRepo.singInClient(username, password);  
			 if (role == 2) return sellerShowProduct.singInClient(username,password);
        throw new InvalidException("Invalid username and password");
		}

	//Sign up then direct to SignIn depend on role buyer or seller
	public ClientLogin signUpClient(ClientLogin clientLogin) {
		 clientLogin.getBuyerDetails().setLoginUsername(clientLogin.getUsername());
		if(signUpClientRepo.signUpClient(clientLogin) == true && 
			signUpClientRepo.insertClientDetails(clientLogin.getBuyerDetails()) == true) {
			
			//Role 1  Buyer, 2 Seller
			 Integer role = validationRepo.checkRole(clientLogin.getUsername(), clientLogin.getPassword());
			 if (role == 1) return buyerSignInRepo.singInClient(clientLogin.getUsername(), clientLogin.getPassword());  
			 if (role == 2) return sellerShowProduct.singInClient(clientLogin.getUsername(), clientLogin.getPassword());
		   }
		    return null;
	}
	
	  public ClientLogin signUpValidation(ClientLogin clientLogin) {
		
		Integer totalAttempt = 3;
		do {
			if (clientInputValidation.checkUsernameNotNull(clientLogin.getUsername()) == true ||
				clientInputValidation.checkPasswordFormat(clientLogin.getPassword())== true) {
				
				return signUpClient(clientLogin);
			}else {
				
			}
		
		totalAttempt --;
					
				} while (totalAttempt > 0);
		 throw new InvalidException("Sign In attemp is done. Kindly wait for a minute");
			}
	  
	  public ClientLogin signInValidation(String password) {
		  Integer totalAttempt = 3;
		  do {
			
				totalAttempt --;
		} while (totalAttempt > 0);
		return null;
	  }

}
