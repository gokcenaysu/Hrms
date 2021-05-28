package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ConfirmByPersonnelService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.adapters.abstracts.VerificationService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService{

	private UserDao userDao;
	private VerificationService emailVerificationService;
	
	@Autowired
	public UserManager(UserDao userDao, VerificationService emailVerificationService) {
		super();
		this.userDao=userDao;
		this.emailVerificationService=emailVerificationService;
	}
	
	
	@Override
	public List<User> getAll() {
		return this.userDao.findAll();
	}

	
	@Override
	public Result delete(User user) {
		this.userDao.delete(user);
		return new SuccessResult("Deletion is successful");
	}
}
