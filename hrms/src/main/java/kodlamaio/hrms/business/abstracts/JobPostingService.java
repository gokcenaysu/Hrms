package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

public interface JobPostingService {

	Result post(JobPosting jobPosting);
	Result delete(JobPosting jobPosting);
	DataResult<List<JobPosting>> findByActivityStatus();
	DataResult<List<JobPosting>> findByActivityStatusAndApplicationDeadline();
	DataResult<List<JobPosting>> findByActivityStatusAndCompanyName(String companyName);
	

}
