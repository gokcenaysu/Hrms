package kodlamaio.hrms.core.adapters.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.adapters.abstracts.ConfirmByPersonnelService;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class ConfirmByPersonnelManager implements ConfirmByPersonnelService{

	@Override
	public boolean isConfirmedByPersonnel(Employer employer) {
		System.out.print("The" + employer.getCompanyName() + "company has been confirmed by the personnel");
		return true;
	}
		
}
