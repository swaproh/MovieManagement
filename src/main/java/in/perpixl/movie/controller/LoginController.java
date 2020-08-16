package in.perpixl.movie.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.perpixl.movie.model.UserDTO;

@RestController(value="login")
@RequestMapping("login")
@CrossOrigin("*")
public class LoginController {
	
	 @GetMapping("/{username}/{password}")
		public Object loginData(@PathVariable String username,@PathVariable String password) {
			//LoginDTO log=serviceI.login(username, password);
			
			return null;
			
		
			
	 }
	 
	 @PostMapping("/log")
	 public Object log(@RequestBody UserDTO userDTO)
	 {
		 class Token{
			 String token;

			public String getToken() {
				return token;
			}

			public void setToken(String token) {
				this.token = token;
			}
			 
		 }
		 class Response{
			 HttpStatus status = HttpStatus.OK;
			 Token result = new Token();
			 String message = "Hello Rohit";
			 
			public String getMessage() {
				return message;
			}
			public void setMessage(String message) {
				this.message = message;
			}
			public HttpStatus getStatus() {
				return status;
			}
			public void setStatus(HttpStatus status) {
				this.status = status;
			}
			public Token getResult() {
				return result;
			}
			public void setResult(Token token) {
				this.result = token;
			}
			 
		 }
		 
		 return new Response();
	 }

}
