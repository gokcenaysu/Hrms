package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.User;

public interface JobSeekerService {
	
	DataResult<List<JobSeeker>> getAll();
	Result register(JobSeeker jobSeeker);
}
