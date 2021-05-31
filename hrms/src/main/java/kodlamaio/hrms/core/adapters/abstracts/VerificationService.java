package kodlamaio.hrms.core.adapters.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface VerificationService {

	//void genereatedVerify(Verification verification, int id);
	//Result isVerified(String emailVerify, int id);
	//Result sendActivationCode(String sendTo, String activationCode);
	Result verify(String email, int id);
}
