package in.perpixl.movie.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import in.perpixl.movie.entity.MovieEntity;
import in.perpixl.movie.entity.SongEntity;

public interface SongRepository extends JpaRepository<SongEntity,Long>{
	Set<SongEntity> findByMovie(MovieEntity m);
}
