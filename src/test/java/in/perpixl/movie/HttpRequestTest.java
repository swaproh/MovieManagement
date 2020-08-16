package in.perpixl.movie;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * @author w10rohit
 * Starts the application, listen for the connection and then send the Http request
 * and assert the response
 */
//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	//@Test
	public void greetingShouldReturnDefaultMessage()
	{
		assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/", 
				String.class)).contains("Welcome to PerPixl Technologies!");
	}
}
