package in.perpixl.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.perpixl.movie.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long>{

}
