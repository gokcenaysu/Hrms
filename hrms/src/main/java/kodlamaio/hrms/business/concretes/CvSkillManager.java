package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CvSkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvSkillDao;
import kodlamaio.hrms.entities.concretes.CvSkill;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

@Service
public class CvSkillManager implements CvSkillService {

	private CvSkillDao cvSkillDao;
	private ModelMapper modelMapper;

	@Autowired
	public CvSkillManager(CvSkillDao cvSkillDao, ModelMapper modelMapper) {
		this.cvSkillDao = cvSkillDao;
		this.modelMapper = modelMapper;
	}

	private List<CreateCvDto> dtoGenerator(List<CvSkill> posting) {
		return posting.stream().map(adv -> modelMapper.map(adv, CreateCvDto.class)).collect(Collectors.toList());
	}

	@Override
	public Result add(CreateCvDto createCvDto) {
		CvSkill cvSkill = this.modelMapper.map(createCvDto, CvSkill.class);
		this.cvSkillDao.save(cvSkill);
		return new SuccessResult("Cv eklendi.");
	}

	@Override
	public Result addAll(List<CvSkill> cvSkill) {
		cvSkillDao.saveAll(cvSkill);
		return new SuccessResult("Cv eklendi");
	}

	@Override
	public DataResult<List<CvSkill>> getAll() {
		return new SuccessDataResult<>(this.cvSkillDao.findAll());
	}

	@Override
	public DataResult<List<CvSkill>> getAllByJobSeekerId(int cvId) {
		return new SuccessDataResult<>(this.cvSkillDao.getAllByJobSeekerId(cvId));
	}
}
