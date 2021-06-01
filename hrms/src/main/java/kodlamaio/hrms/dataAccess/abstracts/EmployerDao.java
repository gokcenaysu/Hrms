package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlamaio.hrms.entities.concretes.Employer;

@Repository
public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	Employer findByEmailEquals(String findEmail);
	Employer findByWebsiteEquals(String findWebsite);
	Employer findByPhoneNumberEquals(String findPhoneNumber);
}
