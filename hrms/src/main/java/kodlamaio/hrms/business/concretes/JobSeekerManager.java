package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.adapters.abstracts.RegexService;
import kodlamaio.hrms.core.adapters.abstracts.SimulatedMernisService;
import kodlamaio.hrms.core.adapters.abstracts.VerificationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.concretes.Verification;

@Service
public class JobSeekerManager implements JobSeekerService{

	private JobSeekerDao jobSeekerDao;
	private RegexService regexService;
	private VerificationService verificationService;
	private SimulatedMernisService simulatedMernisService;
	
	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao, RegexService regexService,
			SimulatedMernisService simulatedMernisService, VerificationService verificationService) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.simulatedMernisService = simulatedMernisService;
		this.regexService=regexService;
		this.verificationService=verificationService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>
		(this.jobSeekerDao.findAll(),"Listed");
	}

	@Override
	public Result register(JobSeeker jobSeeker) {

		if(jobSeeker.getFirstName().isEmpty() || jobSeeker.getLastName().isEmpty()
				|| jobSeeker.getIdentityNumber().isEmpty() || jobSeeker.getBirthYear().isEmpty()
				|| jobSeeker.getEmail().isEmpty() || jobSeeker.getPassword().isEmpty()
				|| jobSeeker.getFirstName().isBlank() || jobSeeker.getLastName().isBlank()
				|| jobSeeker.getPassword().isBlank()) {
			return new ErrorResult("Fields cannot be empty");
		}
		
		if(!regexService.isFirstNameFormat(jobSeeker.getFirstName())) {
			return new ErrorResult("Your name must start with a capital letter");
		}
		
		if(!regexService.isLastNameFormat(jobSeeker.getLastName())) {
			return new ErrorResult("Your surname must start with a capital letter");
		}
		
		if(!regexService.isBirthYearFormat(jobSeeker.getBirthYear())) {
			return new ErrorResult("Please enter in birth year format");
		}
		
		if(!regexService.isEmailFormat(jobSeeker.getEmail())) {
			return new ErrorResult("Please enter in e-mail format");
		}
		
		if(!regexService.isPasswordFormat(jobSeeker.getPassword())) {
			return new ErrorResult("Enter a value in the range of 1-30");
		}
		
		if(!simulatedMernisService.checkMernis(jobSeeker.getFirstName(), jobSeeker.getLastName(), 
				jobSeeker.getIdentityNumber(), jobSeeker.getBirthYear())) {
			return new ErrorResult("Authentication unsuccessful");
		}
		
		if(jobSeekerDao.findByEmailEquals(jobSeeker.getEmail())!=null) {
			return new ErrorResult("E-mail already registered");
		}
		
		if(jobSeekerDao.findByIdentityNumberEquals(jobSeeker.getIdentityNumber())!= null) {
			return new ErrorResult("ID number already registered");
		}
		
		else {
			jobSeeker.setStatus(false);
			jobSeekerDao.save(jobSeeker);
			String code = verificationService.verifyCode();
			verificationCode(code, jobSeeker.getId(), jobSeeker.getEmail());
			return new SuccessResult("Your registration has been created successfully");	
		}	
	}
	
	public void verificationCode(String code, int id, String email) {
		Verification verificationCode = new Verification(id, code, false, LocalDateTime.now());
		this.verificationService.save(verificationCode);
	}

	@Override
	public Result update(JobSeeker jobSeeker, int userId) {
		JobSeeker jobSeekers = getById(userId).getData();
		if(jobSeeker.getBirthYear().isEmpty() || jobSeeker.getFirstName().isEmpty()
			|| jobSeeker.getLastName().isEmpty() || jobSeeker.getPassword().isEmpty()
			|| jobSeeker.getEmail().isEmpty() || jobSeeker.getFirstName().isBlank()
			|| jobSeeker.getLastName().isBlank() || jobSeeker.getPassword().isBlank()) {
			return new ErrorResult("Fields cannot be empty");
		}
		if(!regexService.isFirstNameFormat(jobSeeker.getFirstName())) {
			return new ErrorResult("Please enter in name format");
		}
		
		if(!regexService.isLastNameFormat(jobSeeker.getLastName())) {
			return new ErrorResult("Please enter in surname format");
		}
		
		if(!regexService.isBirthYearFormat(jobSeeker.getBirthYear())) {
			return new ErrorResult("Please enter in birth year format");
		}
		
		if(!regexService.isEmailFormat(jobSeeker.getEmail())) {
			return new ErrorResult("Please enter in e-mail format");
		}
		
		if(!simulatedMernisService.checkMernis(jobSeeker.getFirstName(), jobSeeker.getLastName(), 
				jobSeeker.getIdentityNumber(), jobSeeker.getBirthYear())) {
			return new ErrorResult("Authentication unsuccessful");
		}
		
		if(jobSeekerDao.findByEmailEquals(jobSeeker.getEmail())!=null) {
			return new ErrorResult("E-mail already registered");
		}
		
		if(jobSeekerDao.findByIdentityNumberEquals(jobSeeker.getIdentityNumber())!= null) {
			return new ErrorResult("ID number already registered");
		}
		
		else {
			this.jobSeekerDao.save(jobSeeker);
			return new SuccessResult("Your registration has been created successfully");
		}	
		
	}

	@Override
	public DataResult<JobSeeker> getById(int userId) {
		return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.getOne(userId));
	}
}
