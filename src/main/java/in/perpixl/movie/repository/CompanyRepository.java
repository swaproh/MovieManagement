package in.perpixl.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.perpixl.movie.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity,Long>{

}
