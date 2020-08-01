package in.perpixl.movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.perpixl.movie.entity.LanguageEntity;
import in.perpixl.movie.model.LanguageDTO;
import in.perpixl.movie.repository.LanguageRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class LanguageIntegrationTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private LanguageRepository repository;

	@Test
	public void checkInjectedDependencies() {
		assertThat(mockMvc).isNotNull();
		assertThat(objectMapper).isNotNull();
		assertThat(repository).isNotNull();
	}

	@Test
	@DirtiesContext
	public void saveLanguageTest() throws JsonProcessingException, Exception {
		LanguageDTO dto = new LanguageDTO();
		dto.setName("Marathi");

		mockMvc.perform(
				post(new URI("/language/create"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
		.andExpect(status().isCreated());
		LanguageEntity entity = repository.findByName("Marathi");
		assertThat(entity.getId()).isNotNull();
	}
	
	@Test
	@DirtiesContext
	public void saveLanguageWithIdTest() throws JsonProcessingException, Exception {
		LanguageDTO dto = new LanguageDTO();
		dto.setId(1000L);
		dto.setName("Marathi");

		mockMvc.perform(
				post(new URI("/language/create"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
		.andExpect(status().isCreated())
		.andDo(print());
		LanguageEntity entity = repository.findByName("Marathi");
		assertThat(entity.getId()).isNotNull();
		assertThat(entity.getId()).isNotSameAs(dto.getId());
	}
	
	@Test
	@DirtiesContext
	public void updateLanguageTestIdNull() throws JsonProcessingException, Exception {
		LanguageDTO dto = new LanguageDTO();
		dto.setName("Marathi");

		mockMvc.perform(
				put(new URI("/language/update"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	@DirtiesContext
	public void updateLanguageTest() throws JsonProcessingException, Exception {
		LanguageDTO dto = new LanguageDTO();
		dto.setName("Marathi");

		// save
		mockMvc.perform(
				post(new URI("/language/create"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
		.andExpect(status().isCreated());
		LanguageEntity entity = repository.findByName("Marathi");
		assertThat(entity.getId()).isNotNull();
		
		// udpate
		LanguageDTO dtoUpdate = new LanguageDTO();
		dtoUpdate.setId(entity.getId());
		dtoUpdate.setName("Hindi");
		mockMvc.perform(
				put(new URI("/language/update"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dtoUpdate)))
		.andExpect(status().isOk());
		
		Optional<LanguageEntity> updatedentityOpt = repository.findById(entity.getId());
		assertThat(updatedentityOpt.isPresent()).isTrue();
		assertThat(updatedentityOpt.get().getName()).isNotSameAs(dto.getName());
		assertThat(updatedentityOpt.get().getName()).isEqualTo(dtoUpdate.getName());
	}
	
	@Test
	@DirtiesContext
	public void readLanguageTest() throws JsonProcessingException, Exception {
		LanguageDTO dto = new LanguageDTO();
		dto.setName("Marathi");

		// save
		mockMvc.perform(
				post(new URI("/language/create"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
		.andExpect(status().isCreated());
		LanguageEntity entity = repository.findByName("Marathi");
		assertThat(entity.getId()).isNotNull();
		assertThat(entity.getName()).isEqualTo(dto.getName());
	}
	
	@Test
	@DirtiesContext
	public void readAllLanguageTest() throws JsonProcessingException, Exception {
		LanguageDTO dto = new LanguageDTO();
		dto.setName("Marathi");

		// save
		mockMvc.perform(
				post(new URI("/language/create"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
		.andExpect(status().isCreated());
		List<LanguageEntity> entitySet = repository.findAll();
		assertThat(entitySet.size()).isEqualTo(1);
		assertThat(entitySet.get(0).getId()).isNotNull();
		assertThat(entitySet.get(0).getName()).isEqualTo(dto.getName());
	}
	
	@Test
	@DirtiesContext
	public void deleteLanguageTest() throws JsonProcessingException, Exception {
		LanguageDTO dto = new LanguageDTO();
		dto.setName("Marathi");

		// save
		mockMvc.perform(
				post(new URI("/language/create"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
		.andExpect(status().isCreated());
		LanguageEntity entity = repository.findByName("Marathi");
		assertThat(entity.getId()).isNotNull();
		assertThat(entity.getName()).isEqualTo(dto.getName());
		// delete
		repository.deleteById(entity.getId());
		Optional<LanguageEntity> entityByIdOpt = repository.findById(entity.getId());
		assertThat(entityByIdOpt.isPresent()).isFalse();
	}
}
