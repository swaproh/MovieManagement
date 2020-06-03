package in.perpixl.movie.mapper;

import java.util.List;

/*
 * T - dto
 * U - entity
 */
public interface IMapper<T, U> {
	T mapEntityToDto(U u);
	U mapDtoToEntity(T t);
	void mapEntityToDto(T t, U u);
	void mapDtoToEntity(T t, U u);
	
	List<T> mapEntityListToDTOList(List<U> uList);
	List<U> mapDTOListToEntityList(List<T> tList);
}
