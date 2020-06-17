package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.Entity.MovieEntity;
import in.perpixl.movie.mapper.MovieMapper;
import in.perpixl.movie.model.MovieDTO;
import in.perpixl.movie.repository.MovieRepository;

@Service
@Qualifier("movieservice")
public class MovieServiceImpl implements IService<MovieDTO>{
	
	@Autowired
	private MovieRepository movieRepo;
	@Autowired
	private MovieMapper mapper;
	
	@Override
	public void create(MovieDTO m) {
		System.out.println("In MovieDao");
		MovieEntity entity = mapper.mapDtoToEntity(m);
		movieRepo.save(entity);
	}

	@Override
	public MovieDTO read(long movieId) {
		MovieEntity me=movieRepo.findById(movieId).orElseThrow(RuntimeException::new);
		MovieDTO m=mapper.mapEntityToDto(me);
		return m;
	}

	@Override
	public void update(MovieDTO m) {
		System.out.println("In update dao");
		Optional<MovieEntity> entity=movieRepo.findById(Long.parseLong(m.getMovieId().toString()));
		if(entity.isPresent())
		{
			MovieEntity ent = entity.get();
			mapper.mapDtoToEntity(m, ent);
			movieRepo.save(ent);
		}
		System.out.println("In update dao end");
	}

	@Override
	public long delete(long movieId) {
		Optional<MovieEntity> entity=movieRepo.findById(movieId);
		long rohit=0L;
		if(entity.isPresent())
		{
			MovieEntity ent = entity.get();
	
			movieRepo.delete(ent);
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
