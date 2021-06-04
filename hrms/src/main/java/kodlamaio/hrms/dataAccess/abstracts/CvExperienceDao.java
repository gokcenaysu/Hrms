package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CvExperience;

public interface CvExperienceDao extends JpaRepository<CvExperience, Integer> {

	List<CvExperience> getAllByJobSeekerId(int id);

	List<CvExperience> getAllByJobSeekerIdOrderByJobEndingDateDesc(int jobSeekerId);
}
