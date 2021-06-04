package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CvSkill;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

public interface CvSkillService {

	Result add(CreateCvDto createCvDto);

	Result addAll(List<CvSkill> cvSkill);

	DataResult<List<CvSkill>> getAll();

	DataResult<List<CvSkill>> getAllByJobSeekerId(int jobSeekerId);
}
