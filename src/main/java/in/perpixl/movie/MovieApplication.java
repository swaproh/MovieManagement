package in.perpixl.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import in.perpixl.movie.security.entity.User;
import in.perpixl.movie.security.repository.UserRepository;

@SpringBootApplication
public class MovieApplication {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(UserRepository userRepo)
	{
		return args -> {
			User user1 = new User();
			user1.setAge(21L);
			user1.setFirstName("Rohit");
			user1.setLastName("Muneshwar");
			user1.setUname("rrohitt44");
			user1.setPassword(encoder.encode("rohit123"));
			User existing = userRepo.findByUname(user1.getUname());
			if(existing==null)
				userRepo.save(user1);
		};
	}

}
