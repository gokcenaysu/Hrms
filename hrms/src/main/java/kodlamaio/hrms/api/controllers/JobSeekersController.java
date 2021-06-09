package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.CreateCvDto;
import kodlamaio.hrms.entities.dtos.JobSeekerRegisterDto;

@RestController
@RequestMapping("/api/jobseekers")
@CrossOrigin
public class JobSeekersController {

	private JobSeekerService jobSeekerService;

	@Autowired
	public JobSeekersController(JobSeekerService jobSeekerService) {
		super();
		this.jobSeekerService = jobSeekerService;
	}

	@GetMapping("/getall")
	public DataResult<List<JobSeeker>> getAll() {
		return this.jobSeekerService.getAll();
	}

	@PostMapping("/register")
	public Result register(@RequestBody JobSeekerRegisterDto jobSeekerDto) {
		return this.jobSeekerService.register(jobSeekerDto);
	}
	
	/*
	@PostMapping("/update")
	public Result update(@RequestBody JobSeekerRegisterDto jobSeekerDto) {
		return this.jobSeekerService.update(jobSeekerDto);
	}*/
	
	@GetMapping("/getCv")
	public DataResult<CreateCvDto> getCvById(@RequestParam int id){
		return (this.jobSeekerService.getCvById(id));
	}
}
