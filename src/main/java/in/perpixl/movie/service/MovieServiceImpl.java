package in.perpixl.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.dao.IDao;
import in.perpixl.movie.model.MovieDTO;

@Service
@Qualifier("movieservice")
public class MovieServiceImpl implements IService<MovieDTO>{
	
	@Autowired
	@Qualifier("moviedao")
	private IDao<MovieDTO> daoI;

	@Override
	public void create(MovieDTO m)
	{
		System.out.println("In Movie service Layer");
		daoI.create(m);
	}

	@Override
	public MovieDTO read(long movieId) {
		System.out.println("In service layer");
		MovieDTO m=daoI.read(movieId);
		return m;
	}

	@Override
	public void update(MovieDTO m) {
		System.out.println("In update service");
		daoI.update(m);
		System.out.println("In update service end");
	}

	@Override
	public long delete(long movieId) {
		long l=daoI.delete(movieId);
		System.out.println("delete movie service");
		return l;
	}

	@Override
	public List<MovieDTO> readAll() {
		List<MovieDTO> movieDTOList=daoI.readAll();
		return movieDTOList;
	}

}
