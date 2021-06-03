package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.EmployerRegisterDto;

public interface EmployerService {
	
	DataResult<List<Employer>> getAll();
	Result register(EmployerRegisterDto employer);
	Result confirmByPersonnel(Employer employer);
	DataResult<Employer> getById(int userId);
	
	
	//Result update(Employer employer, int userId);
}
