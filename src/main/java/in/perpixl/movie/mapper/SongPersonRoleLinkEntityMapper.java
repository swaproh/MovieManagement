package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.perpixl.movie.entity.MoviePersonRoleLinkEntity;
import in.perpixl.movie.entity.PersonEntity;
import in.perpixl.movie.entity.RoleEntity;
import in.perpixl.movie.entity.SongEntity;
import in.perpixl.movie.entity.SongPersonRoleLinkEntity;
import in.perpixl.movie.model.PersonDTO;
import in.perpixl.movie.model.RoleDTO;
import in.perpixl.movie.model.SongDTO;
import in.perpixl.movie.repository.PersonRepository;
import in.perpixl.movie.repository.RoleRepository;
import in.perpixl.movie.repository.SongRepository;

@Component
public class SongPersonRoleLinkEntityMapper {
	@Autowired
	SongRepository songRepo;
	
	@Autowired
	PersonRepository personRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	public Set<SongPersonRoleLinkEntity> linkSongPersonRoleEntityToDTO(SongEntity me)
	{
		Set<SongPersonRoleLinkEntity> mprLinkSet = new HashSet<>();
		/*
		 * Set<PersonEntity> peSet = me.getPerson(); for(PersonEntity pe: peSet) {
		 * MoviePersonRoleLinkEntity link = new MoviePersonRoleLinkEntity();
		 * link.setMovie(me); link.setPerson(pe); for(RoleEntity re: pe.getRoleEntity())
		 * { link.setRole(re); } }
		 */
		
		return mprLinkSet;
	}
	
	public Set<SongPersonRoleLinkEntity> linkSongPersonRoleDTOToEntity(SongDTO me, SongEntity entity)
	{
		Set<SongPersonRoleLinkEntity> mprLinkSet = new HashSet<>();
		// set person
		Set<PersonDTO> peSet = me.getPersonDTO();
		for(PersonDTO pd: peSet)
		{
			SongPersonRoleLinkEntity link = new SongPersonRoleLinkEntity();
			 link.setSong(entity);
			 
			 Long personID = pd.getPersonId();
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
			 mprLinkSet.add(link);
		}
		
		return mprLinkSet;
	}
}
