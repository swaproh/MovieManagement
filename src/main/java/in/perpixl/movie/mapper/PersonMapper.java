package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.entity.CountryEntity;
import in.perpixl.movie.entity.PersonEntity;
import in.perpixl.movie.entity.RoleEntity;
import in.perpixl.movie.model.CountryDTO;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.repository.PersonRepository;
import in.perpixl.movie.util.PerpixlUtils;

@Component
public class PersonMapper implements IMapper<PersonDTO, PersonEntity>{
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	CountryMapper countryMapper;
	
	@Autowired
	PersonRepository repo;
	
	@Override
	public PersonDTO mapEntityToDto(PersonEntity u) {
		PersonDTO dto=new PersonDTO();
		mapEntityToDto(dto, u);
		return dto;
	}

	@Override
	public PersonEntity mapDtoToEntity(PersonDTO t) {
		PersonEntity entity=new PersonEntity();
		// check if language with this id is already present
		Optional<PersonEntity> personOpt = Optional.empty();
		if(t.getPersonId()!=null) {
			personOpt = repo.findById(t.getPersonId());
		}
		if(personOpt.isPresent()) {
			entity = personOpt.get();
		}else {
			mapDtoToEntity(t, entity);
		}
		return entity;
	}

	@Override
	public void mapEntityToDto(PersonDTO t, PersonEntity u) {
		t.setPersonId(u.getPersonId());
		t.setFirstName(u.getFirstName());
		t.setLastName(u.getLastName());
		CountryDTO cDto = countryMapper.mapEntityToDto(u.getCountry());
		t.setCountry(cDto);
		t.setDob(u.getDob());
		/*
		 * Set<RoleDTO> rDTO=roleMapper.mapEntityListToDTOList(u.getRoleEntity());
		 * for(RoleDTO rD: rDTO) { t.addRole(rD); }
		 */
	}

	@Override
	public void mapDtoToEntity(PersonDTO t, PersonEntity u) {
		u.setPersonId(t.getPersonId());
		u.setFirstName(t.getFirstName());
		u.setLastName(t.getLastName());
		CountryEntity cEntity = countryMapper.mapDtoToEntity(t.getCountry());
		u.setCountry(cEntity);
		u.setDob(t.getDob());
		//adjust role DTO
		/*
		 * Set<RoleEntity> rEntity=roleMapper.mapDTOListToEntityList(t.getRoles());
		 * for(RoleEntity re: rEntity) { u.addRole(re); }
		 */
	}

	@Override
	public Set<PersonEntity> mapDTOListToEntityList(Set<PersonDTO> tList) {
		Set<PersonEntity> personEntityList = new HashSet<>();
		for(PersonDTO pDto : PerpixlUtils.<PersonDTO>safe(tList))
		{
			PersonEntity pEntity = mapDtoToEntity(pDto);
			personEntityList.add(pEntity);
		}
		return personEntityList;
	}
	
	@Override
	public Set<PersonDTO> mapEntityListToDTOList(Set<PersonEntity> personEntityList) {
		
		Set<PersonDTO> personDTOList = new HashSet<>();
		for(PersonEntity pEntity : PerpixlUtils.<PersonEntity>safe(personEntityList))
		{
			PersonDTO pDTO = mapEntityToDto(pEntity);
			personDTOList.add(pDTO);
		}
		return personDTOList;
	}
}
