package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Photograph;

public interface PhotographDao extends JpaRepository<Photograph, Integer> {

	List<Photograph> getAllByPhotographId(int userId);

}
