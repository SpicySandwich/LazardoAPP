package com.client.LazardoClient.Validation;

import java.util.Optional;

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
		   
		   Optional.of(checkUser)
		   .filter(Boolean::booleanValue)
		   .map(check ->{
			   throw new AlreadyExistException("Username " + username + " already exist. Kindly create new one");
		   });
			return checkUser;
		}
	   
	   public String usernameValidationSignIn(String username, String password) {
			String pass = validationRepo.checkPassword(username);
			
		return Optional.ofNullable(username)
			.filter(user -> validationRepo.ifUsernameExist(user) == true && password.matches(pass) == true)
			.map(user -> {
				return username;
			}).orElseThrow(() ->  new InvalidException("Password is wrong for username "+ username));
			  
		   }
	   
	   public String passwordValidationSignIn(String user) {
		   String checkPass =  validationRepo.checkPassword(user);
		   return checkPass;
	   }
	   
	   public void emailValidationSignUp(String email) {
		   
		   boolean checkEmail =  validationRepo.ifEmailExist(email);
		   
		   Optional.of(checkEmail)
		   .filter(Boolean::booleanValue)
		   .map(check ->{
			   throw new AlreadyExistException("Email "+ email + " already exist. Kindly change");
		   });
		}

	
	
	


}
