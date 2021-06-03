package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlamaio.hrms.entities.concretes.Employer;

@Repository
public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	Employer getByEmailEquals(String email);
	Employer getByWebsiteEquals(String website);
	Employer getByPhoneNumberEquals(String phoneNumber);
}
