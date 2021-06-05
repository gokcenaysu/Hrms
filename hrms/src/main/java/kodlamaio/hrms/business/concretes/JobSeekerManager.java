package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CvEducationService;
import kodlamaio.hrms.business.abstracts.CvExperienceService;
import kodlamaio.hrms.business.abstracts.CvLanguageService;
import kodlamaio.hrms.business.abstracts.CvPrewritingService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.LinkService;
import kodlamaio.hrms.business.abstracts.PhotographService;
import kodlamaio.hrms.core.adapters.abstracts.RegexService;
import kodlamaio.hrms.core.adapters.abstracts.SimulatedMernisService;
import kodlamaio.hrms.core.adapters.abstracts.VerificationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.dataAccess.abstracts.CvSkillDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.Verification;
import kodlamaio.hrms.entities.dtos.CreateCvDto;
import kodlamaio.hrms.entities.dtos.JobSeekerRegisterDto;

@Service
public class JobSeekerManager implements JobSeekerService {

	private ModelMapper modelMapper;
	private JobSeekerDao jobSeekerDao;
	private CvLanguageService cvLanguageService;
	private CvExperienceService cvExperienceService;
	private LinkService linkService;
	private PhotographService photographService;
	private CvSkillDao cvSkillService;
	private CvPrewritingService cvPrewritingService;
	private CvEducationService cvEducationService;
	private VerificationService verificationService;
	private SimulatedMernisService simulatedMernisService;
	private RegexService regexService;

	@Autowired
	public JobSeekerManager(ModelMapper modelMapper, JobSeekerDao jobSeekerDao, CvLanguageService cvLanguageService,
			CvExperienceService cvExperienceService, LinkService linkService, PhotographService photographService,
			CvSkillDao cvSkillService, CvEducationService cvEducationService, VerificationService verificationService,
			SimulatedMernisService simulatedMernisService, RegexService regexService, CvPrewritingService cvPrewritingService) {
		super();
		this.modelMapper = modelMapper;
		this.jobSeekerDao = jobSeekerDao;
		this.cvLanguageService = cvLanguageService;
		this.cvExperienceService = cvExperienceService;
		this.linkService = linkService;
		this.photographService = photographService;
		this.cvSkillService = cvSkillService;
		this.cvPrewritingService=cvPrewritingService;
		this.cvExperienceService = cvExperienceService;
		this.cvEducationService = cvEducationService;
		this.verificationService = verificationService;
		this.simulatedMernisService = simulatedMernisService;
		this.regexService = regexService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "Listed");
	}

	@Override
	public Result register(JobSeekerRegisterDto jobSeekerDto) {

		JobSeeker jobSeeker = this.modelMapper.map(jobSeekerDto, JobSeeker.class);

		if (jobSeeker.getFirstName().isEmpty() || jobSeeker.getLastName().isEmpty()
				|| jobSeeker.getIdentityNumber().isEmpty() || jobSeeker.getBirthYear().isEmpty()
				|| jobSeeker.getEmail().isEmpty() || jobSeeker.getPassword().isEmpty()
				|| jobSeeker.getFirstName().isBlank() || jobSeeker.getLastName().isBlank()
				|| jobSeeker.getPassword().isBlank()) {
			return new ErrorResult("Fields cannot be empty");
		}

		if (!regexService.isFirstNameFormat(jobSeeker.getFirstName())) {
			return new ErrorResult("Your name must start with a capital letter");
		}

		if (!regexService.isLastNameFormat(jobSeeker.getLastName())) {
			return new ErrorResult("Your surname must start with a capital letter");
		}

		if (!regexService.isBirthYearFormat(jobSeeker.getBirthYear())) {
			return new ErrorResult("Please enter in birth year format");
		}

		if (!regexService.isEmailFormat(jobSeeker.getEmail())) {
			return new ErrorResult("Please enter in e-mail format");
		}

		if (!regexService.isPasswordFormat(jobSeeker.getPassword())) {
			return new ErrorResult("Enter a value in the range of 1-30");
		}

		if (!simulatedMernisService.checkMernis(jobSeeker.getFirstName(), jobSeeker.getLastName(),
				jobSeeker.getIdentityNumber(), jobSeeker.getBirthYear())) {
			return new ErrorResult("Authentication unsuccessful");
		}

		if (jobSeekerDao.getByEmailEquals(jobSeeker.getEmail()) != null) {
			return new ErrorResult("E-mail already registered");
		}

		if (jobSeekerDao.getByIdentityNumberEquals(jobSeeker.getIdentityNumber()) != null) {
			return new ErrorResult("ID number already registered");
		}

		else {
			jobSeeker.setStatus(false);
			jobSeekerDao.save(jobSeeker);
			String code = verificationService.verifyCode();
			verificationCode(code, jobSeeker.getId(), jobSeeker.getEmail());
			return new SuccessResult("Your registration has been created successfully");
		}
	}

	public void verificationCode(String code, int id, String email) {
		Verification verificationCode = new Verification(id, code, false, LocalDateTime.now());
		this.verificationService.save(verificationCode);
	}

	@Override
	public DataResult<CreateCvDto> getCvById(int id) {
		CreateCvDto createCvDto = new CreateCvDto();
		createCvDto.setJobSeeker(this.getById(id).getData());
		createCvDto.setPhotograph(this.photographService.getAllByUserId(id).getData());
		createCvDto.setCvLanguage(this.cvLanguageService.getAllByJobSeekerId(id).getData());
		createCvDto.setLink(this.linkService.getAllByJobSeekerId(id).getData());
		createCvDto.setCvSkill(this.cvSkillService.getAllByJobSeekerId(id));
		createCvDto.setCvEducation(this.cvEducationService.getAllByJobSeekerId(id).getData());
		createCvDto.setCvExperience(this.cvExperienceService.getAllByJobSeekerId(id).getData());
		createCvDto.setCvPrewriting(this.cvPrewritingService.getAllByJobSeekerId(id).getData());
		return new SuccessDataResult<>(createCvDto, "Listed");
	}

	/*
	 * @Override public Result update(JobSeekerRegisterDto jobSeekerDto, int userId)
	 * {
	 * 
	 * JobSeeker jobSeeker = this.modelMapper.map(jobSeekerDto, JobSeeker.class);
	 * JobSeeker jobSeekers = getById(userId).getData();
	 * 
	 * if(jobSeeker.getBirthYear().isEmpty() || jobSeeker.getFirstName().isEmpty()
	 * || jobSeeker.getLastName().isEmpty() || jobSeeker.getPassword().isEmpty() ||
	 * jobSeeker.getEmail().isEmpty() || jobSeeker.getFirstName().isBlank() ||
	 * jobSeeker.getLastName().isBlank() || jobSeeker.getPassword().isBlank()) {
	 * return new ErrorResult("Fields cannot be empty"); }
	 * if(!regexService.isFirstNameFormat(jobSeeker.getFirstName())) { return new
	 * ErrorResult("Please enter in name format"); }
	 * 
	 * if(!regexService.isLastNameFormat(jobSeeker.getLastName())) { return new
	 * ErrorResult("Please enter in surname format"); }
	 * 
	 * if(!regexService.isBirthYearFormat(jobSeeker.getBirthYear())) { return new
	 * ErrorResult("Please enter in birth year format"); }
	 * 
	 * if(!regexService.isEmailFormat(jobSeeker.getEmail())) { return new
	 * ErrorResult("Please enter in e-mail format"); }
	 * 
	 * if(!simulatedMernisService.checkMernis(jobSeeker.getFirstName(),
	 * jobSeeker.getLastName(), jobSeeker.getIdentityNumber(),
	 * jobSeeker.getBirthYear())) { return new
	 * ErrorResult("Authentication unsuccessful"); }
	 * 
	 * if(jobSeekerDao.getByEmailEquals(jobSeeker.getEmail())!=null) { return new
	 * ErrorResult("E-mail already registered"); }
	 * 
	 * if(jobSeekerDao.getByIdentityNumberEquals(jobSeeker.getIdentityNumber())!=
	 * null) { return new ErrorResult("ID number already registered"); }
	 * 
	 * else { jobSeekerDao.save(jobSeekerDto); return new
	 * SuccessResult("Has been updated successfully"); }
	 */

	@Override
	public DataResult<JobSeeker> getById(int jobSeekerId) {// getone kullanma burada findbyid().get kullan bu problemli
		return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.getOne(jobSeekerId));
	}
}
