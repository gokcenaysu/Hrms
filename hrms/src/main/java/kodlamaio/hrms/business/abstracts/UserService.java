package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface UserService {

	List<User> getAll();
	
	Result emailVerification(User user, String activationCode);
	DataResult<User> getById(int id);
	Result delete(User user);
	

}
