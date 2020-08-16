package in.perpixl.movie.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CountryEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="country")
	private Set<MovieEntity> movieEntity= new HashSet<>();
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="country")
	private Set<PersonEntity> personEntity = new HashSet<>();
	
	public Set<MovieEntity> getMovieEntity() {
		return new HashSet<>(movieEntity);
	}
	public void removeMovieEntity(MovieEntity movieEntity) {
		// prevent endless loop
		if(!this.movieEntity.contains(movieEntity)) {
			return;
		}
		
		// remove account
		this.movieEntity.remove(movieEntity);
		
		// remove myself from movie
		movieEntity.setCountry(null);
	}
	public void addMovieEntity(MovieEntity movieEntity) {
		// prevent endless loop
		if(this.movieEntity.contains(movieEntity)) {
			return;
		}
		
		// add new movie
		this.movieEntity.add(movieEntity);
		
		// set myself into movie entity
		movieEntity.setCountry(this);
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<PersonEntity> getPersonEntity() {
		return personEntity;
	}
	public void addPersonEntity(PersonEntity personEntity) {
		// prevent endless loop
		if(this.personEntity.contains(personEntity)) {
			return;
		}
		
		// add new movie
		this.personEntity.add(personEntity);
		
		// set myself into movie entity
		personEntity.setCountry(this);
	}
	
	public void removePersonEntity(PersonEntity personEntity) {
		// prevent endless loop
		if(!this.personEntity.contains(personEntity)) {
			return;
		}
		
		// remove account
		this.personEntity.remove(personEntity);
		
		// remove myself from movie
		personEntity.setCountry(null);
	}
	@Override
	public String toString() {
		return "CountryEntity [id=" + id + ", name=" + name + "]";
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
		CountryEntity other = (CountryEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
