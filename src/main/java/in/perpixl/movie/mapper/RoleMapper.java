package in.perpixl.movie.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.perpixl.movie.Entity.MovieEntity;
import in.perpixl.movie.Entity.RoleEntity;
import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.util.PerpixlUtils;

@Component
public class RoleMapper implements IMapper<RoleDTO,RoleEntity>{

	@Override
	public RoleDTO mapEntityToDto(RoleEntity u) {
		RoleDTO dto=new RoleDTO();
		mapEntityToDto(dto, u);
		return dto;
	}

	@Override
	public RoleEntity mapDtoToEntity(RoleDTO t) {
		RoleEntity entity=new RoleEntity();
		mapDtoToEntity(t, entity);
		return entity;
	}

	@Override
	public void mapEntityToDto(RoleDTO t, RoleEntity u) {
		t.setRoleId(u.getRoleId());
		t.setRoleName(u.getRoleName());
		
		
	}

	@Override
	public void mapDtoToEntity(RoleDTO t, RoleEntity u) {
		u.setRoleId(t.getRoleId());
		u.setRoleName(t.getRoleName());
		
	}

	@Override
	public List<RoleDTO> mapEntityListToDTOList(List<RoleEntity> uList) {
		List<RoleDTO> roleDTOList=new ArrayList<>();
		for(RoleEntity rEntity:PerpixlUtils.<RoleEntity>safe(uList))
		{
			RoleDTO rDTO= mapEntityToDto(rEntity);
			roleDTOList.add(rDTO);
		}
		
		return roleDTOList;
	}

	@Override
	public List<RoleEntity> mapDTOListToEntityList(List<RoleDTO> tList) {
		List<RoleEntity> roleEntityList=new ArrayList<>();
		for(RoleDTO rDTO:PerpixlUtils.<RoleDTO>safe(tList))
		{
			RoleEntity rEntity= mapDtoToEntity(rDTO);
			roleEntityList.add(rEntity);
		}
		return roleEntityList;
	}

}
