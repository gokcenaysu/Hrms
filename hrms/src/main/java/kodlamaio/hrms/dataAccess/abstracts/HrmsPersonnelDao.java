package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlamaio.hrms.entities.concretes.HrmsPersonnel;

@Repository
public interface HrmsPersonnelDao extends JpaRepository<HrmsPersonnel, Integer>{

}
