package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CvEducation;

public interface CvEducationDao extends JpaRepository<CvEducation, Integer> {

	List<CvEducation> getAllByJobSeekerId(int id);

	List<CvEducation> getAllByJobSeekerIdOrderByGraduationDateDesc(int jobSeekerId);
}
