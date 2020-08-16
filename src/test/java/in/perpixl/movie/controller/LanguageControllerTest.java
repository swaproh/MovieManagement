package in.perpixl.movie.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.perpixl.movie.model.LanguageDTO;
import in.perpixl.movie.service.ICRUDService;

@WebMvcTest(LanguageController.class)
public class LanguageControllerTest {

	@MockBean(name = "languageservice")
	private ICRUDService<LanguageDTO> serviceI;

	@Autowired
	private MockMvc mockMvc;

	private String APPLICATION_PATH = "/language";
	private String SERVER_NAME = "http://localhost";
	private ObjectMapper mapper = new ObjectMapper();

	//@Test
	public void createTest() throws JsonProcessingException, Exception {
		// local variables
		String RESOURCE_PATH = "/create";
		Long createdResourceId = 100L;
		// prepare data
		LanguageDTO request = new LanguageDTO();
		request.setName("Marathi");

		// mock call
		Mockito.when(serviceI.create(Mockito.any(LanguageDTO.class))).thenReturn(createdResourceId);

		// send request and verify response
		mockMvc.perform(post(APPLICATION_PATH + RESOURCE_PATH).content(mapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(header().string("Location",
						SERVER_NAME + APPLICATION_PATH + RESOURCE_PATH + "/" + createdResourceId));
	}

	//@Test
	public void readTest() throws URISyntaxException, Exception {
		// local variables
		String RESOURCE_PATH = "/read/{id}";
		Long readResourceId = 100L;
		// prepare data
		LanguageDTO response = new LanguageDTO();
		response.setId(readResourceId);
		response.setName("Marathi");

		// mock call
		Mockito.when(serviceI.read(Mockito.any(Long.class))).thenReturn(response);

		// send request and verify response
		mockMvc.perform(get(APPLICATION_PATH + RESOURCE_PATH, readResourceId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(readResourceId));
	}
	
	//@Test
	public void updateTest() throws URISyntaxException, Exception {
		// local variables
		String RESOURCE_PATH = "/update";
		Long createdResourceId = 110L;
		// prepare data
		LanguageDTO request = new LanguageDTO();
		request.setId(createdResourceId);
		request.setName("Marathi");

		// mock call
		Mockito.doNothing().when(serviceI).update(Mockito.any(LanguageDTO.class));
		// send request and verify response
		mockMvc.perform(put(new URI(APPLICATION_PATH + RESOURCE_PATH)).content(mapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	//@Test
	public void deleteTest() throws URISyntaxException, Exception {
		// local variables
		String RESOURCE_PATH = "/delete/{id}";
		Long readResourceId = 100L;
		
		// mock call
		Mockito.doNothing().when(serviceI).delete(Mockito.any(Long.class));

		// send request and verify response
		mockMvc.perform(delete(APPLICATION_PATH + RESOURCE_PATH, readResourceId))
				.andExpect(status().isOk());
	}
	
	//@Test
	public void readAllTest() throws URISyntaxException, Exception {
		// local variables
		String RESOURCE_PATH = "/readAll";
		Long readResourceId = 100L;
		// prepare data
		LanguageDTO l1 = new LanguageDTO();
		l1.setId(readResourceId);
		l1.setName("Marathi");
		LanguageDTO l2 = new LanguageDTO();
		l2.setId(++readResourceId);
		l2.setName("Hindi");
		List<LanguageDTO> response = new ArrayList<>();
		response.add(l1);
		response.add(l2);
		
		// mock call
		Mockito.when(serviceI.readAll(Mockito.anyLong(),Mockito.anyLong())).thenReturn(response);

		// send request and verify response
		mockMvc.perform(get(APPLICATION_PATH + RESOURCE_PATH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}
}
