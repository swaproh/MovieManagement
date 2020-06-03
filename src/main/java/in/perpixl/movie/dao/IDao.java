package in.perpixl.movie.dao;

public interface IDao<T> {

	public void create(T m);
	public T read(long id);
	public void update(T m);
	public long delete(long id);
	

}
