package kodlamaio.hrms.business.concretes;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerPersonnelConfirmService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerPersonnelConfirmDao;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.EmployerPersonnelConfirm;

@Service
public class EmployerPersonnelConfirmManager implements EmployerPersonnelConfirmService {

	private EmployerPersonnelConfirmDao employerPersonnelConfirmDao;
	private EmployerDao employerDao;
	
	@Autowired
	public EmployerPersonnelConfirmManager(EmployerPersonnelConfirmDao employerPersonnelConfirmDao,
			EmployerDao employerDao) {
		super();
		this.employerPersonnelConfirmDao = employerPersonnelConfirmDao;
		this.employerDao = employerDao;
	}

	EmployerPersonnelConfirm user = new EmployerPersonnelConfirm();

	@Override
	public void createConfirm(Employer employer) {
		user.setEmployer(employer);
		user.setPersonnelId(1);
		this.employerPersonnelConfirmDao.save(user);
	}

	@Override
	public Result isConfirmedByPersonnel(int userId) {
		if(!employerDao.existsById(userId)) {
			return new ErrorResult("Not present");
		}
		if(employerDao.getById(userId).isStatus()) {
			return new ErrorResult("Already confirmed");
		}
		
		Employer employer = new Employer();
		employer=employerDao.getById(userId);
		employer.setStatus(true);
		employerDao.save(employer);
		user=employerPersonnelConfirmDao.getByEmployerId(employer.getId());
		user.setConfirmed(true);
		user.setConfirmDate(LocalDateTime.now());
		employerPersonnelConfirmDao.save(user);
		return new SuccessResult("Successful");
	}

	
}
