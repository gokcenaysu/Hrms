package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CvExperience;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

public interface CvExperienceService {

	Result add(CreateCvDto createCvDto);

	Result addAll(List<CvExperience> cvExperience);

	DataResult<List<CvExperience>> getAll();

	DataResult<List<CvExperience>> getAllByJobSeekerIdOrderByJobEndingDateDesc(int jobSeekerId);
}
