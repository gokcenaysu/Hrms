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

private VerificationService emailVerificationService;
	
	@Autowired
	public VerificationsController(VerificationService emailVerificationService) {
		super();
		this.emailVerificationService = emailVerificationService;
	}
	
	/*
	@PostMapping("/update/{verification}/{id}")
	public Result verified(@RequestParam String randomVerify,@RequestParam int id) {
		return emailVerificationService.isVerified(randomVerify, id);
	}*/
}
