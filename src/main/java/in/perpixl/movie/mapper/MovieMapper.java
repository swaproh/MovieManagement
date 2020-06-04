package in.perpixl.movie.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.Entity.MovieEntity;
import in.perpixl.movie.Entity.PersonEntity;
import in.perpixl.movie.model.MovieDTO;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.util.PerpixlUtils;

@Component
public class MovieMapper implements IMapper<MovieDTO, MovieEntity>{
	@Autowired
	PersonMapper personMapper;
	
	@Override
	public void mapDtoToEntity(MovieDTO me, MovieEntity entity)
	{
		entity.setDistributedBy(me.getDistributedBy());
		entity.setLanguage(me.getLanguage());
		entity.setMovieBasedOn(me.getMovieBasedOn());
		entity.setMovieId(me.getMovieId());
		entity.setMovieName(me.getMovieName());
		entity.setProductionCompany(me.getProductionCompany());
		entity.setReleaseDate(me.getReleaseDate());
		entity.setWatchDate(me.getWatchDate());
		
		List<PersonEntity> pEntityList = personMapper.mapDTOListToEntityList(me.getPersonDTO());
		for(PersonEntity pe: pEntityList)
		{
			pe.setMovieEntity(entity);
		}
		entity.setPerson(pEntityList);
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
		dto.setDistributedBy(me.getDistributedBy());
		dto.setLanguage(me.getLanguage());
		dto.setMovieBasedOn(me.getMovieBasedOn());
		dto.setMovieId(me.getMovieId());
		dto.setMovieName(me.getMovieName());
		dto.setProductionCompany(me.getProductionCompany());
		dto.setReleaseDate(me.getReleaseDate());
		dto.setWatchDate(me.getWatchDate());
		
		List<PersonDTO> pDTOList = personMapper.mapEntityListToDTOList(me.getPerson());
		dto.setPersonDTO(pDTOList);
	}

	@Override
	public List<MovieDTO> mapEntityListToDTOList(List<MovieEntity> uList) {
		List<MovieDTO> movieDTOList = new ArrayList<>();
		for(MovieEntity mEntity : PerpixlUtils.<MovieEntity>safe(uList))
		{
			MovieDTO pDTO = mapEntityToDto(mEntity);
			movieDTOList.add(pDTO);
		}
		return movieDTOList;
	}

	@Override
	public List<MovieEntity> mapDTOListToEntityList(List<MovieDTO> MovieDTO) {
		List<MovieEntity> movieEntityList = new ArrayList<>();
		for(MovieDTO pDto : PerpixlUtils.<MovieDTO>safe(MovieDTO))
		{
			MovieEntity pEntity = mapDtoToEntity(pDto);
			movieEntityList.add(pEntity);
		}
		return movieEntityList;
	}

}
