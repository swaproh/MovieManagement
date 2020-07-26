package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.entity.CountryEntity;
import in.perpixl.movie.mapper.CountryMapper;
import in.perpixl.movie.model.CountryDTO;
import in.perpixl.movie.repository.CountryRepository;

@Service
@Qualifier("countryservice")
public class CountryServiceImpl implements ICRUDService<CountryDTO>{
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private CountryMapper mapper;
	
	@Override
	public void create(CountryDTO m) {
		CountryEntity entity = mapper.mapDtoToEntity(m);
		countryRepo.save(entity);
	}

	@Override
	public CountryDTO read(long countryId) {
		CountryEntity me=countryRepo.findById(countryId).orElseThrow(RuntimeException::new);
		CountryDTO m=mapper.mapEntityToDto(me);
		return m;
	}

	@Override
	public void update(CountryDTO m) {
		Optional<CountryEntity> entity=countryRepo.findById(Long.parseLong(m.getId().toString()));
		if(entity.isPresent())
		{
			CountryEntity ent = entity.get();
			mapper.mapDtoToEntity(m, ent);
			countryRepo.save(ent);
		}
	}

	@Override
	public long delete(long countryId) {
		Optional<CountryEntity> entity=countryRepo.findById(countryId);
		long rohit=0L;
		if(entity.isPresent())
		{
			CountryEntity ent = entity.get();
	
			countryRepo.delete(ent);
		}
		return rohit;
	}

	@Override
	public List<CountryDTO> readAll() {
		List<CountryEntity> countryEntityList=countryRepo.findAll();
		Set<CountryDTO> countryDTOList=mapper.mapEntityListToDTOList(new HashSet<>(countryEntityList));
		return new ArrayList<>(countryDTOList);
	}

}
