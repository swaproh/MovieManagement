package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.constants.Constants;
import in.perpixl.movie.entity.LanguageEntity;
import in.perpixl.movie.mapper.LanguageMapper;
import in.perpixl.movie.model.LanguageDTO;
import in.perpixl.movie.repository.LanguageRepository;

@Service
@Qualifier("languageservice")
public class LanguageServiceImpl implements ICRUDService<LanguageDTO>{
	@Autowired
	private LanguageRepository languageRepo;
	@Autowired
	private LanguageMapper mapper;
	
	@Override
	public Long create(LanguageDTO m) {
		LanguageEntity entity = mapper.mapDtoToEntity(m);
		LanguageEntity savedEntity = languageRepo.save(entity);
		return savedEntity.getId();
	}

	@Override
	public LanguageDTO read(Long languageId) {
		LanguageEntity me=languageRepo.findById(languageId)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, languageId)));
		LanguageDTO m=mapper.mapEntityToDto(me);
		return m;
	}

	@Override
	public void update(LanguageDTO m) {
		LanguageEntity entity=languageRepo.findById(m.getId())
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, m.getId())));
		mapper.mapDtoToEntity(m, entity);
		languageRepo.save(entity);
	}

	@Override
	public void delete(Long id) {
		LanguageEntity entity=languageRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		languageRepo.delete(entity);
	}

	@Override
	public List<LanguageDTO> readAll(Long pageNumber, Long pageSize) {
		List<LanguageEntity> LanguageEntityList=languageRepo.findAll();
		Set<LanguageDTO> LanguageDTOList=mapper.mapEntityListToDTOList(new HashSet<>(LanguageEntityList));
		return new ArrayList<>(LanguageDTOList);
	}

}
