package in.perpixl.movie.mapper;

import java.util.Set;

/*
 * T - dto
 * U - entity
 */
public interface IMapper<T, U> {
	T mapEntityToDto(U u);
	U mapDtoToEntity(T t);
	void mapEntityToDto(T t, U u);
	void mapDtoToEntity(T t, U u);
	
	Set<T> mapEntityListToDTOList(Set<U> uList);
	Set<U> mapDTOListToEntityList(Set<T> tList);
}
