package in.perpixl.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.perpixl.movie.Entity.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,Integer>{

}
