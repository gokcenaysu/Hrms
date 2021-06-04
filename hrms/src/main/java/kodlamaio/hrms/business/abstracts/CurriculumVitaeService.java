package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

public interface CurriculumVitaeService {

	// DataResult<List<CreateCvDto>> getResumeByJobSeekerId(int jobSeekerId);
	// DataResult<JobSeeker> getById(int id);
	Result add(CreateCvDto createCvDto, int jobSeekerId);
}
