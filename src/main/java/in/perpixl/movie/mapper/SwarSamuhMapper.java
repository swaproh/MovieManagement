package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import in.perpixl.movie.entity.SwarSamuhEntity;
import in.perpixl.movie.model.SwarSamuhDTO;
import in.perpixl.movie.util.PerpixlUtils;

@Component
public class SwarSamuhMapper implements IMapper<SwarSamuhDTO,SwarSamuhEntity>{

	@Override
	public SwarSamuhDTO mapEntityToDto(SwarSamuhEntity u) {
		SwarSamuhDTO dto=new SwarSamuhDTO();
		mapEntityToDto(dto, u);
		return dto;
	}

	@Override
	public SwarSamuhEntity mapDtoToEntity(SwarSamuhDTO t) {
		SwarSamuhEntity entity=new SwarSamuhEntity();
		if(t!=null)
			mapDtoToEntity(t, entity);
		return entity;
	}

	@Override
	public void mapEntityToDto(SwarSamuhDTO t, SwarSamuhEntity u) {
		t.setSthayi(u.getSthayi());
		t.setAntara(u.getAntara());
	}

	@Override
	public void mapDtoToEntity(SwarSamuhDTO t, SwarSamuhEntity u) {
		u.setSthayi(t.getSthayi());
		u.setAntara(t.getAntara());
	}

	@Override
	public Set<SwarSamuhDTO> mapEntityListToDTOList(Set<SwarSamuhEntity> uList) {
		Set<SwarSamuhDTO> SwarSamuhDTOList=new HashSet<>();
		for(SwarSamuhEntity rEntity:PerpixlUtils.<SwarSamuhEntity>safe(uList))
		{
			SwarSamuhDTO rDTO= mapEntityToDto(rEntity);
			SwarSamuhDTOList.add(rDTO);
		}
		
		return SwarSamuhDTOList;
	}

	@Override
	public Set<SwarSamuhEntity> mapDTOListToEntityList(Set<SwarSamuhDTO> tList) {
		Set<SwarSamuhEntity> SwarSamuhEntityList=new HashSet<>();
		for(SwarSamuhDTO rDTO:PerpixlUtils.<SwarSamuhDTO>safe(tList))
		{
			SwarSamuhEntity rEntity= mapDtoToEntity(rDTO);
			SwarSamuhEntityList.add(rEntity);
		}
		return SwarSamuhEntityList;
	}

}
