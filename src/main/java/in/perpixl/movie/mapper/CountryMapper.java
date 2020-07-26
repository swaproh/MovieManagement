package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.entity.CountryEntity;
import in.perpixl.movie.model.CountryDTO;
import in.perpixl.movie.repository.CountryRepository;
import in.perpixl.movie.util.PerpixlUtils;

@Component
public class CountryMapper implements IMapper<CountryDTO, CountryEntity> {

	@Autowired
	CountryRepository repo;
	
	@Override
	public CountryDTO mapEntityToDto(CountryEntity u) {
		CountryDTO cDto = new CountryDTO();
		mapEntityToDto(cDto, u);
		return cDto;
	}

	@Override
	public CountryEntity mapDtoToEntity(CountryDTO t) {
		CountryEntity entity = new CountryEntity();
		
		// check if country with this id is already present
		Optional<CountryEntity> countryOpt = Optional.empty();
		if(t.getId()!=null) {
			countryOpt = repo.findById(t.getId());
		}
		if(countryOpt.isPresent()) {
			entity = countryOpt.get();
		}else {
			mapDtoToEntity(t, entity);
		}
		
		return entity;
	}

	@Override
	public void mapEntityToDto(CountryDTO t, CountryEntity u) {
		t.setId(u.getId());
		t.setName(u.getName());
	}

	@Override
	public void mapDtoToEntity(CountryDTO t, CountryEntity u) {
		u.setId(t.getId());
		u.setName(t.getName());
	}

	@Override
	public Set<CountryDTO> mapEntityListToDTOList(Set<CountryEntity> uList) {
		Set<CountryDTO> cDTOList=new HashSet<>();
		for(CountryEntity rEntity:PerpixlUtils.<CountryEntity>safe(uList))
		{
			CountryDTO rDTO= mapEntityToDto(rEntity);
			cDTOList.add(rDTO);
		}
		
		return cDTOList;
	}

	@Override
	public Set<CountryEntity> mapDTOListToEntityList(Set<CountryDTO> tList) {
		Set<CountryEntity> cEntityList=new HashSet<>();
		for(CountryDTO rDTO:PerpixlUtils.<CountryDTO>safe(tList))
		{
			CountryEntity rEntity= mapDtoToEntity(rDTO);
			cEntityList.add(rEntity);
		}
		return cEntityList;
	}

}
