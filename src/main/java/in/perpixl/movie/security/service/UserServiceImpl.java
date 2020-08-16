package in.perpixl.movie.security.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.perpixl.movie.security.entity.User;
import in.perpixl.movie.security.repository.UserRepository;

@Service(value="userService")
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUname(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("Invalid Username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getUname(), user.getPassword(), getAuthority());
	}

	private Collection<? extends GrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public User findOne(String username) {
		return userRepo.findByUname(username);
	}
	
}
