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
	   
	   public String passwordValidationSignIn(String user) {
		   String checkPass =  validationRepo.checkPassword(user);
		   return checkPass;
	   }
	   
	   public boolean emailValidationSignUp(String email) {
			boolean checkEmail =  validationRepo.ifEmailExist(email);
			if(checkEmail == true) throw new AlreadyExistException("Email "+ email + " already exist. Kindly change");
			return checkEmail;
			
		}
	   
       public Integer productIDValidation(Integer id) {
	   boolean checkid =  validationRepo.ifProductExist(id);
		if(checkid == false) throw new InvalidException("Product is not available");
		return id;
	}
	
	
	


}