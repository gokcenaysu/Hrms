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
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}


	@GetMapping("/getall")
	public List<User> getAll(){
		return this.userService.getAll();
	}
	
	@PostMapping("/verification")
	public Result emailVerification(@RequestParam User user, String confirmPassword) {
		return this.userService.emailVerification(user, confirmPassword);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody User user) {
		return this.userService.delete(user);
		
	}
}
