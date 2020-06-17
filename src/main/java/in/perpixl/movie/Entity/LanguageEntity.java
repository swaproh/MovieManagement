package in.perpixl.movie.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class LanguageEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@ManyToMany
	@JoinTable(name="movie_company",
	joinColumns= @JoinColumn(name="id"),
	inverseJoinColumns= @JoinColumn(name="movieId"))
	private Set<MovieEntity> movieEntity = new HashSet<>();
	
	@ManyToMany(mappedBy="language",cascade=CascadeType.ALL)
	private Set<MovieEntity> movieEntityForLanguages = new HashSet<>();
	public Set<MovieEntity> getMovieEntityForLanguages() {
		return movieEntityForLanguages;
	}
	public void setMovieEntityForLanguages(Set<MovieEntity> movieEntityForLanguages) {
		this.movieEntityForLanguages = movieEntityForLanguages;
	}
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
		return "LanguageEntity [id=" + id + ", name=" + name + "]";
	}
	

}
