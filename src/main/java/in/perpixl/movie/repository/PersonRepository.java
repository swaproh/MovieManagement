package in.perpixl.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.perpixl.movie.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long>{


}
