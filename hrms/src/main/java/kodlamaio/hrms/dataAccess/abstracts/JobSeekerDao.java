package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.JobSeekerRegisterDto;

@Repository
public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer>{
	
	JobSeeker findByEmailEquals(String findEmail);
	JobSeeker findByIdentityNumberEquals(String findIdentityNumber);
	JobSeeker save(JobSeekerRegisterDto jobSeekerRegisterDto);
}
