package in.perpixl.movie;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import in.perpixl.movie.controller.MovieController;

/**
 * @author w10rohit
 * @SpringBootTest tells spring boot to look for a main configuration class
 * i.e. the one annotated with @SpringBootApplication and use that to start
 * a Spring application context.
 *
 */
@SpringBootTest
class MovieApplicationTests {
	
	@Autowired
	private MovieController controller;

	/**
	 * a simple sanity check test that will faile
	 * if application context cannot start
	 */
	//@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
