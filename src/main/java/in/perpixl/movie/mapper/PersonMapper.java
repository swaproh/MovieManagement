package in.perpixl.movie.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.Entity.PersonEntity;
import in.perpixl.movie.Entity.RoleEntity;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.model.RoleDTO;

@Component
public class PersonMapper implements IMapper<PersonDTO, PersonEntity>{
	@Autowired
	RoleMapper roleMapper;
	
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
		t.setCountry(u.getCountry());
		t.setDob(u.getDob());
		List<RoleDTO> rDTO=roleMapper.mapEntityListToDTOList(u.getRoleEntity());
		t.setRoleDTO(rDTO);
		
	}

	@Override
	public void mapDtoToEntity(PersonDTO t, PersonEntity u) {
		u.setPersonId(t.getPersonId());
		u.setFirstName(t.getFirstName());
		u.setLastName(t.getLastName());
		u.setCountry(t.getCountry());
		u.setDob(t.getDob());
		List<RoleEntity> rEntity=roleMapper.mapDTOListToEntityList(t.getRoleDTO());
		u.setRoleEntity(rEntity);
		
	
		
	}

	@Override
	public List<PersonEntity> mapDTOListToEntityList(List<PersonDTO> tList) {
		List<PersonEntity> personEntityList = new ArrayList<>();
		for(PersonDTO pDto : tList)
		{
			PersonEntity pEntity = mapDtoToEntity(pDto);
			personEntityList.add(pEntity);
		}
		return personEntityList;
	}
	
	@Override
	public List<PersonDTO> mapEntityListToDTOList(List<PersonEntity> personEntityList) {
		
		List<PersonDTO> personDTOList = new ArrayList<>();
		for(PersonEntity pEntity : personEntityList)
		{
			PersonDTO pDTO = mapEntityToDto(pEntity);
			personDTOList.add(pDTO);
		}
		return personDTOList;
	}
}
