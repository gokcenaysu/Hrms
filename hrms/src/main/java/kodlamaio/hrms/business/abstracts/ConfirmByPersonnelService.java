package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.Employer;

public interface ConfirmByPersonnelService {

	boolean isConfirmedByPersonnel(Employer employer);
}
