package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
	public void create(RaagDTO m) {
		RaagEntity entity = mapper.mapDtoToEntity(m);
		repo.save(entity);
	}

	@Override
	public RaagDTO read(long roleId) {
		RaagEntity me=repo.findById(roleId).orElseThrow(RuntimeException::new);
		RaagDTO m=mapper.mapEntityToDto(me);
		return m;
	}

	@Override
	public void update(RaagDTO m) {
		Optional<RaagEntity> entity=repo.findById(Long.parseLong(m.getId().toString()));
		if(entity.isPresent())
		{
			RaagEntity ent = entity.get();
			mapper.mapDtoToEntity(m, ent);
			repo.save(ent);
		}
	}

	@Override
	public long delete(long roleId) {
		Optional<RaagEntity> entity=repo.findById(roleId);
		long rohit=0L;
		if(entity.isPresent())
		{
			RaagEntity ent = entity.get();
	
			repo.delete(ent);
		}
		return rohit;
	}

	@Override
	public List<RaagDTO> readAll() {
		List<RaagEntity> RaagEntityList=repo.findAll();
		Set<RaagDTO> RaagDTOList=mapper.mapEntityListToDTOList(new HashSet<>(RaagEntityList));
		return new ArrayList<>(RaagDTOList);
	}

}
