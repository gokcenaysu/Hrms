package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LinkService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LinkDao;
import kodlamaio.hrms.entities.concretes.Link;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

@Service
public class LinkManager implements LinkService {

	private LinkDao linkDao;
	private ModelMapper modelMapper;

	@Autowired
	public LinkManager(LinkDao linkDao, ModelMapper modelMapper) {
		this.linkDao = linkDao;
		this.modelMapper = modelMapper;
	}

	private List<CreateCvDto> dtoGenerator(List<Link> posting) {
		return posting.stream().map(adv -> modelMapper.map(adv, CreateCvDto.class)).collect(Collectors.toList());
	}

	@Override
	public Result add(CreateCvDto createCvDto) {
		Link link = this.modelMapper.map(createCvDto, Link.class);
		this.linkDao.save(link);
		return new SuccessResult();
	}

	@Override
	public Result add(Link link) {
		this.linkDao.save(link);
		return new SuccessResult("Link added");
	}

	@Override
	public Result addAll(List<Link> link) {
		linkDao.saveAll(link);
		return new SuccessResult();
	}
	
	@Override
	public Result delete(Link link) {
		this.linkDao.delete(link);
		return new SuccessResult("Deletion is successful");
	}

	@Override
	public DataResult<List<Link>> getAll() {
		return new SuccessDataResult<>(this.linkDao.findAll());
	}

	@Override
	public DataResult<List<Link>> getAllByJobSeekerId(int jobSeekerId) {
		return new SuccessDataResult<>(this.linkDao.getAllByLinkId(jobSeekerId));
	}

	@Override
	public DataResult<Link> getById(int id) {
		return new SuccessDataResult<Link>(this.linkDao.getOne(id));
	}
}
