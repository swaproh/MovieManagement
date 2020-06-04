package in.perpixl.movie.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MovieDTO {
	
	private Long movieId;
	private String movieName;
	private List<PersonDTO> personDTO;
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
	public List<PersonDTO> getPersonDTO() {
		return personDTO;
	}
	public void setPersonDTO(List<PersonDTO> personDTO) {
		this.personDTO = personDTO;
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
	@Override
	public String toString() {
		return "MovieDTO [movieId=" + movieId + ", movieName=" + movieName + ", personDTO=" + personDTO
				+ ", movieBasedOn=" + movieBasedOn + ", productionCompany=" + productionCompany + ", distributedBy="
				+ distributedBy + ", language=" + language + ", releaseDate=" + releaseDate + ", watchDate=" + watchDate
				+ "]";
	}
	
	
	
	

}
