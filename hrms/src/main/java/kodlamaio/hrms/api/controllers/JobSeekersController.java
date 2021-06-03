package kodlamaio.hrms.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.CreateCvDto;
import kodlamaio.hrms.entities.dtos.JobSeekerRegisterDto;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekersController {
	
	private JobSeekerService jobSeekerService;
	private CurriculumVitaeService curriculumVitaeService;

	@Autowired
	public JobSeekersController(JobSeekerService jobSeekerService,  CurriculumVitaeService curriculumVitaeService) {
		super();
		this.jobSeekerService = jobSeekerService;
		this.curriculumVitaeService=curriculumVitaeService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobSeeker>> getAll(){
		return this.jobSeekerService.getAll();
	}
		
	@PostMapping("/register")
	public Result register(@RequestBody JobSeekerRegisterDto jobSeekerDto) {
		return this.jobSeekerService.register(jobSeekerDto);
	}
}
	/*
	@PostMapping("/update")
	public Result update(@RequestBody JobSeekerRegisterDto jobSeekerDto, int userId) {
		return this.jobSeekerService.update(jobSeekerDto, userId);
	}*/

