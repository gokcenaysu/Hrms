package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.adapters.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService{

	private UserDao userDao;
	private EmailVerificationService emailVerificationService;
	
	@Autowired
	public UserManager(UserDao userDao, EmailVerificationService emailVerificationService) {
		super();
		this.userDao=userDao;
		this.emailVerificationService=emailVerificationService;
	}
	
	
	@Override
	public List<User> getAll() {
		return this.userDao.findAll();
	}


	@Override
	public Result emailVerify(User user) {
		if(!emailVerificationService.isEmailVerifyClicked(user.getEmail())) {
			return new ErrorResult("Verify your e-mail");
	   }
		return null;
	}

	@Override
	public Result delete(User user) {
		this.userDao.delete(user);
		return new SuccessResult("Deletion is successful");
	}
}
