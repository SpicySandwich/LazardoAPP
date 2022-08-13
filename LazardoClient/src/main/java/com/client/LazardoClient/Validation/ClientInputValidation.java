package com.client.LazardoClient.Validation;

import com.client.LazardoClient.DAO.ValidationRepo;
import com.client.LazardoClient.Model.AddBalance;
import com.client.LazardoClient.Model.BuyerCartProduct;
import com.client.LazardoClient.Model.TransferBalance;
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
		if(email.trim().isEmpty()) throw new NotNullException("Email is empty or contain space");
		if(!email.matches("^(.+)@(.+)$")) throw new NotNullException("Email format is invalid");
		return false;
	}
	
	public String checkUsernameNotNullSignUp(String username) {
		if(username.trim().isEmpty()) throw new NotNullException("Username is empty or contain space");
		return username;
	}
	
	public String checkPasswordFormatSignUp(String password) {
		if(password.trim().isEmpty())throw new NotNullException("Password is empty or contain space");
        if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{7,15}$"))
			throw new InvalidException("Password must contain at "
					+ "least one digit [0-9],one lowercase Latin character [a-z],"
					+ "least one uppercase Latin character [A-Z],"
					+ "least least one special character like ! @ # & ( ),"
					+ "length of at least 7 characters and a maximum of 15 characters");
		return password;
	}
	
	public Integer checkProductIDBuyer(Integer productID , String email){
		if(validationRepo.ifProductExistBuyer(productID, email) == false)  throw new InvalidException("Product is unavailable to be deleted");
		return productID;
	}
	
	public Integer checkProductIDSellerr(Integer productID , String email){
		if(validationRepo.ifProductExistSeller(productID, email) == false)  throw new InvalidException("Product is unavailable to be deleted Or Product is already purchase by buyer ");
		return productID;
	}

	public Double checkDouble(Double balance) {
    boolean check =	Double.compare(balance, 0.0) < 0;
    if(check == true)throw new InvalidException("Balance can't be negative value");
		return  balance;
	}

	 public String checkEmailTrue(String email) {
		if (validationRepo.ifEmailExist(email) == true) return email;
			throw new InvalidException("Email don't exist");
	 }
	 public String checkEmailFalse(String email) {
			if (validationRepo.ifEmailExist(email) == true) 	throw new InvalidException("Email already exist");
			return email;
		 }
	 
	 public Integer checkRole(Integer role) {
		String check =	String.valueOf(role);
		if(check.matches("[1-2]")) return role;
		throw new InvalidException("Role don't exist");
		 }


}
