package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.HrmsPersonnelService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.HrmsPersonnel;

@RestController
@RequestMapping("/api/hrms-personnels")
@CrossOrigin
public class HrmsPersonnelsController {

	private HrmsPersonnelService hrmsPersonnelService;

	@Autowired
	public HrmsPersonnelsController(HrmsPersonnelService hrmsPersonnelService) {
		super();
		this.hrmsPersonnelService = hrmsPersonnelService;
	}

	@GetMapping("/getall")
	public DataResult<List<HrmsPersonnel>> getAll() {

		return this.hrmsPersonnelService.getAll();
	}
}
