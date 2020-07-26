package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.entity.CompanyEntity;
import in.perpixl.movie.mapper.CompanyMapper;
import in.perpixl.movie.model.CompanyDTO;
import in.perpixl.movie.repository.CompanyRepository;

@Service
@Qualifier("companyservice")
public class CompanyServiceImpl implements ICRUDService<CompanyDTO>{
	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private CompanyMapper mapper;
	
	@Override
	public void create(CompanyDTO m) {
		CompanyEntity entity = mapper.mapDtoToEntity(m);
		companyRepo.save(entity);
	}

	@Override
	public CompanyDTO read(long companyId) {
		CompanyEntity me=companyRepo.findById(companyId).orElseThrow(RuntimeException::new);
		CompanyDTO m=mapper.mapEntityToDto(me);
		return m;
	}

	@Override
	public void update(CompanyDTO m) {
		Optional<CompanyEntity> entity=companyRepo.findById(Long.parseLong(m.getId().toString()));
		if(entity.isPresent())
		{
			CompanyEntity ent = entity.get();
			mapper.mapDtoToEntity(m, ent);
			companyRepo.save(ent);
		}
	}

	@Override
	public long delete(long companyId) {
		Optional<CompanyEntity> entity=companyRepo.findById(companyId);
		long rohit=0L;
		if(entity.isPresent())
		{
			CompanyEntity ent = entity.get();
	
			companyRepo.delete(ent);
		}
		return rohit;
	}

	@Override
	public List<CompanyDTO> readAll() {
		List<CompanyEntity> companyEntityList=companyRepo.findAll();
		Set<CompanyDTO> companyDTOList=mapper.mapEntityListToDTOList(new HashSet<>(companyEntityList));
		return new ArrayList<>(companyDTOList);
	}

}
