package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.Entity.CountryEntity;
import in.perpixl.movie.Entity.PersonEntity;
import in.perpixl.movie.Entity.RoleEntity;
import in.perpixl.movie.model.CountryDTO;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.util.PerpixlUtils;

@Component
public class PersonMapper implements IMapper<PersonDTO, PersonEntity>{
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	CountryMapper countryMapper;
	
	@Override
	public PersonDTO mapEntityToDto(PersonEntity u) {
		PersonDTO dto=new PersonDTO();
		mapEntityToDto(dto, u);
		return dto;
	}

	@Override
	public PersonEntity mapDtoToEntity(PersonDTO t) {
		PersonEntity entity=new PersonEntity();
		mapDtoToEntity(t, entity);
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
		Set<RoleDTO> rDTO=roleMapper.mapEntityListToDTOList(u.getRoleEntity());
		for(RoleDTO rD: rDTO) {
			t.addRole(rD);
		}
		
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
		Set<RoleEntity> rEntity=roleMapper.mapDTOListToEntityList(t.getRoles());
		for(RoleEntity re: rEntity) {
			u.addRole(re);
		}
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
