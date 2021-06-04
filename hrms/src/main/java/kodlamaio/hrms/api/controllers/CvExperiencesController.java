package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CvExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.CvEducation;
import kodlamaio.hrms.entities.concretes.CvExperience;

@RestController
@RequestMapping("/api/cv-experiences")
public class CvExperiencesController {
	private CvExperienceService cvExperienceService;

	@Autowired
	public CvExperiencesController(CvExperienceService cvExperienceService) {
		super();
		this.cvExperienceService = cvExperienceService;
	}

	@GetMapping("/getGraduationDateDesc")
	public DataResult<List<CvExperience>> getAllByJobSeekerIdOrderByJobEndingDateDesc(@RequestParam int jobSeekerId) {
		return this.cvExperienceService.getAllByJobSeekerIdOrderByJobEndingDateDesc(jobSeekerId);
	}
}
