package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.entity.RaagEntity;
import in.perpixl.movie.model.RaagDTO;
import in.perpixl.movie.util.PerpixlUtils;

@Component
public class RaagMapper implements IMapper<RaagDTO,RaagEntity>{

	@Autowired
	SwarSamuhMapper swarSamuhMapper;
	
	@Override
	public RaagDTO mapEntityToDto(RaagEntity u) {
		RaagDTO dto = null;
		if(u!=null) {
		 dto=new RaagDTO();
		mapEntityToDto(dto, u);
		}
		return dto;
	}

	@Override
	public RaagEntity mapDtoToEntity(RaagDTO t) {
		RaagEntity entity =null;
		if(t!=null) {
		 entity=new RaagEntity();
		mapDtoToEntity(t, entity);
		}
		return entity;
	}

	@Override
	public void mapEntityToDto(RaagDTO t, RaagEntity u) {
		t.setId(u.getId());
		t.setName(u.getName());
		t.setAaroh(u.getAaroh());
		t.setAvroh(u.getAvroh());
		t.setJaati(u.getJaati());
		t.setPakad(u.getPakad());
		t.setSamay(u.getSamay());
		t.setSamvaadiSwar(u.getSamvaadiSwar());
		t.setSandarbh(u.getSandarbh());
		t.setThaat(u.getThaat());
		t.setVaadiSwar(u.getVaadiSwar());
		t.setVarjyaSwar(u.getVarjyaSwar());
		t.setGat(u.getGat());
		t.setAalapi(swarSamuhMapper.mapEntityToDto(u.getAalapi()));
		t.setTaana(swarSamuhMapper.mapEntityToDto(u.getTaana()));
		t.setSwarVistar(swarSamuhMapper.mapEntityToDto(u.getSwarVistar()));
	}

	@Override
	public void mapDtoToEntity(RaagDTO t, RaagEntity u) {
		u.setId(t.getId());
		u.setName(t.getName());
		u.setAaroh(t.getAaroh());
		u.setAvroh(t.getAvroh());
		u.setJaati(t.getJaati());
		u.setPakad(t.getPakad());
		u.setSamay(t.getSamay());
		u.setSamvaadiSwar(t.getSamvaadiSwar());
		u.setSandarbh(t.getSandarbh());
		u.setThaat(t.getThaat());
		u.setVaadiSwar(t.getVaadiSwar());
		u.setVarjyaSwar(t.getVarjyaSwar());
		u.setGat(t.getGat());
		u.setAalapi(swarSamuhMapper.mapDtoToEntity(t.getAalapi()));
		u.setTaana(swarSamuhMapper.mapDtoToEntity(t.getTaana()));
		u.setSwarVistar(swarSamuhMapper.mapDtoToEntity(t.getSwarVistar()));
	}

	@Override
	public Set<RaagDTO> mapEntityListToDTOList(Set<RaagEntity> uList) {
		Set<RaagDTO> raagDTOList=new HashSet<>();
		for(RaagEntity rEntity:PerpixlUtils.<RaagEntity>safe(uList))
		{
			RaagDTO rDTO= mapEntityToDto(rEntity);
			raagDTOList.add(rDTO);
		}
		
		return raagDTOList;
	}

	@Override
	public Set<RaagEntity> mapDTOListToEntityList(Set<RaagDTO> tList) {
		Set<RaagEntity> raagEntityList=new HashSet<>();
		for(RaagDTO rDTO:PerpixlUtils.<RaagDTO>safe(tList))
		{
			RaagEntity rEntity= mapDtoToEntity(rDTO);
			raagEntityList.add(rEntity);
		}
		return raagEntityList;
	}

}
