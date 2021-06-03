package kodlamaio.hrms.business.abstracts;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.concretes.Photograph;

public interface PhotographService {

	Result add(Photograph Photograph);
	Result addAll(List<Photograph> Photograph);
	Result add(Photograph Photograph, MultipartFile file);
	DataResult<List<Photograph>> getAll();
	DataResult<List<Photograph>> getAllByUserId(int userId);
	DataResult<Photograph> imageUpload(int userId, MultipartFile file) throws IOException;

}
