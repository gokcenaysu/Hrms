package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.PhotographService;
import kodlamaio.hrms.core.dataAccess.UserDao;
import kodlamaio.hrms.core.imageUploaders.ImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.dataAccess.abstracts.PhotographDao;
import kodlamaio.hrms.entities.concretes.Photograph;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

@Service
public class PhotographManager implements PhotographService {

	private PhotographDao photographDao;
	private ImageService imageService;
	private ModelMapper modelMapper;

	@Autowired
	public PhotographManager(PhotographDao photographDao, ImageService imageService) {
		this.photographDao = photographDao;
		this.imageService = imageService;
	}

	private List<CreateCvDto> dtoGenerator(List<CreateCvDto> posting) {
		return posting.stream().map(adv -> modelMapper.map(adv, CreateCvDto.class)).collect(Collectors.toList());
	}

	@Override
	public Result add(Photograph photograph) {
		this.photographDao.save(photograph);
		return new SuccessResult("Photo added");
	}

	@Override
	public Result addAll(List<Photograph> photograph) {
		photographDao.saveAll(photograph);
		return new SuccessResult();
	}

	@Override
	public Result add(Photograph photograph, MultipartFile file) {
		Map<String, String> result = (Map<String, String>) imageService.save(file).getData();
		String url = result.get("url");
		photograph.setPhotographLink(url);
		photograph.setCreatedOn(LocalDateTime.now());
		return add(photograph);
	}

	@Override
	public Result delete(Photograph photograph) {
		this.photographDao.delete(photograph);
		return new SuccessResult("Deletion is successful");
	}

	@Override
	public DataResult<List<Photograph>> getAll() {
		return new SuccessDataResult<>(this.photographDao.findAll());
	}

	@Override
	public DataResult<List<Photograph>> getAllByUserId(int userId) {
		return new SuccessDataResult<>(this.photographDao.getAllByPhotographId(userId));
	}

	@Override
	public DataResult<Photograph> getById(int photographId) {
		return new SuccessDataResult<Photograph>(this.photographDao.getOne(photographId));
	}
}
