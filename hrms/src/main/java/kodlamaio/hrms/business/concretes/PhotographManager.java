package kodlamaio.hrms.business.concretes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.PhotographService;
import kodlamaio.hrms.core.dataAccess.UserDao;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.imageUploaders.ImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.PhotographDao;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.concretes.Photograph;

@Service
public class PhotographManager implements PhotographService {

	private final PhotographDao photographDao;
	private final ImageService imageService;
	private UserDao userDao;

	@Autowired
	public PhotographManager(PhotographDao photographDao, ImageService imageService,UserDao userDao){
		this.photographDao = photographDao;
		this.imageService = imageService;
		this.userDao = userDao;
	}

	@Override
	public Result add(Photograph photograph) {
		this.photographDao.save(photograph);
		return new SuccessResult();
	}

	@Override
	public Result addAll(List<Photograph> candidateImage) {
		photographDao.saveAll(candidateImage);
		return new SuccessResult();
	}

	@Override
	public Result add(Photograph photograph, MultipartFile file) {
		Map<String, String> result = (Map<String, String>) imageService.save(file).getData();
		String url = result.get("url");
		photograph.setPhotographLink(url);
		photograph.setUpdatedOn(LocalDateTime.now());
		return add(photograph);
	}

	@Override
	public DataResult<Photograph> imageUpload(int userId, MultipartFile
			  file) throws IOException { var photograph=this.photographDao.getById(userId);
			  var imageUrl=uploadImageToCloudinary(file,photograph.getPhotographLink());
			  photograph.setPhotographLink("error"); return new
			  SuccessDataResult<>(this.photographDao.save(photograph)); }

	private Result uploadImageToCloudinary( MultipartFile file, String imageUrl)
			  throws IOException { var result = this.imageService.upload(file);
			  if(!result.isSuccess()){ return new ErrorResult("error"); } if(imageUrl !=
			  null){ var imageId = imageUrl.split(("/"))[imageUrl.split(("/")).length -
			  1].split("\\.")[0]; this.imageService.delete(imageId); } var url =
			  result.getData().get("url"); return new SuccessResult(url.toString()); }

	@Override
	public DataResult<List<Photograph>> getAll() {
		return new SuccessDataResult<>(this.photographDao.findAll());
	}

	@Override
	public DataResult<List<Photograph>> getAllByUserId(int userId){
		return new SuccessDataResult<>(this.photographDao.getAllByPhotographId(userId));
	}

	/*
	 * private UserDao userDao; private PhotographDao photographDao; private
	 * ImageService imageService;
	 * 
	 * @Autowired public PhotographManager(UserDao userDao, PhotographDao
	 * photographDao, ImageService imageService) { super(); 
	 * this.photographDao=photographDao; this.imageService=imageService; }
	 * 
	 * 
	 * @Override public DataResult<Photograph> imageUpload(int userId, MultipartFile
	 * file) throws IOException { var photograph=this.photographDao.getById(userId);
	 * var imageUrl=uploadImageToCloudinary(file,photograph.getPhotographLink());
	 * photograph.setPhotographLink("error"); return new
	 * SuccessDataResult<>(this.photographDao.save(photograph)); }
	 * 
	 * private Result uploadImageToCloudinary( MultipartFile file, String imageUrl)
	 * throws IOException { var result = this.imageService.upload(file);
	 * if(!result.isSuccess()){ return new ErrorResult("error"); } if(imageUrl !=
	 * null){ var imageId = imageUrl.split(("/"))[imageUrl.split(("/")).length -
	 * 1].split("\\.")[0]; this.imageService.delete(imageId); } var url =
	 * result.getData().get("url"); return new SuccessResult(url.toString()); }
	 * 
	 * 
	 * @Override public List<Photograph> getAll() { return
	 * this.photographDao.findAll(); }
	 * 
	 * 
	 * @Override public Result post(Photograph photograph) {
	 * this.photographDao.save(photograph); return new
	 * SuccessResult("The job posting has been successfully added"); }
	 */
}
