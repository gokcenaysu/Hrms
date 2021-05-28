package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.adapters.abstracts.RegexService;
import kodlamaio.hrms.core.adapters.abstracts.SimulatedMernisService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService{

	private JobSeekerDao jobSeekerDao;
	private RegexService regexService;
	private SimulatedMernisService simulatedMernisService;
	
	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao, RegexService regexService,
			SimulatedMernisService simulatedMernisService) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.simulatedMernisService = simulatedMernisService;
		this.regexService=regexService;
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
				|| jobSeeker.getEmail().isEmpty() || jobSeeker.getPassword().isEmpty()) {
			return new ErrorResult("Fields cannot be left blank");
		}
		
		if(!regexService.isEmailFormat(jobSeeker.getEmail())) {
			return new ErrorResult("Please enter in e-mail format");
		}
		
		if(!simulatedMernisService.checkMernis(jobSeeker.getFirstName(), jobSeeker.getLastName(), 
				jobSeeker.getIdentityNumber(), jobSeeker.getBirthYear())) {
			return new ErrorResult("Authentication unsuccessful");
		}
		
		if(jobSeekerDao.findByEmailEquals(jobSeeker.getEmail())!= null || jobSeekerDao.findByIdentityNumberEquals(jobSeeker.getIdentityNumber())!= null) {
			return new ErrorResult("Registered e-mail or ID number");
		}
		
		else {
			this.jobSeekerDao.save(jobSeeker);
			return new SuccessResult("Your registration has been created successfully");			
		}	
	}
}
