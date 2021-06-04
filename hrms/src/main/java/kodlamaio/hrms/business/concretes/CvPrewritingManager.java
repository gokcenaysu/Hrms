package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CvPrewritingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvPrewritingDao;
import kodlamaio.hrms.entities.concretes.CvPrewriting;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

@Service
public class CvPrewritingManager implements CvPrewritingService {

	private CvPrewritingDao cvPrewritingDao;
	private ModelMapper modelMapper;

	@Autowired
	public CvPrewritingManager(CvPrewritingDao cvPrewritingDao, ModelMapper modelMapper) {
		this.cvPrewritingDao = cvPrewritingDao;
		this.modelMapper = modelMapper;
	}

	private List<CreateCvDto> dtoGenerator(List<CvPrewriting> posting) {
		return posting.stream().map(adv -> modelMapper.map(adv, CreateCvDto.class)).collect(Collectors.toList());
	}

	@Override
	public Result addAll(List<CvPrewriting> cvPrewriting) {
		cvPrewritingDao.saveAll(cvPrewriting);
		return new SuccessResult();
	}

	@Override
	public Result add(CreateCvDto createCvDto) {
		CvPrewriting cvPrewriting = this.modelMapper.map(createCvDto, CvPrewriting.class);
		this.cvPrewritingDao.save(cvPrewriting);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<CvPrewriting>> getAllByJobSeekerId(int id) {
		return new SuccessDataResult<>(this.cvPrewritingDao.getAllByJobSeekerId(id));
	}

}
