package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.adapters.abstracts.RegexService;
import kodlamaio.hrms.core.adapters.abstracts.VerificationService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService{

	private UserDao userDao;
	private VerificationService verificationService;
	private RegexService regexService;

	
	@Autowired
	public UserManager(UserDao userDao, RegexService regexService, VerificationService verificationService) {
		super();
		this.userDao=userDao;
		this.regexService=regexService;
		this.verificationService=verificationService;
	}
	
	
	@Override
	public List<User> getAll() {
		return this.userDao.findAll();
	}
	
	@Override
	public Result delete(User user) {
		if(!regexService.isEmailFormat(user.getEmail())) {
			return new ErrorResult("Deletion is unsuccessful. Please enter in e-mail format");

		}
		else {
		this.userDao.delete(user);
		return new SuccessResult("Deletion is successful");
		}
	}



	@Override
	public Result confirmActivation(String email, String activationCode) {
		User user = this.userDao.findUserByEmail(email);
		if(user != null) {
			if(!user.isStatus() && !user.getEmail().equals(activationCode)) {
			//	if(user.getActivationCode().equals(activationCode) && !user.isDeleted()) {
					user.setStatus(true);;
					userDao.save(user);
					return new SuccessResult("no");
				}
			}
			return new SuccessResult("ok");
		}
	}

