package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.PostingPersonnelConfirm;


public interface PostingPersonnelConfirmDao extends JpaRepository<PostingPersonnelConfirm, Integer>{

	PostingPersonnelConfirm getByJobPostingId (int id);

	//boolean existsByJobPostingId(int id);
}
