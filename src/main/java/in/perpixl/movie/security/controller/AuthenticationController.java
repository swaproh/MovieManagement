package in.perpixl.movie.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.perpixl.movie.model.ApiResponse;
import in.perpixl.movie.model.AuthToken;
import in.perpixl.movie.model.UserDTO;
import in.perpixl.movie.security.entity.User;
import in.perpixl.movie.security.service.UserServiceImpl;
import in.perpixl.movie.util.JwtTokenUtil;

@RestController
@RequestMapping("/token")
@CrossOrigin("*")
public class AuthenticationController 
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/generate-token")
	public ResponseEntity register(@RequestBody UserDTO loginUser)
	{
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUname(), loginUser.getPassword())	
				);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final User user = userService.findOne(loginUser.getUname());
		final String token = jwtTokenUtil.generateToken(user);
		ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Authenticated", new AuthToken(token));
		return ResponseEntity.ok(response);
	}
}
