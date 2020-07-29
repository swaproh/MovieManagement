package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.entity.PersonEntity;
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
	public void create(SongDTO m) {
		SongEntity entity = mapper.mapDtoToEntity(m);
		// link movie person role
		sprLinkServiceImpl.addSPRLinkEntityList(m,entity);
		songRepo.save(entity);
	}

	@Override
	public SongDTO read(long roleId) {
		SongEntity me=songRepo.findById(roleId).orElseThrow(RuntimeException::new);
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
		Optional<SongEntity> entityOpt=songRepo.findById(Long.parseLong(m.getId().toString()));
		if(entityOpt.isPresent())
		{
			SongEntity entity = entityOpt.get();
			// link song person role
			sprLinkServiceImpl.addSPRLinkEntityList(m,entity);
			mapper.mapDtoToEntity(m, entity);
			songRepo.save(entity);
		}
	}

	@Override
	public long delete(long roleId) {
		Optional<SongEntity> entity=songRepo.findById(roleId);
		long rohit=0L;
		if(entity.isPresent())
		{
			SongEntity ent = entity.get();
			songRepo.delete(ent);
		}
		return rohit;
	}

	@Override
	public List<SongDTO> readAll() {
		List<SongEntity> SongEntityList=songRepo.findAll();
		Set<SongDTO> SongDTOList=mapper.mapEntityListToDTOList(new HashSet<>(SongEntityList));
		return new ArrayList<>(SongDTOList);
	}

}
