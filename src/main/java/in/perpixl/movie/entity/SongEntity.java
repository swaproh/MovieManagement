package in.perpixl.movie.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SongEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String lyrics;
	
	@ManyToMany(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name="artist_song",
	joinColumns= @JoinColumn(name="Song_Id", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="Person_Id", referencedColumnName="personId"))
	private Set<PersonEntity> personDTO=new HashSet<>();
	
	@OneToMany(mappedBy="song", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<SongPersonRoleLinkEntity> sprLink=new HashSet<>();
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH})
	private RaagEntity raag;
	
	@ManyToOne
	private MovieEntity movie;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public MovieEntity getMovie() {
		return movie;
	}
	public void setMovie(MovieEntity movie) {
		// prevent endless loop
		if(this.movie==null ? movie==null : this.movie.equals(movie)) {
			return;
		}
		
		// set new keeping old as a backup
		MovieEntity oldMovie = this.movie;
		this.movie = movie;
		
		// remove song from old movie
		if(oldMovie!=null)
		oldMovie.removeSong(this);
		
		// set myself to new movie
		if(movie!=null) {
			movie.addSong(this);
		}
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	public Set<PersonEntity> getPersonDTO() {
		return personDTO;
	}
	public void addPersonDTO(PersonEntity personDTO) {
		// prevent endless loop
		if(this.personDTO.contains(personDTO)) {
			return;
		}
		
		// add person to list
		this.personDTO.add(personDTO);
		
		// add myself to the person
		personDTO.addSong(this);
	}
	
	public RaagEntity getRaag() {
		return raag;
	}
	public void addRaag(RaagEntity raag) {
		// prevent endless loop
		if(this.raag==null ? raag==null : this.raag.equals(raag)) {
			return;
		}
		
		// set new raag keeping old as a backup
		RaagEntity oldRaag = this.raag;
		this.raag = raag;
		
		// remove song from old raag
		if(oldRaag!=null)
			oldRaag.removeSong(this);
		
		// add myself to new raag
		if(raag!=null)
			raag.addSong(this);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SongEntity other = (SongEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Set<SongPersonRoleLinkEntity> getSprLink() {
		return sprLink;
	}
	
	public void addSprLink(SongPersonRoleLinkEntity songPersonRoleLinkEntity) {
		// prevent endless loop
		if(this.sprLink.contains(songPersonRoleLinkEntity)) {
			return;
		}
		// add to existing
		this.sprLink.add(songPersonRoleLinkEntity);
		
		// add myself to link
		songPersonRoleLinkEntity.setSong(this);
	}
	
	public void removeSprLink(SongPersonRoleLinkEntity songPersonRoleLinkEntity) {
		// prevent endless loop
		if(!this.sprLink.contains(songPersonRoleLinkEntity)) {
			return;
		}
		// remove from existing
		this.sprLink.remove(songPersonRoleLinkEntity);
		
		// remove myself from link
		songPersonRoleLinkEntity.setSong(null);
	}
}
