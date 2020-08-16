package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.perpixl.movie.constants.Constants;
import in.perpixl.movie.entity.MovieEntity;
import in.perpixl.movie.entity.PersonEntity;
import in.perpixl.movie.mapper.PersonMapper;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.repository.PersonRepository;

@Service
@Qualifier("personservice")
public class PersonServiceImpl implements ICRUDService<PersonDTO>{
	
	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private PersonMapper mapper;
	
	
	@Override
	@Transactional
	public Long create(PersonDTO m) {
		PersonEntity entity = mapper.mapDtoToEntity(m);
		PersonEntity savedEntity = personRepo.save(entity);
		return savedEntity.getPersonId();
	}

	@Override
	public PersonDTO read(Long id) {
		PersonEntity me=personRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		return mapper.mapEntityToDto(me);
	}

	@Override
	public void update(PersonDTO m) {
		PersonEntity entity=personRepo.findById(m.getPersonId())
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, m.getPersonId())));
		mapper.mapDtoToEntity(m, entity);
		personRepo.save(entity);
	}

	@Override
	public void delete(Long id) {
		PersonEntity entity=personRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		personRepo.delete(entity);
	}

	@Override
	public List<PersonDTO> readAll(Long pageNumber, Long pageSize) {
		Pageable pageInfo = PageRequest.of(Integer.valueOf(pageNumber.toString()), Integer.valueOf(pageSize.toString()));
		Page<PersonEntity> personEntityPage=personRepo.findAll(pageInfo);
		Set<PersonDTO> personDTOList=mapper.mapEntityListToDTOList(personEntityPage.toSet());
		return new ArrayList<>(personDTOList);
	}

}
