package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerPersonnelConfirmService {

	void createConfirm(Employer employer);

	Result isConfirmedByPersonnel(int userId);
}
