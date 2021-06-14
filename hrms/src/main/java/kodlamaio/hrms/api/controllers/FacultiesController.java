package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.FacultyService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.Faculty;

@RestController
@RequestMapping("/api/faculties")
@CrossOrigin
public class FacultiesController {

	private FacultyService facultyService;

	@Autowired
	public FacultiesController(FacultyService facultyService) {
		super();
		this.facultyService = facultyService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Faculty>> getAll(){
		return this.facultyService.getAll();
	}
}
