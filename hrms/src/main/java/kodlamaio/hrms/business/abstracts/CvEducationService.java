package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CvEducation;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

public interface CvEducationService {

	Result add(CreateCvDto createCvDto);

	Result addAll(List<CvEducation> cvEducation);

	DataResult<List<CvEducation>> getAll();
	
	DataResult<List<CvEducation>> getAllByJobSeekerIdOrderByGraduationDateDesc(int jobSeekerId);
}
