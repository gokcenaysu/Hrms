package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.entities.dtos.CreateCvDto;

@RestController
@RequestMapping("/api/cv")
public class CurriculumVitaeController {

	@Autowired
	private CurriculumVitaeService curriculumVitaeService;
	
	 public CurriculumVitaeController(CurriculumVitaeService curriculumVitaeService) {
		super();
		this.curriculumVitaeService = curriculumVitaeService;
	}

	@PostMapping("/createCv")
	    public ResponseEntity<?> add(@RequestBody @Valid CreateCvDto createCvDto, int jobSeekerId) {
	        return ResponseEntity.ok(curriculumVitaeService.add(createCvDto,jobSeekerId));
	    }
}
