package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.entity.RoleEntity;
import in.perpixl.movie.mapper.RoleMapper;
import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.repository.RoleRepository;

@Service
@Qualifier("roleservice")
public class RoleServiceImpl implements ICRUDService<RoleDTO>{
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
		Set<RoleDTO> roleDTOList=mapper.mapEntityListToDTOList(new HashSet<>(roleEntityList));
		return new ArrayList<>(roleDTOList);
	}

}
