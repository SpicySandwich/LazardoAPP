package com.client.LazardoClient.Validation;

import com.client.LazardoClient.DAO.ValidationRepo;
import com.client.LazardoClient.ModelException.AlreadyExistException;
import com.client.LazardoClient.ModelException.InvalidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientRepoValidation {
	
	@Autowired
	private ValidationRepo validationRepo;

	   public boolean usernameValidationSignUp(String username) {
		   boolean checkUser =  validationRepo.ifUsernameExist(username);
		   if(checkUser  == true) throw new AlreadyExistException("Username " + username + " already exist. Kindly create new one");
			return checkUser;
		}
	   
	   public String usernameValidationSignIn(String username, String password) {
		String pass = validationRepo.checkPassword(username);
		
		   if(validationRepo.ifUsernameExist(username) == true && password.matches(pass) == true) return username;
		   throw new InvalidException("Password is wrong for username "+ username);
	   }
	   
	   public String passwordValidationSignIn(String user) {
		   String checkPass =  validationRepo.checkPassword(user);
		   return checkPass;
	   }
	   
	   public boolean emailValidationSignUp(String email) {
			boolean checkEmail =  validationRepo.ifEmailExist(email);
			if(checkEmail == true) throw new AlreadyExistException("Email "+ email + " already exist. Kindly change");
			return checkEmail;
			
		}

	
	
	


}
