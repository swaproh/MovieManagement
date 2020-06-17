package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.Entity.CompanyEntity;
import in.perpixl.movie.Entity.CountryEntity;
import in.perpixl.movie.Entity.LanguageEntity;
import in.perpixl.movie.Entity.MovieEntity;
import in.perpixl.movie.Entity.PersonEntity;
import in.perpixl.movie.model.CompanyDTO;
import in.perpixl.movie.model.LanguageDTO;
import in.perpixl.movie.model.MovieDTO;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.util.PerpixlUtils;

@Component
public class MovieMapper implements IMapper<MovieDTO, MovieEntity>{
	@Autowired
	PersonMapper personMapper;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	LanguageMapper languageMapper;
	
	@Autowired
	CountryMapper countryMapper;
	
	@Override
	public void mapDtoToEntity(MovieDTO me, MovieEntity entity)
	{
		Set<CompanyEntity> distributionCompanies = companyMapper.mapDTOListToEntityList(me.getDistributedBy());
		for(CompanyEntity cEntity : distributionCompanies) {
			entity.addDistributedBy(cEntity);
		}
		
		Set<LanguageEntity> languages = languageMapper.mapDTOListToEntityList(me.getLanguage());
		for(LanguageEntity lEntity: languages) {
			entity.addLanguage(lEntity);
		}
		entity.setMovieBasedOn(me.getMovieBasedOn());
		entity.setMovieId(me.getMovieId());
		entity.setMovieName(me.getMovieName());
		
		Set<CompanyEntity> productionCompanies = companyMapper.mapDTOListToEntityList(me.getProductionCompany());
		for(CompanyEntity cEntity : productionCompanies) {
			entity.addProductionCompany(cEntity);
		}
		entity.setReleaseDate(me.getReleaseDate());
		entity.setWatchDate(me.getWatchDate());
		
		Set<PersonEntity> pEntityList = personMapper.mapDTOListToEntityList(me.getPersonDTO());
		for(PersonEntity pe: pEntityList)
		{
			entity.addPerson(pe);
		}
		
		CountryEntity countryEntity = countryMapper.mapDtoToEntity(me.getCountry());
		entity.setCountry(countryEntity);
		
	}

	@Override
	public MovieDTO mapEntityToDto(MovieEntity me) {
		MovieDTO dto =new MovieDTO();
		mapEntityToDto(dto, me);
		return dto;
	}

	@Override
	public MovieEntity mapDtoToEntity(MovieDTO t) {
		MovieEntity entity = new MovieEntity();
		mapDtoToEntity(t, entity);
		return entity;
	}

	@Override
	public void mapEntityToDto(MovieDTO dto, MovieEntity me) {
		Set<CompanyDTO> cDTOList = companyMapper.mapEntityListToDTOList(me.getDistributedBy());
		dto.setDistributedBy(cDTOList);
		
		Set<LanguageDTO> l =languageMapper.mapEntityListToDTOList(me.getLanguage());
		dto.setLanguage(l);
		
		dto.setMovieBasedOn(me.getMovieBasedOn());
		dto.setMovieId(me.getMovieId());
		dto.setMovieName(me.getMovieName());
		
		Set<CompanyDTO> prDTOList = companyMapper.mapEntityListToDTOList(me.getProductionCompany());
		dto.setDistributedBy(prDTOList);
		
		dto.setReleaseDate(me.getReleaseDate());
		dto.setWatchDate(me.getWatchDate());
		
		Set<PersonDTO> pDTOList = personMapper.mapEntityListToDTOList(me.getPerson());
		dto.setPersonDTO(pDTOList);
	}

	@Override
	public Set<MovieDTO> mapEntityListToDTOList(Set<MovieEntity> uList) {
		Set<MovieDTO> movieDTOList = new HashSet<>();
		for(MovieEntity mEntity : PerpixlUtils.<MovieEntity>safe(uList))
		{
			MovieDTO pDTO = mapEntityToDto(mEntity);
			movieDTOList.add(pDTO);
		}
		return movieDTOList;
	}

	@Override
	public Set<MovieEntity> mapDTOListToEntityList(Set<MovieDTO> MovieDTO) {
		Set<MovieEntity> movieEntityList = new HashSet<>();
		for(MovieDTO pDto : PerpixlUtils.<MovieDTO>safe(MovieDTO))
		{
			MovieEntity pEntity = mapDtoToEntity(pDto);
			movieEntityList.add(pEntity);
		}
		return movieEntityList;
	}

}
