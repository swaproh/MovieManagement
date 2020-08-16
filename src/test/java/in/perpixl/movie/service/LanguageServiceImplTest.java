package in.perpixl.movie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import in.perpixl.movie.entity.LanguageEntity;
import in.perpixl.movie.mapper.LanguageMapper;
import in.perpixl.movie.model.LanguageDTO;
import in.perpixl.movie.repository.LanguageRepository;

//@RunWith(MockitoJUnitRunner.class)
public class LanguageServiceImplTest {
	@Mock
	private LanguageRepository repo;
	@Mock
	private LanguageMapper mapper;
	@InjectMocks
	LanguageServiceImpl service;
	
	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
	
	//@Test
	public void saveTest()
	{
		// prepare data
		LanguageDTO dto = new LanguageDTO();
		dto.setName("Hindi");
		LanguageEntity entity = new LanguageEntity();
		entity.setId(101L);
		entity.setName(dto.getName());
		// mock calls
		Mockito.when(mapper.mapDtoToEntity(Mockito.any(LanguageDTO.class))).thenCallRealMethod();
		Mockito.when(repo.save(Mockito.any(LanguageEntity.class)))
		.thenReturn(entity);
		// test method
		Long id = service.create(dto);
		// make assertions
		assertThat(id).isSameAs(entity.getId());
	}
	
	//@Test(expected=EntityNotFoundException.class)
	public void updateTestEntityNotFound()
	{
		// prepare data
		LanguageDTO dto = new LanguageDTO();
		dto.setId(101L);
		dto.setName("Hindi");
		LanguageEntity entity = new LanguageEntity();
		entity.setId(101L);
		entity.setName("Marathi");
		// mock calls
		// test method
		service.update(dto);
		// make assertions
		assertThat(dto.getId()).isSameAs(entity.getId());
		assertThat(dto.getName()).isSameAs(entity.getName());
	}
	
	//@Test
	public void updateTestSuccess()
	{
		// prepare data
		LanguageDTO dto = new LanguageDTO();
		dto.setId(101L);
		dto.setName("Hindi");
		LanguageEntity entity = new LanguageEntity();
		entity.setId(101L);
		entity.setName("Marathi");
		// mock calls
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));
		// test method
		service.update(dto);
		// make assertions
		assertThat(dto.getId()).isSameAs(entity.getId());
		assertThat(dto.getName()).isNotSameAs(entity.getName());
		assertThat("Marathi").isSameAs(entity.getName());
	}
	
	//@Test(expected=EntityNotFoundException.class)
	public void readTestEntityNotFound()
	{
		// test method
		service.read(101L);
	}
	
	//@Test
	public void readTestSuccess()
	{
		// prepare data
		LanguageDTO dto = new LanguageDTO();
		dto.setId(101L);
		dto.setName("Hindi");
		LanguageEntity entity = new LanguageEntity();
		entity.setId(101L);
		entity.setName("Marathi");
		// mock calls
		Mockito.when(mapper.mapEntityToDto(Mockito.any(LanguageEntity.class))).thenReturn(dto);
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));
		// test method
		LanguageDTO returnedDto = service.read(101L);
		// make assertions
		assertThat(returnedDto.getId()).isSameAs(entity.getId());
	}
	
	//@Test
	public void readAllTest()
	{
		// prepare data
		LanguageDTO l1 = new LanguageDTO();
		l1.setId(100L);
		l1.setName("Marathi");
		LanguageDTO l2 = new LanguageDTO();
		l2.setId(101L);
		l2.setName("Hindi");
		Set<LanguageDTO> response = new HashSet<>();
		response.add(l1);
		response.add(l2);
		
		LanguageEntity entity = new LanguageEntity();
		entity.setId(100L);
		entity.setName("Marathi");
		LanguageEntity entity2 = new LanguageEntity();
		entity2.setId(101L);
		entity2.setName("Hindi");
		
		List<LanguageEntity> responseList = new ArrayList<>();
		responseList.add(entity);
		responseList.add(entity2);
		// mock calls
		Mockito.when(mapper.mapEntityListToDTOList(Mockito.any(Set.class))).thenReturn(response);
		Mockito.when(repo.findAll()).thenReturn(responseList);
		// test method
		List<LanguageDTO> actualList = service.readAll(1L,5L);
		// make assertions
		assertThat(actualList.size()).isSameAs(response.size());
	}
}
