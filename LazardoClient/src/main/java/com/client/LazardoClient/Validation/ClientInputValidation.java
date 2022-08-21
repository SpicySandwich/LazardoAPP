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
		 
		Optional<String> emailOptional = Optional.ofNullable(email);
				
		emailOptional
		.filter(word -> word.trim().isEmpty())
		.map(word -> {
			 throw new NotNullException("Email is empty or contain space");
		});
		emailOptional
		.filter(word -> word.matches("^(.+)@(.+)$"))
		.orElseThrow(() -> new NotNullException("Email invalid format "));
				
		return false;
	}
	
	public String checkUsernameNotNullSignUp(String username) {
		
		Optional.ofNullable(username)
		.filter(user -> user.trim().isEmpty())
		.map(user -> {
			throw new NotNullException("Username is empty or contain space");
		});
		
		return username;
	}
	
	public String checkPasswordFormatSignUp(String password) {
		
		Optional<String> passOptional = Optional.ofNullable(password);
		
		passOptional
		.filter(pass -> pass.trim().isEmpty())
		.map(pass -> {
			throw new NotNullException("Password is empty or contain space");
		});
		
		passOptional
		.filter(pass -> pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{7,15}$"))
		.orElseThrow(() -> 
			new InvalidException("Password must contain at "
					+ "least one digit [0-9],one lowercase Latin character [a-z],"
					+ "least one uppercase Latin character [A-Z],"
					+ "least least one special character like ! @ # & ( ),"
					+ "length of at least 7 characters and a maximum of 15 characters"));
	
	
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
		 
		 boolean check =	 validationRepo.ifEmailExist(email);
		 
		 Optional.of(check)
		 .filter(Boolean::booleanValue)
		 .map(check2 -> {
			 return email;
		 });
		 throw new InvalidException("Email don't exist");
	 }
	 
	 public String checkEmailFalse(String email) {
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
			if(validationRepo.ifProductExistBuyer(productID, email) == false)  throw new InvalidException("Product is unavailable to be deleted");
			return productID;
		}
		
		public void checkProductIDBuyerCartProduct(Integer productID){
			if(validationRepo.ifProductAvailableCart(productID) == false)  throw new InvalidException("Product is unavailable to be cart");
		}
		
		public Integer checkProductIDSellerr(Integer productID , String email){
			if(validationRepo.ifProductExistSeller(productID, email) == false)  throw new InvalidException("Product is unavailable to be deleted Or Product is already purchase by buyer ");
			return productID;
		}
		
		public void checkProductIDUpdateExist(Integer productID , String email) {
			if(validationRepo.ifProductExistSellerProductUpdate(productID, email) == false) throw new InvalidException("Product is unavailable to update or don't exist");
		}
		
		public void checkBuyerCartProductPaymentUnpaid(String buyeremail, Integer purchaseid) {
			if(validationRepo.ifBuyerCartProductUnpaidPayment(buyeremail, purchaseid) == false) throw new InvalidException("Product already paid or don't exist");
		}

}
