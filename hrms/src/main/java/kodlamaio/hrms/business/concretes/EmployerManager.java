package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.ConfirmByPersonnelService;
import kodlamaio.hrms.core.adapters.abstracts.RegexService;
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
	private RegexService regexService;
	private ConfirmByPersonnelService confirmByPersonnelService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao,  RegexService regexService,
			ConfirmByPersonnelService confirmByPersonnelService) {
		super();
		this.employerDao = employerDao;
		this.confirmByPersonnelService = confirmByPersonnelService;
		this.regexService=regexService;
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
        
        if(!regexService.isPhoneNumberFormat(employer.getPhoneNumber())) {
        	return new ErrorResult("Invalid phone number");
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
	@Override
	public Result confirm(Employer employer) {
		if(!confirmByPersonnelService.isConfirmedByPersonnel(employer)) {
			return new ErrorResult("Your required confirm by the personnel has not been completed");
		}
		else {
		  return new SuccessResult("Your confirm by the personnel has been completed");
		}
	}
}
