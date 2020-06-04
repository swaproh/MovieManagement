package in.perpixl.movie.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import in.perpixl.movie.Entity.MovieEntity;
import in.perpixl.movie.Entity.PersonEntity;
import in.perpixl.movie.Entity.RoleEntity;
import in.perpixl.movie.mapper.PersonMapper;
import in.perpixl.movie.mapper.RoleMapper;
import in.perpixl.movie.model.MovieDTO;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.repository.PersonRepository; 
import in.perpixl.movie.repository.RoleRepository;

@Component
@Qualifier("roledao")
public class RoleDaoImpl implements IDao<RoleDTO> {
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private RoleMapper mapper;
	
	@Override
	public void create(RoleDTO m) {
		RoleEntity entity = mapper.mapDtoToEntity(m);
		roleRepo.save(entity);
	}

	@Override
	public RoleDTO read(long roleId) {
		RoleEntity me=roleRepo.findById(roleId).orElseThrow(RuntimeException::new);
		RoleDTO m=mapper.mapEntityToDto(me);
		return m;
	}

	@Override
	public void update(RoleDTO m) {
		Optional<RoleEntity> entity=roleRepo.findById(Long.parseLong(m.getRoleId().toString()));
		if(entity.isPresent())
		{
			RoleEntity ent = entity.get();
			mapper.mapDtoToEntity(m, ent);
			roleRepo.save(ent);
		}
	}

	@Override
	public long delete(long roleId) {
		Optional<RoleEntity> entity=roleRepo.findById(roleId);
		long rohit=0L;
		if(entity.isPresent())
		{
			RoleEntity ent = entity.get();
	
			roleRepo.delete(ent);
		}
		return rohit;
	}

	@Override
	public List<RoleDTO> readAll() {
		List<RoleEntity> roleEntityList=roleRepo.findAll();
		List<RoleDTO> roleDTOList=mapper.mapEntityListToDTOList(roleEntityList);
		return roleDTOList;
	}

}
