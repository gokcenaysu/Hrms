package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CvSkill;

public interface CvSkillService {

	Result add(CvSkill cvSkill);
	Result addAll(List<CvSkill> cvSkill);
	DataResult<List<CvSkill>> getAll();
	DataResult<List<CvSkill>> getAllByJobSeekerId(int jobSeekerId);
}
