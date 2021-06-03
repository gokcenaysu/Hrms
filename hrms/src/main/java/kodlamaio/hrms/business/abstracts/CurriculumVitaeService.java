package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

public interface CurriculumVitaeService {

	  DataResult<CreateCvDto> getResumeByJobSeekerId(int jobSeekerId);
	  DataResult<JobSeeker> getById(int id);
	  Result add(CreateCvDto createCvDto,int jobSeekerId);
}
