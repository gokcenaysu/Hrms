package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CvPrewriting;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

public interface CvPrewritingService {

	Result add(CreateCvDto createCvDto);

	Result addAll(List<CvPrewriting> cvPrewriting);
}
