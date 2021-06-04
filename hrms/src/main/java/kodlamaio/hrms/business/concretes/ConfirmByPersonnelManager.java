package kodlamaio.hrms.business.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ConfirmByPersonnelService;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class ConfirmByPersonnelManager implements ConfirmByPersonnelService {

	@Override
	public boolean isConfirmedByPersonnel(Employer employer) {
		return true;
	}

}
