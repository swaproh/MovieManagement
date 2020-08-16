package in.perpixl.movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author w10rohit
 * This test class won't start the server at all. But directly triggers the Http requests
 * as if triggered by client and handles the incoming requests.
 * 
 * To do this, we have used @AutoConfigureMockMvc, which creates bean MockMvc.
 * In this test, the full spring application context is started but without server.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class WebApplicationTest {
	@Autowired
	private MockMvc mockMvc;
	
	//@Test
	public void checkInjectedDependencies()
	{
		assertThat(mockMvc).isNotNull();
	}
	
	//@Test
	public void shouldReturnDefaultMessage() throws Exception
	{
		this.mockMvc.perform(get("/"))
		.andDo(print()).andExpect(status().isOk())
		.andReturn();
		//.andExpect(content().string("PerPixl"));
	}
}
