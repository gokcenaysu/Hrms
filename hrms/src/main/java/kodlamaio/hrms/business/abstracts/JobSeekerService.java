package kodlamaio.hrms.business.abstracts;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.JobSeekerRegisterDto;

public interface JobSeekerService {
	

	DataResult<List<JobSeeker>> getAll();
	Result register(JobSeekerRegisterDto jobSeekerDto);
	DataResult<JobSeeker> getById(int userId);
	
//	Result update(JobSeekerRegisterDto jobSeekerDto, int userId);

}
