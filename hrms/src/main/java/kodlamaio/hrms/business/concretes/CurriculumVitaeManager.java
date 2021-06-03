package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.business.abstracts.CvEducationService;
import kodlamaio.hrms.business.abstracts.CvExperienceService;
import kodlamaio.hrms.business.abstracts.CvLanguageService;
import kodlamaio.hrms.business.abstracts.CvSkillService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.LinkService;
import kodlamaio.hrms.business.abstracts.PhotographService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.CvEducation;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.Link;
import kodlamaio.hrms.entities.concretes.Photograph;
import kodlamaio.hrms.entities.dtos.CreateCvDto;
import kodlamaio.hrms.entities.dtos.JobPostingDto;

@Service
public class CurriculumVitaeManager implements CurriculumVitaeService {

	private final CurriculumVitaeDao curriculumVitaeDao;
	private final CvEducationService cvEducationService;
	private final CvExperienceService cvExperienceService;
	private final CvLanguageService cvLanguageService;
	private final CvSkillService cvSkillService;
	private final PhotographService photographService;
	private final LinkService linkService;
	private ModelMapper modelMapper;
	private JobSeekerDao jobSeekerDao;
	private JobSeekerService jobSeekerService;

	@Autowired
	public CurriculumVitaeManager(CurriculumVitaeDao curriculumVitaeDao,
			CvEducationService cvEducationService, CvExperienceService cvExperienceService,
			CvLanguageService cvLanguageService, CvSkillService cvSkillService, 
			PhotographService photographService, LinkService linkService, JobSeekerDao jobSeekerDao,
			ModelMapper modelMapper, JobSeekerService jobSeekerService) {
		super();
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.cvEducationService = cvEducationService;
		this.cvExperienceService = cvExperienceService;
		this.cvLanguageService = cvLanguageService;
		this.cvSkillService = cvSkillService;
		this.photographService = photographService;
		this.linkService = linkService;
		this.modelMapper = modelMapper;
		this.jobSeekerDao=jobSeekerDao;
		this.jobSeekerService=jobSeekerService;
	}

	@Override
	public DataResult<CreateCvDto> getResumeByJobSeekerId(int jobSeekerId) {
		CreateCvDto createCvDto = new CreateCvDto();
		createCvDto.setJobSeeker(this.getById(jobSeekerId).getData());
		createCvDto.setCvEducations(this.cvEducationService.getAllByJobSeekerIdOrderByGraduationYear(jobSeekerId).getData());
		createCvDto.setCvExperiences(
				this.cvExperienceService.getAllByJobSeekerIdOrderByJobEndingDate(jobSeekerId).getData());
		createCvDto.setCvLanguages(	 this.cvLanguageService.getAllByJobSeekerId(jobSeekerId).getData());
		createCvDto.setCvSkills(this.cvSkillService.getAllByJobSeekerId(jobSeekerId).getData());
		createCvDto.setPhotograph(this.photographService.getAllByUserId(jobSeekerId).getData());
		createCvDto.setLink(this.linkService.getAllByJobSeekerId(jobSeekerId).getData());
		return new SuccessDataResult<>(createCvDto);
	}

	 @Override
	    public Result add(CreateCvDto createCvDto, int jobSeekerId) {
	        JobSeeker jobSeeker = jobSeekerService.getById(jobSeekerId).getData();
	        createCvDto.setJobSeeker(jobSeeker);
	        CurriculumVitae curriculumVitae = curriculumVitaeDao.getById(1);
	        
	        
	        createCvDto.getCvEducations().forEach(cvEducation -> cvEducation.setCurriculumVitae(curriculumVitae));
	        cvEducationService.addAll(createCvDto.getCvEducations());

	        createCvDto.getCvExperiences().forEach(cvExperience -> cvExperience.setCurriculumVitae(curriculumVitae));
	        cvExperienceService.addAll(createCvDto.getCvExperiences());

	        createCvDto.getCvLanguages().forEach(cvLanguage -> cvLanguage.setCurriculumVitae(curriculumVitae));
	        cvLanguageService.addAll(createCvDto.getCvLanguages());
	        
	        createCvDto.getCvSkills().forEach(cvSkill -> cvSkill.setCurriculumVitae(curriculumVitae));
	        cvSkillService.addAll(createCvDto.getCvSkills());
	        
	        createCvDto.getPhotograph().forEach(photograph -> photograph.setUser(jobSeeker));
	        photographService.addAll(createCvDto.getPhotograph());

	        createCvDto.getLink().forEach(link -> link.setUser(jobSeeker));
	        linkService.addAll(createCvDto.getLink());

	        createCvDto.setCurriculumVitae(curriculumVitae);
	        curriculumVitae.setJobSeeker(jobSeeker);

	        return new SuccessResult("CV created");
	    }
	 
	 @Override
	    public DataResult<JobSeeker> getById(int id) {
	        return new SuccessDataResult<>(this.jobSeekerDao.findById(id).get());
	    }
	 
	 
	 
	/*
	 * private List<CreateCvDto> dtoGenerator(List<CurriculumVitae> createCv) {
	 * return createCv.stream().map(adv -> modelMapper.map(adv,
	 * CreateCvDto.class)).collect(Collectors.toList()); }
	 * 
	 * @Override public DataResult<List<CreateCvDto>> getAll() { return new
	 * SuccessDataResult<List<CreateCvDto>>(this.dtoGenerator(this.
	 * curriculumVitaeDao.findAll())); }
	 */
}
