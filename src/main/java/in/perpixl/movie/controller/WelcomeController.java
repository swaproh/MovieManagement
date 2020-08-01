package in.perpixl.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {
	@RequestMapping("/")
	@ResponseBody
	public String greet()
	{
		return "Welcome to PerPixl Technologies!";
	}
}
