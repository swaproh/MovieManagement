package in.perpixl.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.perpixl.movie.Entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,Long>{

}
