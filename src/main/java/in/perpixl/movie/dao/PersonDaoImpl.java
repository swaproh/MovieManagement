package in.perpixl.movie.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.Entity.PersonEntity;
import in.perpixl.movie.mapper.PersonMapper;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.repository.PersonRepository;

@Component
public class PersonDaoImpl implements IDao<PersonDTO>{
	
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


}
