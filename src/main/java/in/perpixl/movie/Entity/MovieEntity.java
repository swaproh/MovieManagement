package in.perpixl.movie.Entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MovieEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long movieId;
	private String movieName;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="movieEntity")
	private List<PersonEntity> person;
	private String movieBasedOn;
	private String productionCompany;
	private String distributedBy;
	private String language;
	private Date releaseDate;
	private Date watchDate;
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public List<PersonEntity> getPerson() {
		return person;
	}
	public void setPerson(List<PersonEntity> person) {
		this.person = person;
	}
	public String getMovieBasedOn() {
		return movieBasedOn;
	}
	public void setMovieBasedOn(String movieBasedOn) {
		this.movieBasedOn = movieBasedOn;
	}
	public String getProductionCompany() {
		return productionCompany;
	}
	public void setProductionCompany(String productionCompany) {
		this.productionCompany = productionCompany;
	}
	public String getDistributedBy() {
		return distributedBy;
	}
	public void setDistributedBy(String distributedBy) {
		this.distributedBy = distributedBy;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Date getWatchDate() {
		return watchDate;
	}
	public void setWatchDate(Date watchDate) {
		this.watchDate = watchDate;
	}
	
	
	

}
