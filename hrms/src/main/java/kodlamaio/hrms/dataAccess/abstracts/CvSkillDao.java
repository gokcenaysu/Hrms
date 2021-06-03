package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CvSkill;

public interface CvSkillDao extends JpaRepository<CvSkill, Integer>{

	List<CvSkill> getAllByCurriculumVitae(int cvId);
}
