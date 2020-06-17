package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import in.perpixl.movie.Entity.LanguageEntity;
import in.perpixl.movie.model.LanguageDTO;
import in.perpixl.movie.util.PerpixlUtils;

@Component
public class LanguageMapper implements IMapper<LanguageDTO, LanguageEntity> {

	@Override
	public LanguageDTO mapEntityToDto(LanguageEntity u) {
		LanguageDTO lDto = new LanguageDTO();
		mapEntityToDto(lDto, u);
		return lDto;
	}

	@Override
	public LanguageEntity mapDtoToEntity(LanguageDTO t) {
		LanguageEntity entity = new LanguageEntity();
		mapDtoToEntity(t, entity);
		return entity;
	}

	@Override
	public void mapEntityToDto(LanguageDTO t, LanguageEntity u) {
		t.setId(u.getId());
		t.setName(u.getName());
	}

	@Override
	public void mapDtoToEntity(LanguageDTO t, LanguageEntity u) {
		u.setId(t.getId());
		u.setName(t.getName());
	}

	@Override
	public Set<LanguageDTO> mapEntityListToDTOList(Set<LanguageEntity> uList) {
		Set<LanguageDTO> cDTOList=new HashSet<>();
		for(LanguageEntity rEntity:PerpixlUtils.<LanguageEntity>safe(uList))
		{
			LanguageDTO rDTO= mapEntityToDto(rEntity);
			cDTOList.add(rDTO);
		}
		
		return cDTOList;
	}

	@Override
	public Set<LanguageEntity> mapDTOListToEntityList(Set<LanguageDTO> tList) {
		Set<LanguageEntity> cEntityList=new HashSet<>();
		for(LanguageDTO rDTO:PerpixlUtils.<LanguageDTO>safe(tList))
		{
			LanguageEntity rEntity= mapDtoToEntity(rDTO);
			cEntityList.add(rEntity);
		}
		return cEntityList;
	}

}
