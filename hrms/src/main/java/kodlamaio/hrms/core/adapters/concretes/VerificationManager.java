package kodlamaio.hrms.core.adapters.concretes;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public void genereatedCode(Verification verification) {
		
		Verification verifications = verification;
		verifications.setEmailVerification(null);
		verifications.setVerified(false);
		if (verification.isVerified()==false) {
			String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			StringBuilder sb = new StringBuilder();
			Random random = new Random();
			int length = 6;
			for(int i =0; i<length; i++) {
				int index = random.nextInt(alphaNumeric.length());
				char randomChar = alphaNumeric.charAt(index);
			}
			
			String randomString = sb.toString();
			System.out.println(randomString);
			verifications.setEmailVerification(randomString);
			

			this.verificationDao.save(verifications);
		}
		return;

	}		
	
	@Override
	public Result isVerified(String emailVerify, int id) {
		Verification verify = verificationDao.getOne(id);
		if(verify.getEmailVerification().equals(emailVerify)) {
			verify.setVerified(true);
			return new SuccessDataResult<Verification>(this.verificationDao.save(verify),"Successfull");
		}
		else {
			return new ErrorDataResult<Verification>("Unsuccessfull");
		}
	}
	}

