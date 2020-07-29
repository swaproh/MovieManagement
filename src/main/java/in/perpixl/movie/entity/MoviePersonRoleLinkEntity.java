package in.perpixl.movie.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MoviePersonRoleLinkEntity 
{
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="movieId")
	private MovieEntity movie;
	
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

	public MovieEntity getMovie() {
		return movie;
	}

	public void setMovie(MovieEntity movie) {
		// prevent infinite loop
		if(this.movie==null ? movie==null : this.movie.equals(movie)) {
			return;
		}
		
		// set new movie keeping old as a backup
		MovieEntity oldMovie = this.movie;
		this.movie = movie;

		// remove old reference
		if(oldMovie!=null) {
			oldMovie.removeMprLink(this);
		}
		
		if(movie!=null) {
			movie.addMprLink(this);
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
			oldPerson.removeMprLink(this);
		}
		
		if(person!=null) {
			person.addMprLink(this);
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
			oldRole.removeMprLink(this);
		}
		
		if(role!=null) {
			role.addMprLink(this);
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
		MoviePersonRoleLinkEntity other = (MoviePersonRoleLinkEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
