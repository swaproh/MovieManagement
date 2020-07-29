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

import in.perpixl.movie.entity.MovieEntity;
import in.perpixl.movie.entity.MoviePersonRoleLinkEntity;
import in.perpixl.movie.entity.PersonEntity;
import in.perpixl.movie.entity.SongEntity;
import in.perpixl.movie.mapper.MovieMapper;
import in.perpixl.movie.mapper.PersonMapper;
import in.perpixl.movie.mapper.RoleMapper;
import in.perpixl.movie.model.MovieDTO;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.repository.MovieRepository;

@Service
@Qualifier("movieservice")
public class MovieServiceImpl implements ICRUDService<MovieDTO>{
	
	@Autowired
	private MovieRepository movieRepo;
	
	@Autowired
	private MovieMapper mapper;
	
	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private MoviePersonRoleLinkServiceImpl mprLinkServiceImpl;
	
	@Autowired
	private MovieServiceImplEx movieServiceImplEx;
	
	@Transactional
	@Override
	public void create(MovieDTO m) {
		System.out.println("In MovieDao");
		MovieEntity entity = mapper.mapDtoToEntity(m);
		// link movie person role
		mprLinkServiceImpl.addMPRLinkEntityList(m,entity);
		
		movieRepo.save(entity);
	}

	@Override
	public MovieDTO read(long movieId) {
		MovieEntity me=movieRepo.findById(movieId).orElseThrow(RuntimeException::new);
		MovieDTO m=mapper.mapEntityToDto(me);
		

		// set links
		Set<MoviePersonRoleLinkEntity> mprLinks = me.getMprLink();
		Set<PersonDTO> personDTOSet = new HashSet<>();
		for(MoviePersonRoleLinkEntity link: mprLinks)
		{
			PersonEntity pe = link.getPerson();
			if(pe!=null)
			{
			PersonDTO pd = personMapper.mapEntityToDto(pe);
			RoleDTO rd = roleMapper.mapEntityToDto(link.getRole());
			pd.addRole(rd);
			personDTOSet.add(pd);
			}
		}
		m.setPersonDTO(personDTOSet);
		return m;
	}

	@Transactional
	@Override
	public void update(MovieDTO m) {
		System.out.println("In update dao");
		Optional<MovieEntity> entityOpt=movieRepo.findById(Long.parseLong(m.getMovieId().toString()));
		if(entityOpt.isPresent())
		{
			MovieEntity entity = entityOpt.get();
			// link movie person role
			mprLinkServiceImpl.addMPRLinkEntityList(m,entity);
			// clear existing company
			movieServiceImplEx.removeExistingCompanyList(entity);
			// clear existing languages
			movieServiceImplEx.removeExistingLanguageList(entity);
			mapper.mapDtoToEntity(m, entity);
			movieRepo.save(entity);
		}else
		{
			System.out.println("No entity found with id "+m.getMovieId());
		}
		System.out.println("In update dao end");
	}

	@Transactional
	@Override
	public long delete(long movieId) {
		Optional<MovieEntity> entity=movieRepo.findById(movieId);
		long rohit=0L;
		if(entity.isPresent())
		{
			MovieEntity ent = entity.get();
			System.out.println("Finding songs associated with movie "+ent.getMovieName());
			Set<SongEntity> songs = ent.getSongs();
			for(SongEntity song: songs) 
			{
				// remove movie reference from song
				song.setMovie(null);
			}
			//manage links
			/*
			 * Set<MoviePersonRoleLinkEntity> links = ent.getMprLink();
			 * for(MoviePersonRoleLinkEntity link : links) { link.setMovie(null); }
			 */
			movieRepo.delete(ent);
		}else
		{
			System.out.println("No entity found with id "+movieId);
		}
		System.out.println("delete movie dao");
		return rohit;
		
	}

	@Override
	public List<MovieDTO> readAll() {
		List<MovieEntity> movieEntityList=movieRepo.findAll();
		Set<MovieDTO> movieDTOList=mapper.mapEntityListToDTOList(new HashSet<>(movieEntityList));
		return new ArrayList<>(movieDTOList);
	}

}
