package in.perpixl.movie.service;

public interface IService<T> {

	public void create(T m);
	public T read(long id);
	public void update(T m);
	public long delete(long id);
	
	
	
	
	
	
	
	

}
