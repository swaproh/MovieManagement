package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.constants.Constants;
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
	public Long create(CountryDTO m) {
		CountryEntity entity = mapper.mapDtoToEntity(m);
		CountryEntity savedEntity = countryRepo.save(entity);
		return savedEntity.getId();
	}

	@Override
	public CountryDTO read(Long id) {
		CountryEntity me=countryRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		return mapper.mapEntityToDto(me);
	}

	@Override
	public void update(CountryDTO m) {
		CountryEntity entity=countryRepo.findById(m.getId())
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, m.getId())));
		mapper.mapDtoToEntity(m, entity);
		countryRepo.save(entity);
	}

	@Override
	public void delete(Long id) {
		Optional<CountryEntity> entity=countryRepo.findById(id);
		if(entity.isPresent())
		{
			CountryEntity ent = entity.get();
	
			countryRepo.delete(ent);
		}else
		{
			throw new EntityNotFoundException(
					String.format(Constants.ENTITY_NOT_FOUND, id));
		}
	}

	@Override
	public List<CountryDTO> readAll(Long pageNumber, Long pageSize) {
		List<CountryEntity> countryEntityList=countryRepo.findAll();
		Set<CountryDTO> countryDTOList=mapper.mapEntityListToDTOList(new HashSet<>(countryEntityList));
		return new ArrayList<>(countryDTOList);
	}

}
