package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.Entity.PersonEntity;
import in.perpixl.movie.mapper.PersonMapper;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.repository.PersonRepository;

@Service
@Qualifier("personservice")
public class PersonServiceImpl implements IService<PersonDTO>{
	
	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private PersonMapper mapper;
	
	@Override
	public void create(PersonDTO m) {
		PersonEntity entity = mapper.mapDtoToEntity(m);
		personRepo.save(entity);
	}

	@Override
	public PersonDTO read(long personId) {
		PersonEntity me=personRepo.findById(personId).orElseThrow(RuntimeException::new);
		PersonDTO m=mapper.mapEntityToDto(me);
		return m;
	}

	@Override
	public void update(PersonDTO m) {
		Optional<PersonEntity> entity=personRepo.findById(Long.parseLong(m.getPersonId().toString()));
		if(entity.isPresent())
		{
			PersonEntity ent = entity.get();
			mapper.mapDtoToEntity(m, ent);
			personRepo.save(ent);
		}
	}

	@Override
	public long delete(long personId) {
		Optional<PersonEntity> entity=personRepo.findById(personId);
		long rohit=0L;
		if(entity.isPresent())
		{
			PersonEntity ent = entity.get();
	
			personRepo.delete(ent);
		}
		return rohit;
	}

	@Override
	public List<PersonDTO> readAll() {
		List<PersonEntity> personEntityList=personRepo.findAll();
		Set<PersonDTO> personDTOList=mapper.mapEntityListToDTOList(new HashSet<>(personEntityList));
		return new ArrayList<>(personDTOList);
	}

}
