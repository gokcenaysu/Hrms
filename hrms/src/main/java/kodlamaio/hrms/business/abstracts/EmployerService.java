package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerService {
	
	DataResult<List<Employer>> getAll();
	Result register(Employer employer);
	Result confirmByPersonnel(Employer employer);
	Result update(Employer employer, int userId);
	DataResult<Employer> getById(int userId);
}
