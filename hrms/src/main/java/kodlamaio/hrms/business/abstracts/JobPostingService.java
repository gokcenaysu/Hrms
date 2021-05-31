package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.dtos.JobPostingDto;

public interface JobPostingService {

	Result post(JobPosting jobPosting);
	Result update(int postingId, boolean activityStatus);
	Result delete(int postingId);
	DataResult<JobPosting> getByPostingId(int postingId);
	DataResult<List<JobPostingDto>> findByActivityStatus();
	DataResult<List<JobPostingDto>> findByActivityStatusAndApplicationDeadline();
	DataResult<List<JobPostingDto>> findByActivityStatusAndEmployer(int employerId);
}
