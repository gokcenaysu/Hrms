package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.adapters.abstracts.VerificationService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UserService userService;
	private VerificationService verificationService;
	
	@Autowired
	public UsersController(UserService userService, VerificationService verificationService) {
		super();
		this.userService = userService;
		this.verificationService=verificationService;
	}


	@GetMapping("/getall")
	public List<User> getAll(){
		return this.userService.getAll();
	}
	
	@PostMapping("/update/{verification}/{id}")
	public Result verified(@RequestParam String randomVerify,@RequestParam int id) {
		return verificationService.isVerified(randomVerify, id);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody User user) {
		return this.userService.delete(user);
		
	}
}
