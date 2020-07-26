package in.perpixl.movie.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SongPersonRoleLinkEntity 
{
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="songId")
	private SongEntity song;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="personId")
	private PersonEntity person;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="roleId")
	private RoleEntity role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SongEntity getSong() {
		return song;
	}

	public void setSong(SongEntity song) {
		// prevent infinite loop
		if(this.song==null ? song==null : this.song.equals(song)) {
			return;
		}
		
		// set new movie keeping old as a backup
		SongEntity oldMovie = this.song;
		this.song = song;

		// remove old reference
		if(oldMovie!=null) {
			oldMovie.removeMprLink(this);
		}
		
		if(song!=null) {
			song.addMprLink(this);
		}
	}

	public PersonEntity getPerson() {
		return person;
	}

	public void setPerson(PersonEntity person) {
		this.person = person;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	
}
