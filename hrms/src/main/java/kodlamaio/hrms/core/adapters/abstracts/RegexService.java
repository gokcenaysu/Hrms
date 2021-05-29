package kodlamaio.hrms.core.adapters.abstracts;

public interface RegexService {
	
	boolean isPhoneNumberFormat(String phoneNumberFormat);
	boolean isEmailFormat(String emailFormat);
	boolean isFirstNameFormat(String firstNameFormat);
	boolean isLastNameFormat(String lastNameFormat);
	boolean isBirthYearFormat(String birthYearFormat);
}
