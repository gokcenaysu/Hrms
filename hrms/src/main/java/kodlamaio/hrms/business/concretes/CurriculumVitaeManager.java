package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.business.abstracts.CvEducationService;
import kodlamaio.hrms.business.abstracts.CvExperienceService;
import kodlamaio.hrms.business.abstracts.CvLanguageService;
import kodlamaio.hrms.business.abstracts.CvPrewritingService;
import kodlamaio.hrms.business.abstracts.CvSkillService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.LinkService;
import kodlamaio.hrms.business.abstracts.PhotographService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

@Service
public class CurriculumVitaeManager implements CurriculumVitaeService {

	private JobSeekerService jobSeekerService;
	private CvEducationService cvEducationService;
	private CvExperienceService cvExperienceService;
	private CvLanguageService cvLanguageService;
	private CvSkillService cvSkillService;
	private PhotographService photographService;
	private LinkService linkService;
	private CvPrewritingService cvPrewritingService;

	@Autowired
	public CurriculumVitaeManager(JobSeekerService jobSeekerService, CvEducationService cvEducationService,
			CvExperienceService cvExperienceService, CvLanguageService cvLanguageService, CvSkillService cvSkillService,
			PhotographService photographService, LinkService linkService, CvPrewritingService cvPrewritingService) {
		super();
		this.jobSeekerService = jobSeekerService;
		this.cvEducationService = cvEducationService;
		this.cvExperienceService = cvExperienceService;
		this.cvLanguageService = cvLanguageService;
		this.cvSkillService = cvSkillService;
		this.photographService = photographService;
		this.linkService = linkService;
		this.cvPrewritingService = cvPrewritingService;
	}

	@Override
	public Result add(CreateCvDto createCvDto, int jobSeekerId) {
		JobSeeker jobSeeker = jobSeekerService.getById(jobSeekerId).getData();
		createCvDto.setJobSeeker(jobSeeker); // bu ileri butonları nereye gitti ya :D

		createCvDto.getCvEducation().forEach(cvEducation -> cvEducation.setJobSeeker(jobSeeker));
		cvEducationService.addAll(createCvDto.getCvEducation());

		createCvDto.getCvExperience().forEach(cvExperience -> cvExperience.setJobSeeker(jobSeeker));
		cvExperienceService.addAll(createCvDto.getCvExperience());

		createCvDto.getCvSkill().forEach(cvSkill -> cvSkill.setJobSeeker(jobSeeker));
		cvSkillService.addAll(createCvDto.getCvSkill());

		createCvDto.getCvLanguage().forEach(cvLanguage -> cvLanguage.setJobSeeker(jobSeeker));
		cvLanguageService.addAll(createCvDto.getCvLanguage());

		createCvDto.getCvPrewriting().forEach(cvPrewriting -> cvPrewriting.setJobSeeker(jobSeeker));
		cvPrewritingService.addAll(createCvDto.getCvPrewriting());

		createCvDto.getPhotograph().forEach(photograph -> photograph.setJobSeeker(jobSeeker));
		photographService.addAll(createCvDto.getPhotograph());

		createCvDto.getLink().forEach(link -> link.setJobSeeker(jobSeeker));
		linkService.addAll(createCvDto.getLink());

		return new SuccessResult("Cv added");	
	}
}
