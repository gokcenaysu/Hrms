package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.core.adapters.abstracts.VerificationService;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/verifications")
public class VerificationsController {

private VerificationService verificationService;
	
	@Autowired
	public VerificationsController(VerificationService verificationService) {
		super();
		this.verificationService = verificationService;
	}
	
}
