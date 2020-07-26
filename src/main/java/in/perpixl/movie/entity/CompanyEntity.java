package in.perpixl.movie.entity;

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
public class CompanyEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="productionCompany")
	private Set<MovieEntity> movieEntityProd = new HashSet<>();
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="distributedBy")
	private Set<MovieEntity> movieEntityDist = new HashSet<>();
	
	public Set<MovieEntity> getMovieEntityProd() {
		return new HashSet<>(movieEntityProd);
	}
	public Set<MovieEntity> getMovieEntityDist() {
		return new HashSet<>(movieEntityDist);
	}
	public void addMovieEntityProd(MovieEntity movieEntity) {
		// prevent endless loop
		if(this.movieEntityProd.contains(movieEntity)) {
			return;
		}
		
		// add movie to company
		this.movieEntityProd.add(movieEntity);
		
		// add me to this movie
		movieEntity.addProductionCompany(this);
	}
	public void removeMovieEntityProd(MovieEntity movieEntity) {
		// prevent endless loop
		if(!this.movieEntityProd.contains(movieEntity)) {
			return;
		}
		
		// remove movie from company
		this.movieEntityProd.remove(movieEntity);
		
		// remove me from this movie
		movieEntity.removeProductionCompany(this);
		
	}
	public void addMovieEntityDist(MovieEntity movieEntity) {
		// prevent endless loop
		if(this.movieEntityDist.contains(movieEntity)) {
			return;
		}
		
		// add movie to company
		this.movieEntityDist.add(movieEntity);
		
		// add me to this movie
		movieEntity.addDistributedBy(this);
		
	}
	public void removeMovieEntityDist(MovieEntity movieEntity) {
		// prevent endless loop
		if(!this.movieEntityDist.contains(movieEntity)) {
			return;
		}
		
		// remove movie from company
		this.movieEntityDist.remove(movieEntity);
		
		// remove me from this movie
		movieEntity.removeDistributedBy(this);
		
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
		return "CompanyEntity [id=" + id + ", name=" + name + "]";
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
		CompanyEntity other = (CompanyEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
