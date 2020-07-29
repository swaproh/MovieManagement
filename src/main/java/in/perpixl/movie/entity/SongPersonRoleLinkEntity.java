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
			oldMovie.removeSprLink(this);
		}
		
		if(song!=null) {
			song.addSprLink(this);
		}
	}

	public PersonEntity getPerson() {
		return person;
	}

	public void setPerson(PersonEntity person) {
		// prevent infinite loop
		if(this.person==null ? person==null : this.person.equals(person)) {
			return;
		}
		
		// set new movie keeping old as a backup
		PersonEntity oldPerson = this.person;
		this.person = person;

		// remove old reference
		if(oldPerson!=null) {
			oldPerson.removeSprLink(this);
		}
		
		if(person!=null) {
			person.addSprLink(this);
		}
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		// prevent infinite loop
		if(this.role==null ? role==null : this.role.equals(role)) {
			return;
		}
		
		// set new movie keeping old as a backup
		RoleEntity oldRole = this.role;
		this.role = role;

		// remove old reference
		if(oldRole!=null) {
			oldRole.removeSprLink(this);
		}
		
		if(role!=null) {
			role.addSprLink(this);
		}
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
		SongPersonRoleLinkEntity other = (SongPersonRoleLinkEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
