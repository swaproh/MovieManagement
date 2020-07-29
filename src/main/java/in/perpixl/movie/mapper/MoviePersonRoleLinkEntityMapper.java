package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.entity.MovieEntity;
import in.perpixl.movie.entity.MoviePersonRoleLinkEntity;
import in.perpixl.movie.entity.PersonEntity;
import in.perpixl.movie.entity.RoleEntity;
import in.perpixl.movie.model.MovieDTO;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.repository.MovieRepository;
import in.perpixl.movie.repository.PersonRepository;
import in.perpixl.movie.repository.RoleRepository;

@Component
public class MoviePersonRoleLinkEntityMapper {
	@Autowired
	MovieRepository movieRepo;
	
	@Autowired
	PersonRepository personRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	public Set<MoviePersonRoleLinkEntity> linkMoviePersonRoleDTOToEntity(MovieDTO me, MovieEntity entity)
	{
		Set<MoviePersonRoleLinkEntity> mprLinkSet = new HashSet<>();
		// set person
		Set<PersonDTO> peSet = me.getPersonDTO();
		for(PersonDTO pd: peSet)
		{
			 MoviePersonRoleLinkEntity link = new MoviePersonRoleLinkEntity();
			 link.setMovie(entity);
			 
			 Long personID = pd.getPersonId();
			 if(personID!=null)
			 {
			 Optional<PersonEntity> peOpt = personRepo.findById(personID);
			 if(peOpt.isPresent())
			 {
				 PersonEntity pe = peOpt.get();
				 link.setPerson(pe);
			 }
			 
			 // set dto
			 Set<RoleDTO> rdSet = pd.getRoles();
			 for(RoleDTO rd: rdSet)
			 {
				 Long roleId = rd.getRoleId();
				 Optional<RoleEntity> reOpt = roleRepo.findById(roleId);
				 if(reOpt.isPresent())
				 {
					 RoleEntity re = reOpt.get();
					 link.setRole(re);
				 }
			 }
			 }
			 mprLinkSet.add(link);
		}
		
		return mprLinkSet;
	}
}
