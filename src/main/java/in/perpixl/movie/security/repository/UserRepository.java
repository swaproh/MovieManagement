package in.perpixl.movie.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.perpixl.movie.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUname(String uname);
}
