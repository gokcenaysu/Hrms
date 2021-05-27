package kodlamaio.hrms.core.adapters.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.adapters.abstracts.EmailVerificationService;

@Service
public class EmailVerificationManager implements EmailVerificationService{
	
	@Override
	public boolean isEmailFormat(String emailFormat) {
		
		    String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

		    Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(emailFormat);
	        return matcher.matches();
	}

	@Override
	public boolean isEmailVerifyClicked(String emailClick) {
		System.out.println("Email verification was successful");
		return true;
	}
}
