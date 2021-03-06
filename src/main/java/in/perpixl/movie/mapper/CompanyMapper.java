package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.entity.CompanyEntity;
import in.perpixl.movie.entity.CountryEntity;
import in.perpixl.movie.entity.RoleEntity;
import in.perpixl.movie.model.CompanyDTO;
import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.repository.CompanyRepository;
import in.perpixl.movie.util.PerpixlUtils;

@Component
public class CompanyMapper implements IMapper<CompanyDTO, CompanyEntity> {

	@Autowired
	CompanyRepository repo;
	
	@Override
	public CompanyDTO mapEntityToDto(CompanyEntity u) {
		CompanyDTO dto =new CompanyDTO();
		mapEntityToDto(dto, u);
		return dto;
	}

	@Override
	public CompanyEntity mapDtoToEntity(CompanyDTO t) {
		CompanyEntity entity = new CompanyEntity();
		
		// check if company with this id is already present
		Optional<CompanyEntity> companyOpt = Optional.empty();
		if(t.getId()!=null) {
			companyOpt = repo.findById(t.getId());
		}
		if(companyOpt.isPresent()) {
			entity = companyOpt.get();
		}else {
			mapDtoToEntity(t, entity);
		}
		return entity;
	}

	@Override
	public void mapEntityToDto(CompanyDTO t, CompanyEntity u) {
		t.setId(u.getId());
		t.setName(u.getName());
	}

	@Override
	public void mapDtoToEntity(CompanyDTO t, CompanyEntity u) {
		u.setId(t.getId());
		u.setName(t.getName());
		
	}

	@Override
	public Set<CompanyDTO> mapEntityListToDTOList(Set<CompanyEntity> uList) {
		Set<CompanyDTO> companyDTOList=new HashSet<>();
		for(CompanyEntity rEntity:PerpixlUtils.<CompanyEntity>safe(uList))
		{
			CompanyDTO rDTO= mapEntityToDto(rEntity);
			companyDTOList.add(rDTO);
		}
		
		return companyDTOList;
	}

	@Override
	public Set<CompanyEntity> mapDTOListToEntityList(Set<CompanyDTO> tList) {
		Set<CompanyEntity> cEntityList=new HashSet<>();
		for(CompanyDTO rDTO:PerpixlUtils.<CompanyDTO>safe(tList))
		{
			CompanyEntity rEntity= mapDtoToEntity(rDTO);
			cEntityList.add(rEntity);
		}
		return cEntityList;
	}

}
