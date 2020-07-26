package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.entity.MovieEntity;
import in.perpixl.movie.entity.PersonEntity;
import in.perpixl.movie.entity.RaagEntity;
import in.perpixl.movie.entity.SongEntity;
import in.perpixl.movie.model.MovieDTO;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.model.RaagDTO;
import in.perpixl.movie.model.SongDTO;
import in.perpixl.movie.util.PerpixlUtils;

@Component
public class SongMapper implements IMapper<SongDTO,SongEntity>{

	@Autowired
	PersonMapper personMapper;
	
	@Autowired
	MovieMapper movieMapper;
	
	@Autowired
	RaagMapper raagMapper;
	
	@Override
	public SongDTO mapEntityToDto(SongEntity u) {
		SongDTO dto=new SongDTO();
		mapEntityToDto(dto, u);
		return dto;
	}

	@Override
	public SongEntity mapDtoToEntity(SongDTO t) {
		SongEntity entity=new SongEntity();
		mapDtoToEntity(t, entity);
		return entity;
	}

	@Override
	public void mapEntityToDto(SongDTO t, SongEntity u) {
		t.setId(u.getId());
		t.setLyrics(u.getLyrics());
		t.setTitle(u.getTitle());
		
		Set<PersonDTO> pDTOList = personMapper.mapEntityListToDTOList(u.getPersonDTO());
		t.setPersonDTO(pDTOList);
		
		MovieDTO md = movieMapper.mapEntityToDto(u.getMovie());
		t.setMovie(md);
		
		RaagDTO rd = raagMapper.mapEntityToDto(u.getRaag());
		t.setRaag(rd);
	}

	@Override
	public void mapDtoToEntity(SongDTO t, SongEntity u) {
		u.setId(t.getId());
		u.setLyrics(t.getLyrics());
		u.setTitle(t.getTitle());
		
		if(PerpixlUtils.isNotNullObject(t.getMovie()) && PerpixlUtils.isNotNullObject(t.getMovie().getMovieId()))
		{
			MovieEntity me = movieMapper.mapDtoToEntity(t.getMovie());
			u.setMovie(me);
		}
		
		/*
		 * Set<PersonEntity> pEntityList =
		 * personMapper.mapDTOListToEntityList(t.getPersonDTO()); for(PersonEntity pe:
		 * pEntityList){ if(PerpixlUtils.isNotNullObject(pe.getPersonId()))
		 * u.addPersonDTO(pe); }
		 */
		
		if(PerpixlUtils.isNotNullObject(t.getRaag()) && PerpixlUtils.isNotNullObject(t.getRaag().getId()))
		{
			RaagEntity re = raagMapper.mapDtoToEntity(t.getRaag());
			u.addRaag(re);
		}
	}

	@Override
	public Set<SongDTO> mapEntityListToDTOList(Set<SongEntity> uList) {
		Set<SongDTO> songDTOList=new HashSet<>();
		for(SongEntity rEntity:PerpixlUtils.<SongEntity>safe(uList))
		{
			SongDTO rDTO= mapEntityToDto(rEntity);
			songDTOList.add(rDTO);
		}
		
		return songDTOList;
	}

	@Override
	public Set<SongEntity> mapDTOListToEntityList(Set<SongDTO> tList) {
		Set<SongEntity> songEntityList=new HashSet<>();
		for(SongDTO rDTO:PerpixlUtils.<SongDTO>safe(tList))
		{
			SongEntity rEntity= mapDtoToEntity(rDTO);
			songEntityList.add(rEntity);
		}
		return songEntityList;
	}

}
