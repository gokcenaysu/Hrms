package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CvLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvLanguageDao;
import kodlamaio.hrms.entities.concretes.CvLanguage;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

@Service
public class CvLanguageManager implements CvLanguageService {

	private CvLanguageDao cvLanguageDao;
	private ModelMapper modelMapper;

	@Autowired
	public CvLanguageManager(CvLanguageDao cvLanguageDao, ModelMapper modelMapper) {
		this.cvLanguageDao = cvLanguageDao;
		this.modelMapper = modelMapper;
	}

	private List<CreateCvDto> dtoGenerator(List<CvLanguage> posting) {
		return posting.stream().map(adv -> modelMapper.map(adv, CreateCvDto.class)).collect(Collectors.toList());
	}

	@Override
	public Result add(CreateCvDto createCvDto) {
		CvLanguage cvLanguage = this.modelMapper.map(createCvDto, CvLanguage.class);
		this.cvLanguageDao.save(cvLanguage);
		return new SuccessResult("Cv eklendi.");
	}

	@Override
	public Result addAll(List<CvLanguage> cvLanguage) {
		cvLanguageDao.saveAll(cvLanguage);
		return new SuccessResult("Cv eklendi");
	}

	@Override
	public DataResult<List<CvLanguage>> getAll() {
		return new SuccessDataResult<>(this.cvLanguageDao.findAll());
	}

	@Override
	public DataResult<List<CvLanguage>> getAllByJobSeekerId(int jobSeekerId) {
		return new SuccessDataResult<>(this.cvLanguageDao.getAllByCvLanguageId(jobSeekerId));
	}
}
