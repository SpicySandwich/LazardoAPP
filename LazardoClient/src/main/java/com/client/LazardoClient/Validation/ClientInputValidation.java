package com.client.LazardoClient.Validation;

import java.util.Optional;

import com.client.LazardoClient.DAO.ValidationRepo;
import com.client.LazardoClient.ModelException.InvalidException;
import com.client.LazardoClient.ModelException.NotNullException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientInputValidation {
	
	@Autowired
	private ClientRepoValidation validation;
	
	@Autowired
	private ValidationRepo validationRepo;
	
	
	public boolean checkEmailFormatSignUp( String email) {
		 validation.emailValidationSignUp(email);
		 
	  Optional.ofNullable(email)
		.filter(word -> word.matches("^(.+)@(.+)$") && !word.trim().isEmpty())
		.orElseThrow(()-> new NotNullException("Email invalid format or empty"));
		
		return false;
	}
	
	public String checkUsernameNotNullSignUp(String username, String retypeusername) {
		
		Boolean userCheck = (username == null || username.trim().isEmpty() || retypeusername == null || retypeusername.trim().isEmpty()) ? true : false;
		
		Optional.of(userCheck)
		.filter(Boolean::booleanValue)
		.map(check -> {
			throw  new  NotNullException("Username is empty");
		});
		return checkUsernameMatch(username, retypeusername);
	
	}
	
	public String checkUsernameMatch(String username, String retypeusername) {
		
		Boolean checkMatch = (!username.equals(retypeusername) || !retypeusername.equals(username)) ? true : false;
		
		Optional.of(checkMatch)
		.filter(Boolean::booleanValue)
		.map(check -> {
			throw  new  NotNullException("Username do not match");
		});
		
		return username;
	}
	
	
	public String checkPasswordNotNullSignUp(String password, String retypepassword) {
		
		Boolean passCheck = (password == null || password.trim().isEmpty()  || retypepassword == null || retypepassword.trim().isEmpty()) ? true : false ;
		
		Optional.of(passCheck)
		.filter(Boolean::booleanValue)
		.map(pass -> {
			throw new NotNullException("Password is empty ");	
		});
		return checkPasswordFormatSignUp(password,retypepassword);
	}
	
	public String checkPasswordFormatSignUp(String password, String retypepassword) {
		
		Boolean passFormCheck = (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()???[{}]:;',?/*~$^+=<>]).{7,15}$") ||
														  !retypepassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()???[{}]:;',?/*~$^+=<>]).{7,15}$")) 
				                                          ? true : false;
		
		Optional.of(passFormCheck)
		.filter(Boolean::booleanValue)
		.map(pass -> {
			throw new InvalidException("Password must contain at "
					+ "least one digit [0-9],one lowercase Latin character [a-z],"
					+ "least one uppercase Latin character [A-Z],"
					+ "least least one special character like ! @ # & ( ),"
					+ "length of at least 7 characters and a maximum of 15 characters");
		});	
		return checkPasswordMatch(password, retypepassword);
	}
	
	public String checkPasswordMatch(String password, String retypepassword) {
		
		Boolean checkMatch = (!password.equals(retypepassword) || !retypepassword.equals(password)) ? true : false;
		
		Optional.of(checkMatch)
		.filter(Boolean::booleanValue)
		.map(check -> {
			throw new InvalidException("Password do not match");
		});
		
		return password;
		
	}

	public Double checkDouble(Double balance) {
    boolean check =	Double.compare(balance, 0.0) < 0;
    
    Optional.of(check)
    .filter(Boolean::booleanValue)
    .map(check2 -> {
    	throw new InvalidException("Balance can't be negative value");
    });

		return  balance;
	}

	 public String checkEmailTrue(String email) {
		 
		 Optional.ofNullable(email)
		 .orElseThrow(() -> new NotNullException("Email is null"));
		 
		 boolean check =	 validationRepo.ifEmailExist(email);
		 
		return Optional.of(check)
		 .filter(Boolean::booleanValue)
		 .map(check2 -> email).<InvalidException>orElseThrow(() -> new InvalidException("Email don't exist"));


	 }
	 
	 public String checkEmailFalse(String email) {
		 
		 Optional.ofNullable(email)
		 .orElseThrow(() -> new NotNullException("Email is null"));
		 
		 boolean check =	 validationRepo.ifEmailExist(email);
		 
		 Optional.of(check)
		 .filter(Boolean::booleanValue)
		 .map(check2 -> {
			 throw new InvalidException("Email already exist");
		 });
			return email;
		 }
	 
	 public Integer checkRole(Integer role) {
		String check =	String.valueOf(role);
		
		Optional.ofNullable(check)
		.filter(word -> word.matches("[1-2]"))
		.orElseThrow(() -> new InvalidException("Role don't exist"));
		
          return role;

		 }
	 
	 public String checkProductName(String productname) {
		 
		 Optional.ofNullable(productname)
		 .orElseThrow(() -> new InvalidException("Product name is empty"));

		return productname;
	 }
	 
	 public String checkProductBrand(String productbrand) {
		 
		 Optional.ofNullable(productbrand)
		 .orElseThrow(() -> new InvalidException("Product brand is empty"));
	
		return productbrand;
	 }
	 
	 public String checkProductComment(String productcomment) {
		 
		 Optional.ofNullable(productcomment)
		 .orElseThrow(() -> new InvalidException("Product comment is empty"));

		return productcomment;
	 }
	 
		public Integer checkProductIDBuyer(Integer productID , String email){
			
			Optional.of(validationRepo.ifProductExistBuyer(productID, email))
			.filter(Boolean::booleanValue)
			.orElseThrow(() -> new InvalidException("Product is unavailable to be deleted"));
			return productID;
		}
		
		public void checkProductIDBuyerCartProduct(Integer productID){
			
			Optional.of(validationRepo.ifProductAvailableCart(productID))
			.filter(Boolean::booleanValue)
			.orElseThrow(() ->  new InvalidException("Product is unavailable to be cart"));
		}
		
		public Integer checkProductIDSellerr(Integer productID , String email){
			
			Optional.of(validationRepo.ifProductExistSeller(productID, email))
			.filter(Boolean::booleanValue)
			.orElseThrow(() ->  new InvalidException("Product is unavailable to be deleted Or Product is already purchase by buyer "));
			return productID;
		}
		
		public void checkProductIDUpdateExist(Integer productID , String email) {
			
			Optional.of(validationRepo.ifProductExistSellerProductUpdate(productID, email))
			.filter(Boolean::booleanValue)
			.orElseThrow(() ->  new InvalidException("Product is unavailable to update or don't exist"));
		}
		
		public void checkBuyerCartProductPaymentUnpaid(String buyeremail, Integer purchaseid) {
			
			Optional.of(validationRepo.ifBuyerCartProductUnpaidPayment(buyeremail, purchaseid))
			.filter(Boolean::booleanValue)
			.orElseThrow(() -> new InvalidException("Product already paid or don't exist"));
			}

}
