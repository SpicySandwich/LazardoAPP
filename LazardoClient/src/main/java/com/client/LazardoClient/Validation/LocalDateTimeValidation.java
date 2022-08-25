package com.client.LazardoClient.Validation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.client.LazardoClient.ModelException.InvalidException;
import com.client.LazardoClient.ModelException.NotNullException;

import org.springframework.stereotype.Service;

@Service
public class LocalDateTimeValidation {
	

	public String LocalDateTimeNotNull(String date) {
		
		Boolean checkNull = (date.trim().isEmpty() || date == null) ? true : false ;
		
		Optional.of(checkNull)
		.filter(Boolean::booleanValue)
		.map(check -> {
			throw new NotNullException("Date cannot be empty");
		});
		    return date;
		    
		}

	public String LocalDateTimeFormatValidation(String input) {
		String dString = LocalDateTimeNotNull(input);
		
		Optional.of(input)
		.filter(format -> !format.matches(" ^((?:(?:1[6-9]|2[0-9])\\d{2})(-)(?:(?:(?:0[13578]|1[02])(-)31)|((0[1,3-9]|1[0-2])(-)(29|30))))$|^(?:(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(-)02(-)29)$|^(?:(?:1[6-9]|2[0-9])\\d{2})(-)(?:(?:0[1-9])|(?:1[0-2]))(-)(?:0[1-9]|1\\d|2[0-8])$"))
		.map(format -> {
			 throw new InvalidException("Date format is invalid. Example format (yyyy-MM-dd HH:mm:ss) or Date is invalid in calendar");
		});
		return checkDateIfEqualOrPrevious(dString);			 
		  
	}

		public String  checkDateIfEqualOrPrevious(String stringdate) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate datep = LocalDate.parse(stringdate, formatter);
		       LocalDate currentdate = LocalDate.now(ZoneId.systemDefault());
		       LocalDate expiredDate =currentdate.plusMonths(2);
		       int expiredday = expiredDate.getDayOfMonth();
		       int expiredyear = expiredDate.getYear();
		       int expiredmonth= expiredDate.getMonthValue();

				LocalDate localDate3 =LocalDate.of(expiredyear,expiredmonth,expiredday + 1 );
				
				Optional.of(datep)
				.filter(dateexp -> dateexp.equals(localDate3) || dateexp.isBefore(localDate3))
				.map(dateexp -> {
					throw new InvalidException("Expiration date must ahead or equal to " + localDate3 + ".");
				});
				return stringdate;
		 }

}
