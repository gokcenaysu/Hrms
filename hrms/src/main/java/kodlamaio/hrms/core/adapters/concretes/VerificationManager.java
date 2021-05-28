package kodlamaio.hrms.core.adapters.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.adapters.abstracts.VerificationService;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.VerificationDao;
import kodlamaio.hrms.entities.concretes.Verification;

@Service
public class VerificationManager implements VerificationService{
	
	private VerificationDao verificationDao;
	
	@Autowired
	public VerificationManager(VerificationDao verificationDao) {
		super();
		this.verificationDao=verificationDao;
	}

	@Override
	public void genereatedVerify(Verification verification, int id) {
		
		Verification verifications = verification;
		verifications.setEmailVerification(null);
		verifications.setVerified(false);
		if (verification.isVerified()==false) {
			RandomGeneratedVerify randomVerify = new RandomGeneratedVerify();
			String create_verify = randomVerify.create();
			verifications.setEmailVerification(create_verify);
			
			this.verificationDao.save(verifications);
		}
		return;

	}		
	
	@Override
	public Result isVerified(String randomVerify, int id) {
		Verification verify = verificationDao.getOne(id);
		if(verify.getEmailVerification().equals(randomVerify)) {
			verify.setVerified(true);
			return new SuccessDataResult<Verification>(this.verificationDao.save(verify),"Successfull");
		}
		else {
			return new ErrorDataResult<Verification>("Unsuccessfull");
		}
	}
	}

