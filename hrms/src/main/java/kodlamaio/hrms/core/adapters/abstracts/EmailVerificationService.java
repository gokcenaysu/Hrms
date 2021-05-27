package kodlamaio.hrms.core.adapters.abstracts;

public interface EmailVerificationService {

	boolean isEmailFormat(String emailFormat);
	boolean isEmailVerifyClicked(String emailClick);
}
