package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CvEducationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.CvEducation;
import kodlamaio.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/cv-education")
public class CvEducationsController {

	private CvEducationService cvEducationService;
	
	@Autowired
	public CvEducationsController(CvEducationService cvEducationService) {
		super();
		this.cvEducationService = cvEducationService;
	}


	@GetMapping("/getGraduationDateDesc")
	public DataResult<List<CvEducation>> getAllByJobSeekerIdOrderByGraduationYear(@RequestParam int jobSeekerId){
		return this.cvEducationService.getAllByJobSeekerIdOrderByGraduationYear(jobSeekerId);
	}
}
