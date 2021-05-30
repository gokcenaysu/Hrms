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
import kodlamaio.hrms.entities.dtos.JobSeekerDto;

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
	public Result register(JobSeekerDto jobSeekerDto) {

		if(jobSeekerDto.getFirstName().isEmpty() || jobSeekerDto.getLastName().isEmpty()
				|| jobSeekerDto.getIdentityNumber().isEmpty() || jobSeekerDto.getBirthYear().isEmpty()
				|| jobSeekerDto.getEmail().isEmpty() || jobSeekerDto.getPassword().isEmpty()) {
			return new ErrorResult("Fields cannot be left blank");
		}
		
		if(!regexService.isFirstNameFormat(jobSeekerDto.getFirstName())) {
			return new ErrorResult("Please enter in name format");
		}
		
		if(!regexService.isLastNameFormat(jobSeekerDto.getLastName())) {
			return new ErrorResult("Please enter in surname format");
		}
		
		if(!regexService.isBirthYearFormat(jobSeekerDto.getBirthYear())) {
			return new ErrorResult("Please enter in birth year format");
		}
		
		if(!regexService.isEmailFormat(jobSeekerDto.getEmail())) {
			return new ErrorResult("Please enter in e-mail format");
		}
		
		if(!simulatedMernisService.checkMernis(jobSeekerDto.getFirstName(), jobSeekerDto.getLastName(), 
				jobSeekerDto.getIdentityNumber(), jobSeekerDto.getBirthYear())) {
			return new ErrorResult("Authentication unsuccessful");
		}
		
		if(jobSeekerDao.findByEmailEquals(jobSeekerDto.getEmail())!=null) {
			return new ErrorResult("Registered e-mail");
		}
		
		if(jobSeekerDao.findByIdentityNumberEquals(jobSeekerDto.getIdentityNumber())!= null) {
			return new ErrorResult("Registered ID number");
		}
		
		else {
			this.jobSeekerDao.save(jobSeekerDto);
			return new SuccessResult("Your registration has been created successfully");			
		}	
	}
}
