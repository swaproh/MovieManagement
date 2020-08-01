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
import in.perpixl.movie.entity.CompanyEntity;
import in.perpixl.movie.mapper.CompanyMapper;
import in.perpixl.movie.model.CompanyDTO;
import in.perpixl.movie.repository.CompanyRepository;

@Service
@Qualifier("companyservice")
public class CompanyServiceImpl implements ICRUDService<CompanyDTO>{
	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private CompanyMapper mapper;
	
	@Override
	public Long create(CompanyDTO m) {
		CompanyEntity entity = mapper.mapDtoToEntity(m);
		CompanyEntity savedEntity = companyRepo.save(entity);
		return savedEntity.getId();
	}

	@Override
	public CompanyDTO read(Long id) {
		CompanyEntity me=companyRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		return mapper.mapEntityToDto(me);
	}

	@Override
	public void update(CompanyDTO m) {
		CompanyEntity entity=companyRepo.findById(m.getId())
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, m.getId())));
		mapper.mapDtoToEntity(m, entity);
		companyRepo.save(entity);
	}

	@Override
	public void delete(Long id) {
		CompanyEntity entity=companyRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		companyRepo.delete(entity);
	}

	@Override
	public List<CompanyDTO> readAll() {
		List<CompanyEntity> companyEntityList=companyRepo.findAll();
		Set<CompanyDTO> companyDTOList=mapper.mapEntityListToDTOList(new HashSet<>(companyEntityList));
		return new ArrayList<>(companyDTOList);
	}

}
