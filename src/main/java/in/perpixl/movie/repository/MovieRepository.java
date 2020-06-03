package in.perpixl.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.perpixl.movie.Entity.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>{

}
