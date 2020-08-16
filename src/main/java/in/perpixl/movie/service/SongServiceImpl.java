package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.perpixl.movie.constants.Constants;
import in.perpixl.movie.entity.PersonEntity;
import in.perpixl.movie.entity.RaagEntity;
import in.perpixl.movie.entity.SongEntity;
import in.perpixl.movie.entity.SongPersonRoleLinkEntity;
import in.perpixl.movie.mapper.PersonMapper;
import in.perpixl.movie.mapper.RoleMapper;
import in.perpixl.movie.mapper.SongMapper;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.model.SongDTO;
import in.perpixl.movie.repository.SongRepository;

@Service
@Qualifier("songservice")
public class SongServiceImpl implements ICRUDService<SongDTO>{
	@Autowired
	private SongRepository songRepo;
	@Autowired
	private SongMapper mapper;
	@Autowired
	private PersonMapper personMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private SongPersonRoleLinkServiceImpl sprLinkServiceImpl;
	
	@Transactional
	@Override
	public Long create(SongDTO m) {
		SongEntity entity = mapper.mapDtoToEntity(m);
		// link movie person role
		sprLinkServiceImpl.addSPRLinkEntityList(m,entity);
		SongEntity savedEntity = songRepo.save(entity);
		return savedEntity.getId();
	}

	@Override
	public SongDTO read(Long id) {
		SongEntity me=songRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		SongDTO m=mapper.mapEntityToDto(me);
		
		// set links
		Set<SongPersonRoleLinkEntity> mprLinks = me.getSprLink();
		Set<PersonDTO> personDTOSet = new HashSet<>();
		for(SongPersonRoleLinkEntity link: mprLinks)
		{
			PersonEntity pe = link.getPerson();
			PersonDTO pd = personMapper.mapEntityToDto(pe);
			RoleDTO rd = roleMapper.mapEntityToDto(link.getRole());
			pd.addRole(rd);
			personDTOSet.add(pd);
		}
		m.setPersonDTO(personDTOSet);
		return m;
	}

	@Transactional
	@Override
	public void update(SongDTO m) {
		SongEntity entity=songRepo.findById(m.getId())
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, m.getId())));
		// link song person role
		sprLinkServiceImpl.addSPRLinkEntityList(m,entity);
		mapper.mapDtoToEntity(m, entity);
		songRepo.save(entity);
	}

	@Override
	public void delete(Long id) {
		SongEntity entity=songRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(Constants.ENTITY_NOT_FOUND, id)));
		entity.addRaag(null);
		songRepo.delete(entity);
	}

	@Override
	public List<SongDTO> readAll(Long pageNumber, Long pageSize) {
		Pageable pageInfo = PageRequest.of(Integer.valueOf(pageNumber.toString()), Integer.valueOf(pageSize.toString()));
		Page<SongEntity> songEntityPage=songRepo.findAll(pageInfo);
		Set<SongDTO> SongDTOList=mapper.mapEntityListToDTOList(songEntityPage.toSet());
		return new ArrayList<>(SongDTOList);
	}

}
