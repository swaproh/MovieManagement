package in.perpixl.movie.mapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	// add links to this entity
	public Set<SongPersonRoleLinkEntity> linkSongPersonRoleDTOToEntity(SongDTO me, SongEntity entity)
	{
		Set<SongPersonRoleLinkEntity> mprLinkSet = new HashSet<>();
		// set person
		Set<PersonDTO> peSet = me.getPersonDTO();
		for(PersonDTO pd: peSet)
		{
			if(pd.getPersonId()!=null)
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
		}
		
		return mprLinkSet;
	}
}
