package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CvExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvExperienceDao;
import kodlamaio.hrms.entities.concretes.CvExperience;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

@Service
public class CvExperienceManager implements CvExperienceService {

	private CvExperienceDao cvExperienceDao;
	private ModelMapper modelMapper;

	@Autowired
	public CvExperienceManager(CvExperienceDao cvExperienceDao, ModelMapper modelMapper) {
		this.cvExperienceDao = cvExperienceDao;
		this.modelMapper = modelMapper;
	}

	private List<CreateCvDto> dtoGenerator(List<CvExperience> posting) {
		return posting.stream().map(adv -> modelMapper.map(adv, CreateCvDto.class)).collect(Collectors.toList());
	}

	@Override
	public Result add(CreateCvDto createCvDto) {
		CvExperience cvExperience = this.modelMapper.map(createCvDto, CvExperience.class);
		this.cvExperienceDao.save(cvExperience);
		return new SuccessResult("Cv eklendi.");
	}

	@Override
	public Result addAll(List<CvExperience> cvExperince) {
		this.cvExperienceDao.saveAll(cvExperince);
		return new SuccessResult("Cv eklendi.");
	}

	@Override
	public DataResult<List<CvExperience>> getAll() {
		return new SuccessDataResult<>(this.cvExperienceDao.findAll());
	}

	@Override
	public DataResult<List<CvExperience>> getAllByJobSeekerIdOrderByJobEndingDateDesc(int jobSeekerId) {
		return new SuccessDataResult<>(this.cvExperienceDao.getAllByJobSeekerIdOrderByJobEndingDateDesc(jobSeekerId));
	}

}
