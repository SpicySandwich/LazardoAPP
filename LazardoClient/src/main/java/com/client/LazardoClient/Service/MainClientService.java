package com.client.LazardoClient.Service;

import com.client.LazardoClient.DAO.BuyerSignInRepo;
import com.client.LazardoClient.DAO.SellerSignInRepo;
import com.client.LazardoClient.DAO.SignUpClientRepo;
import com.client.LazardoClient.DAO.ValidationRepo;
import com.client.LazardoClient.Model.ClientLogin;
import com.client.LazardoClient.ModelException.InvalidException;
import com.client.LazardoClient.Validation.CompileValidation;

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
	private CompileValidation compileValidation;
	
	 public ClientLogin siginInMainClient(String username, String password) {
		 
//		 compileValidation.signInValidation(username, password);
		 
			 Integer role = validationRepo.checkRole(username, password);
			 if (role == 1) return buyerSignInRepo.buyerSignIn(username, password);
			 else if (role == 2) return sellerShowProduct.sellerSignIn(username,password);
			 
        throw new InvalidException("Invalid username and password");
		}

	//Sign up then direct to SignIn depend on role buyer or seller
	public ClientLogin signUpClient(ClientLogin clientLogin) {
		
		compileValidation.signUpValidation(clientLogin);
	
		if(!(clientLogin.getSellerDetails() == null)) {
			compileValidation.checkSellerDetails(clientLogin.getSellerDetails());
			clientLogin.getSellerDetails().setLoginUsername(clientLogin.getUsername());
			return signUpSeller(clientLogin);
		}else if(!(clientLogin.getBuyerDetails() == null)) {
			compileValidation.checkBuyerDetails(clientLogin.getBuyerDetails());
			 clientLogin.getBuyerDetails().setLoginUsername(clientLogin.getUsername());
			 return signUpBuyer(clientLogin);
		}
		throw new InvalidException("Invalid sign up");
	}
	
			public ClientLogin signUpSeller(ClientLogin clientLogin) {
				 if(signUpClientRepo.signUpClient(clientLogin) == true && 
						 signUpClientRepo.insertSellerDetails(clientLogin.getSellerDetails()) == true  ) 
					 return sellerShowProduct.sellerSignIn(clientLogin.getUsername(), clientLogin.getPassword());
				 
				 throw new InvalidException("Invalid sign up for buyer");
			}
	
		public ClientLogin signUpBuyer(ClientLogin clientLogin) {
			
			 if(signUpClientRepo.signUpClient(clientLogin) == true && 
					 signUpClientRepo.insertBuyerDetails(clientLogin.getBuyerDetails()) == true )
				 return buyerSignInRepo.buyerSignIn(clientLogin.getUsername(), clientLogin.getPassword()); 
			 
			 throw new InvalidException("Invalid sign up for seller");
				
			}

}