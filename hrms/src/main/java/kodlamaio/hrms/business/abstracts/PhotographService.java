package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Photograph;

public interface PhotographService {

	Result add(Photograph photograph);

	Result addAll(List<Photograph> Photograph);

	Result add(Photograph Photograph, MultipartFile file);

	Result delete(Photograph photograph);

	DataResult<Photograph> getById(int photographId);

	DataResult<List<Photograph>> getAll();

	DataResult<List<Photograph>> getAllByUserId(int userId);

}
