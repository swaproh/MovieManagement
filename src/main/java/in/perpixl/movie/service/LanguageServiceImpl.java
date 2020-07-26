package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
	public void create(LanguageDTO m) {
		LanguageEntity entity = mapper.mapDtoToEntity(m);
		languageRepo.save(entity);
	}

	@Override
	public LanguageDTO read(long languageId) {
		LanguageEntity me=languageRepo.findById(languageId).orElseThrow(RuntimeException::new);
		LanguageDTO m=mapper.mapEntityToDto(me);
		return m;
	}

	@Override
	public void update(LanguageDTO m) {
		Optional<LanguageEntity> entity=languageRepo.findById(Long.parseLong(m.getId().toString()));
		if(entity.isPresent())
		{
			LanguageEntity ent = entity.get();
			mapper.mapDtoToEntity(m, ent);
			languageRepo.save(ent);
		}
	}

	@Override
	public long delete(long languageId) {
		Optional<LanguageEntity> entity=languageRepo.findById(languageId);
		long rohit=0L;
		if(entity.isPresent())
		{
			LanguageEntity ent = entity.get();
	
			languageRepo.delete(ent);
		}
		return rohit;
	}

	@Override
	public List<LanguageDTO> readAll() {
		List<LanguageEntity> LanguageEntityList=languageRepo.findAll();
		Set<LanguageDTO> LanguageDTOList=mapper.mapEntityListToDTOList(new HashSet<>(LanguageEntityList));
		return new ArrayList<>(LanguageDTOList);
	}

}
