package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.ConfirmByPersonnelService;
import kodlamaio.hrms.core.adapters.abstracts.RegexService;
import kodlamaio.hrms.core.adapters.abstracts.VerificationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.Verification;
import kodlamaio.hrms.entities.dtos.EmployerRegisterDto;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;
	private RegexService regexService;
	private ModelMapper modelMapper;
	private VerificationService verificationService;
	private ConfirmByPersonnelService confirmByPersonnelService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao,  RegexService regexService, ModelMapper modelMapper,
			ConfirmByPersonnelService confirmByPersonnelService, VerificationService verificationService) {
		super();
		this.employerDao = employerDao;
		this.confirmByPersonnelService = confirmByPersonnelService;
		this.regexService=regexService;
		this.verificationService=verificationService;
		this.modelMapper=modelMapper;
	}

	
	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>
		(this.employerDao.findAll(), "Listed");
	}

	@Override
	public Result register(EmployerRegisterDto employerDto) {
		
		Employer employer = this.modelMapper.map(employerDto, Employer.class);
	
		if(employer.getCompanyName().isEmpty() || employer.getWebsite().isEmpty()
				|| employer.getPhoneNumber().isEmpty() || employer.getEmail().isEmpty() 
				|| employer.getPassword().isEmpty() || employer.getWebsite().isBlank() 
				|| employer.getPassword().isBlank()) {
			return new ErrorResult("Fields cannot be left blank");
		}
		
		String email = employer.getEmail();
        String[] emailSplit = email.split("@");
        if(!emailSplit[1].equals(employer.getWebsite())) {
            return new ErrorResult("Your e-mail address and domain do not match");
        }
        
        if(employerDao.getByEmailEquals(employer.getEmail())!=null) {
        	return new ErrorResult("E-mail already registered");
        }
        
        if(!regexService.isPhoneNumberFormat(employer.getPhoneNumber())) {
        	return new ErrorResult("Invalid phone number");
        }
        
        if(!regexService.isPasswordFormat(employer.getPassword())) {
			return new ErrorResult("Enter a value in the range of 1-30");
		}
        
        if(employerDao.getByWebsiteEquals(employer.getWebsite())!=null) {
        	return new ErrorResult("Website already registered");
        }
        
        if(employerDao.getByPhoneNumberEquals(employer.getPhoneNumber())!=null) {
        	return new ErrorResult("Phone number already registered");
        }
        
        else {
        	employer.setStatus(false);
			this.employerDao.save(employer);
			String code = verificationService.verifyCode();
			verificationCode(code, employer.getId(), employer.getEmail());
			return new SuccessResult("Your registration has been created successfully");
	   }
	}
        
        public void verificationCode(String code, int id, String email) {
    		Verification verificationCode = new Verification(id, code, false, LocalDateTime.now());
    		this.verificationService.save(verificationCode);
    	}
	
	@Override
	public Result confirmByPersonnel(Employer employer) {
		if(!confirmByPersonnelService.isConfirmedByPersonnel(employer)) {
			return new ErrorResult("Your required confirm by the personnel has not been completed");
		}
		else {
		  return new SuccessResult("Your confirm by the personnel has been completed");
		}
	}


	/*
	@Override
	public Result update(Employer employer, int userId) {
		Employer employers = getById(userId).getData();
		if(employer.getCompanyName().isEmpty() || employer.getWebsite().isEmpty()
				|| employer.getPhoneNumber().isEmpty() || employer.getEmail().isEmpty() 
				|| employer.getPassword().isEmpty() || employer.getWebsite().isBlank() 
				|| employer.getPassword().isBlank()) {
			return new ErrorResult("Fields cannot be left blank");
		}
		
		String email = employer.getEmail();
        String[] emailSplit = email.split("@");
        if(!emailSplit[1].equals(employer.getWebsite())) {
            return new ErrorResult("Your e-mail address and domain do not match");
        }
        
        if(employerDao.getByEmailEquals(employer.getEmail())!=null) {
        	return new ErrorResult("Registered e-mail");
        }
        
        if(!regexService.isPhoneNumberFormat(employer.getPhoneNumber())) {
        	return new ErrorResult("Invalid phone number");
        }
        
        if(employerDao.getByWebsiteEquals(employer.getWebsite())!=null) {
        	return new ErrorResult("Registered website");
        }
        
        if(employerDao.getByPhoneNumberEquals(employer.getPhoneNumber())!=null) {
        	return new ErrorResult("Registered phone number");
        }
        
        else {
			this.employerDao.save(employer);
			return new SuccessResult("Your registration has been created successfully");
	}
	}*/


	@Override
	public DataResult<Employer> getById(int userId) {
		return new SuccessDataResult<Employer>(this.employerDao.getOne(userId));
	}
}
