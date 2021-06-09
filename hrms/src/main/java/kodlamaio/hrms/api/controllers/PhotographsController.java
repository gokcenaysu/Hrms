package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.PhotographService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.Photograph;

@RestController
@RequestMapping("/api/photographs")
@CrossOrigin
public class PhotographsController {

	private PhotographService photographService;

	@Autowired
	public PhotographsController(PhotographService photographService) {
		super();
		this.photographService = photographService;
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@RequestBody MultipartFile file, @RequestParam int jobSeekerId) {
		Photograph photograph = new Photograph();
		JobSeeker jobSeeker = new JobSeeker();
		jobSeeker.setId(jobSeekerId);
		photograph.setJobSeeker(jobSeeker);
		return ResponseEntity.ok(this.photographService.add(photograph, file));
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody Photograph photograph) {
		return this.photographService.delete(photograph);
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.photographService.getAll());
	}
}
