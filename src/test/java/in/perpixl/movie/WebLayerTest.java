package in.perpixl.movie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import in.perpixl.movie.controller.WelcomeController;

/**
 * @author w10rohit
 * This class targets only web layer. i.e. instead of instantiating whole context
 * it only instantiates web layer.
 * 
 * @WebMvcTest targets whole web layer which includes all controller classes.
 * We can also restrict to one controller by providing controller class like @WebMvcTest(WelcomeController.class)
 */
@WebMvcTest(WelcomeController.class)
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturnDefaultMessage() throws Exception
	{
		this.mockMvc.perform(get("/"))
		.andDo(print()).andExpect(status().isOk())
		.andReturn();
		//.andExpect(content().string("PerPixl"));
	}
}
