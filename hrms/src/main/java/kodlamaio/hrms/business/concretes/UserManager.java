package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.adapters.abstracts.RegexService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService{

	private UserDao userDao;
	private RegexService regexService;

	
	@Autowired
	public UserManager(UserDao userDao, RegexService regexService) {
		super();
		this.userDao=userDao;
		this.regexService=regexService;
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
}
