package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.constants.Constants;
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
	public Long create(RoleDTO m) {
		RoleEntity entity = mapper.mapDtoToEntity(m);
		RoleEntity savedEntity = roleRepo.save(entity);
		return savedEntity.getRoleId();
	}

	@Override
	public RoleDTO read(Long id) {
		RoleEntity me=roleRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		return mapper.mapEntityToDto(me);
	}

	@Override
	public void update(RoleDTO m) {
		RoleEntity entity=roleRepo.findById(m.getRoleId())
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, m.getRoleId())));
		mapper.mapDtoToEntity(m, entity);
		roleRepo.save(entity);
	}

	@Override
	public void delete(Long id) {
		RoleEntity entity=roleRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		roleRepo.delete(entity);
	}

	@Override
	public List<RoleDTO> readAll(Long pageNumber, Long pageSize) {
		List<RoleEntity> roleEntityList=roleRepo.findAll();
		Set<RoleDTO> roleDTOList=mapper.mapEntityListToDTOList(new HashSet<>(roleEntityList));
		return new ArrayList<>(roleDTOList);
	}

}
