package in.perpixl.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.perpixl.movie.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity,Long>{

}
