package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.HrmsPersonnel;

public interface HrmsPersonnelService {
	
	DataResult<List<HrmsPersonnel>> getAll();

}
