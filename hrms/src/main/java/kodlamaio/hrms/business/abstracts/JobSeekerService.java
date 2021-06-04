package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.CreateCvDto;
import kodlamaio.hrms.entities.dtos.JobSeekerRegisterDto;

public interface JobSeekerService {

	DataResult<List<JobSeeker>> getAll();

	Result register(JobSeekerRegisterDto jobSeekerDto);

	DataResult<JobSeeker> getById(int userId);
	
    DataResult<CreateCvDto> getCreateCvById(int id);


//	Result update(JobSeekerRegisterDto jobSeekerDto, int userId);

}
