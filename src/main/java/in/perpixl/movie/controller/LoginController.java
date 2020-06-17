package in.perpixl.movie.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="login")
@RequestMapping("login")
@CrossOrigin("*")
public class LoginController {
	
	 @GetMapping("/{username}/{password}")
		public Object loginData(@PathVariable String username,@PathVariable String password) {
			//LoginDTO log=serviceI.login(username, password);
			
			return null;
			
		
			
	 }

}
