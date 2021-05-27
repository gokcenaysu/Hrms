package kodlamaio.hrms.core.adapters.abstracts;

import kodlamaio.hrms.entities.concretes.Employer;

public interface ConfirmByPersonnelService {

	boolean isConfirmedByPersonnel(Employer employer);
}
