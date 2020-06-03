package in.perpixl.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.perpixl.movie.dao.IDao;
import in.perpixl.movie.model.MovieDTO;

@Service
public class MovieServiceImpl implements IService<MovieDTO>{
	
	@Autowired
	private IDao<MovieDTO> daoI;

	@Override
	public void create(MovieDTO m)
	{
		System.out.println("In service Layer");
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
		daoI.update(m);
	}

	@Override
	public long delete(long movieId) {
		long l=daoI.delete(movieId);
		return l;
	}

}
