package in.perpixl.movie.service;

import java.util.List;

public interface ICRUDService<T> {

	public Long create(T m);
	public T read(Long id);
	public void update(T m);
	public void delete(Long id);
	public List<T> readAll();
}
