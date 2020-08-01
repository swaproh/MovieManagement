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
import in.perpixl.movie.entity.RaagEntity;
import in.perpixl.movie.mapper.RaagMapper;
import in.perpixl.movie.model.RaagDTO;
import in.perpixl.movie.repository.RaagRepository;

@Service
@Qualifier("raagservice")
public class RaagServiceImpl implements ICRUDService<RaagDTO>{
	@Autowired
	private RaagRepository repo;
	@Autowired
	private RaagMapper mapper;
	
	@Override
	public Long create(RaagDTO m) {
		RaagEntity entity = mapper.mapDtoToEntity(m);
		RaagEntity savedEntity = repo.save(entity);
		return savedEntity.getId();
	}

	@Override
	public RaagDTO read(Long id) {
		RaagEntity me=repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		return mapper.mapEntityToDto(me);
	}

	@Override
	public void update(RaagDTO m) {
		RaagEntity entity=repo.findById(m.getId())
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, m.getId())));
		mapper.mapDtoToEntity(m, entity);
		repo.save(entity);
	}

	@Override
	public void delete(Long id) {
		RaagEntity entity=repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		repo.delete(entity);
	}

	@Override
	public List<RaagDTO> readAll() {
		List<RaagEntity> RaagEntityList=repo.findAll();
		Set<RaagDTO> RaagDTOList=mapper.mapEntityListToDTOList(new HashSet<>(RaagEntityList));
		return new ArrayList<>(RaagDTOList);
	}

}
