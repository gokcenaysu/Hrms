package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrms.entities.concretes.JobPosting;

@Service
public class JobPostingManager implements JobPostingService{

	private JobPostingDao jobPostingDao;
	
	@Autowired
	public JobPostingManager(JobPostingDao jobPostingDao) {
		super();
		this.jobPostingDao = jobPostingDao;
	}


	@Override
	public Result post(JobPosting jobPosting) {
		if(jobPostingDao.findByPostingIdEquals(jobPosting.getPostingId())!=null) {
			return new ErrorResult("This job posting is present");
		}
		else {
			this.jobPostingDao.save(jobPosting);
			return new SuccessResult("The job posting has been successfully added");
		}
	}

	@Override
	public DataResult<List<JobPosting>> findByActivityStatus() {
		return new SuccessDataResult<List<JobPosting>>
		(this.jobPostingDao.findByActivityStatus(true));
	}
	
	@Override
	public DataResult<List<JobPosting>> findByActivityStatusAndApplicationDeadline() {
		return new SuccessDataResult<List<JobPosting>>
		(this.jobPostingDao.findByActivityStatusOrderByApplicationDeadline(true));
		
	}
	
	@Override
	public DataResult<List<JobPosting>> findByActivityStatusAndCompanyName(String companyName) {
		return new SuccessDataResult<List<JobPosting>>
		(this.jobPostingDao.findByActivityStatusAndEmployer_CompanyName(true,companyName));
		
	}


	@Override
	public Result delete(JobPosting jobPosting) {
		this.jobPostingDao.delete(jobPosting);
		return new SuccessResult("Deletion is successful");
		}
	}

