package in.perpixl.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.perpixl.movie.entity.LanguageEntity;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity,Long>{
	LanguageEntity findByName(String name);
}
