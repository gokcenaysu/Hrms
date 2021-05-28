package kodlamaio.hrms.core.adapters.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Verification;

public interface VerificationService {

	void genereatedCode(Verification verification);
	Result isVerified(String emailVerify, int id);
}
