package com.client.LazardoClient.Validation;

import com.client.LazardoClient.ModelException.InvalidException;
import com.client.LazardoClient.ModelException.NotNullException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientInputValidation {
	
	@Autowired
	private ClientRepoValidation validation;
	
	public Integer checkProductIdExist(Integer id) {
		validation.productIDValidation(id);
		return id;
	}
	
	public String checkEmailFormat( String email) {
		validation.emailValidation(email);
		if(email.trim().isEmpty()) throw new NotNullException("Email is empty or contain space");
		if(!email.matches("^(.+)@(.+)$")) throw new NotNullException("Email format is invalid");
		return email;
	}
	
	public boolean checkUsernameNotNull(String username) {
		boolean valid = true;
		validation.usernameValidation(username);
		if(username.trim().isEmpty()) throw new NotNullException("Username is empty or contain space");
		return valid;
	}
	
	public boolean checkPasswordFormat(String password) {
		boolean valid = true;
		if(password.trim().isEmpty())throw new NotNullException("Password is empty or contain space");
        if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{7,15}$"))
			throw new InvalidException("Password must contain at "
					+ "\n least one digit [0-9],one lowercase Latin character [a-z],"
					+ "\n least one uppercase Latin character [A-Z],"
					+ "\n least least one special character like ! @ # & ( ),"
					+ "\n length of at least 7 characters and a maximum of 15 characters");
		return valid;
	}

}
