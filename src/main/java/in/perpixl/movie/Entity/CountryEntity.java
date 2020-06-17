package in.perpixl.movie.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CountryEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="country")
	private Set<MovieEntity> movieEntity= new HashSet<>();
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="country")
	private PersonEntity personEntity;
	
	public Set<MovieEntity> getMovieEntity() {
		return movieEntity;
	}
	public void setMovieEntity(Set<MovieEntity> movieEntity) {
		this.movieEntity = movieEntity;
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
	@Override
	public String toString() {
		return "CountryEntity [id=" + id + ", name=" + name + "]";
	}
	

}
