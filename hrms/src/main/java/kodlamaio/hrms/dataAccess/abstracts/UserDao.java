package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlamaio.hrms.core.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	User findById(User user);
	User findUserByEmail(String email);
}
