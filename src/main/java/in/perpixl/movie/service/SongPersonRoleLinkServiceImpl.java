package in.perpixl.movie.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.perpixl.movie.entity.SongEntity;
import in.perpixl.movie.entity.SongPersonRoleLinkEntity;
import in.perpixl.movie.mapper.SongPersonRoleLinkEntityMapper;
import in.perpixl.movie.model.SongDTO;
import in.perpixl.movie.repository.SongPersonRoleLinkEntityRepository;

@Service
@Qualifier("sprLink")
public class SongPersonRoleLinkServiceImpl{
	@Autowired
	private SongPersonRoleLinkEntityRepository sprLinkRepo;
	@Autowired
	private SongPersonRoleLinkEntityMapper sprLinkMapper;
	
	public long delete(long id) {
		Optional<SongPersonRoleLinkEntity> entity=sprLinkRepo.findById(id);
		long rohit=0L;
		if(entity.isPresent())
		{
			SongPersonRoleLinkEntity ent = entity.get();
			sprLinkRepo.delete(ent);
		}
		return rohit;
	}
	
	public void addSPRLinkEntityList(SongDTO m, SongEntity entity)
	{
		// remove existing links
		unlinkExistingSPRLinks(entity);
		//entity.getSprLink().clear();
		// add new links
		Set<SongPersonRoleLinkEntity> linkEntity = sprLinkMapper.linkSongPersonRoleDTOToEntity(m,entity);
		 for(SongPersonRoleLinkEntity link: linkEntity) {
				entity.addSprLink(link);
			}
	}
	
	// delete existing links associated with entity
	public void unlinkExistingSPRLinks(SongEntity entity)
	{
		Set<SongPersonRoleLinkEntity> existingSprLinks = entity.getSprLink();
		sprLinkRepo.deleteAll(existingSprLinks);
		existingSprLinks.clear();
		sprLinkRepo.flush();
	}
	
}
