package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.adapters.abstracts.ConfirmByPersonnelService;
import kodlamaio.hrms.core.adapters.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;
	private EmailVerificationService emailVerificationService;
	private ConfirmByPersonnelService confirmByPersonnelService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao, EmailVerificationService emailVerificationService,
			ConfirmByPersonnelService confirmByPersonnelService) {
		super();
		this.employerDao = employerDao;
		this.emailVerificationService = emailVerificationService;
		this.confirmByPersonnelService = confirmByPersonnelService;
	}

	
	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>
		(this.employerDao.findAll(), "Listed");
	}

	@Override
	public Result register(Employer employer) {
		
		if(employer.getCompanyName().isEmpty() || employer.getWebsite().isEmpty()
				|| employer.getPhoneNumber().isEmpty() || employer.getEmail().isEmpty() 
				|| employer.getPassword().isEmpty()) {
			return new ErrorResult("Fields cannot be left blank");
		}
		
		String email = employer.getEmail();
        String[] emailSplit = email.split("@");
        if(!emailSplit[1].equals(employer.getWebsite())) {
            return new ErrorResult("Your e-mail address and domain do not match");
        }
        
        if(employerDao.findByEmailEquals(employer.getEmail())!=null
        		|| employerDao.findByWebsiteEquals(employer.getWebsite())!=null) {
        	return new ErrorResult("Registered e-mail");
        }
        
        if(employerDao.findByWebsiteEquals(employer.getWebsite())!=null) {
        	return new ErrorResult("Registered website");
        }
        
        if(employerDao.findByPhoneNumberEquals(employer.getPhoneNumber())!=null) {
        	return new ErrorResult("Registered phone number");
        }
        
        else {
			this.employerDao.save(employer);
			return new SuccessResult("Your registration has been created successfully");
	}
  }
}
