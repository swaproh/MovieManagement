package in.perpixl.movie.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class LanguageEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	/*
	 * @ManyToMany
	 * 
	 * @JoinTable(name="movie_company", joinColumns= @JoinColumn(name="Language_Id",
	 * referencedColumnName="id"), inverseJoinColumns= @JoinColumn(name="Movid_Id",
	 * referencedColumnName="movieId")) private Set<MovieEntity> movieEntity = new
	 * HashSet<>();
	 */
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="language")
	private Set<MovieEntity> movieEntityForLanguages = new HashSet<>();
	public Set<MovieEntity> getMovieEntityForLanguages() {
		return new HashSet<>(movieEntityForLanguages);
	}
	public void addMovieEntityForLanguages(MovieEntity movieEntity) {
		// prevent endless loop
		if(this.movieEntityForLanguages.contains(movieEntity)) {
			return;
		}
		
		// add movie to existing language
		this.movieEntityForLanguages.add(movieEntity);
		// add myself to the movie entity
		movieEntity.addLanguage(this);
	}
	public void removeMovieEntityForLanguages(MovieEntity movieEntity) {
		// prevent endless loop
		if(!this.movieEntityForLanguages.contains(movieEntity)) {
			return;
		}
		
		// remove movie from existing language
		this.movieEntityForLanguages.remove(movieEntity);
		// add myself to the movie entity
		movieEntity.removeLanguage(this);
		
	}

	/*
	 * public Set<MovieEntity> getMovieEntity() { return new HashSet<>(movieEntity);
	 * } public void setMovieEntity(Set<MovieEntity> movieEntity) { this.movieEntity
	 * = movieEntity; }
	 */
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
		LanguageEntity other = (LanguageEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
