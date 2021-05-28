package kodlamaio.hrms.core.adapters.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.adapters.abstracts.RegexService;

@Service
public class RegexManager implements RegexService{

	@Override
	public boolean isPhoneNumberFormat(String phoneNumberFormat) {
		  String regex = "^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$";

		    Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(phoneNumberFormat);
	        return matcher.matches();
	}

	@Override
	public boolean isEmailFormat(String emailFormat) {
		
		 String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

		    Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(emailFormat);
	        return matcher.matches();
	}

}