package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.PhotographService;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.concretes.Photograph;

@RestController
@RequestMapping("/api/photographs")
public class PhotographsController {

	private PhotographService photographService;

	@Autowired
	public PhotographsController(PhotographService photographService) {
		super();
		this.photographService = photographService;
	}
	
	 @PostMapping(value = "/add")
	    public ResponseEntity<?> add(@RequestBody MultipartFile file,@RequestParam int userId) {
	        Photograph photograph = new Photograph();
	        User user = new User();
	        user.setId(userId);
	        photograph.setUser(user);
	        return ResponseEntity.ok(this.photographService.add(photograph,file));
	    }


	    @GetMapping("/getall")
	    public ResponseEntity<?> getAll(){
	        return ResponseEntity.ok(this.photographService.getAll());
	    }
}
