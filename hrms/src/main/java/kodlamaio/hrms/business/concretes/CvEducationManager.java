package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CvEducationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvEducationDao;
import kodlamaio.hrms.entities.concretes.CvEducation;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

@Service
public class CvEducationManager implements CvEducationService {

	private CvEducationDao cvEducationDao;
	private ModelMapper modelMapper;

	@Autowired
	public CvEducationManager(CvEducationDao cvEducationDao, ModelMapper modelMapper) {
		this.cvEducationDao = cvEducationDao;
		this.modelMapper = modelMapper;
	}

	private List<CreateCvDto> dtoGenerator(List<CvEducation> posting) {
		return posting.stream().map(adv -> modelMapper.map(adv, CreateCvDto.class)).collect(Collectors.toList());
	}

	@Override
	public Result addAll(List<CvEducation> cvEducation) {
		cvEducationDao.saveAll(cvEducation);
		return new SuccessResult();
	}

	@Override
	public Result add(CreateCvDto createCvDto) {
		CvEducation cvEducation = this.modelMapper.map(createCvDto, CvEducation.class);
		this.cvEducationDao.save(cvEducation);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<CvEducation>> getAll() {
		return new SuccessDataResult<>(this.cvEducationDao.findAll());
	}

	@Override
	public DataResult<List<CvEducation>> getAllByJobSeekerIdOrderByGraduationDateDesc(int jobSeekerId) {
		return new SuccessDataResult<>(this.cvEducationDao.getAllByJobSeekerIdOrderByGraduationDateDesc(jobSeekerId),
				"Listed");
	}

	@Override
	public DataResult<List<CvEducation>> getAllByJobSeekerId(int id) {
		return new SuccessDataResult<>(this.cvEducationDao.getAllByJobSeekerId(id));
	}
}
