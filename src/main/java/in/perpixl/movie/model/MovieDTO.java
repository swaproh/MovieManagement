package in.perpixl.movie.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MovieDTO {
	
	private Long movieId;
	private String movieName;
	private Set<PersonDTO> personDTO = new HashSet<>();
	private String movieBasedOn;
	
	private Set<CompanyDTO> productionCompany = new HashSet<>();
	private Set<CompanyDTO> distributedBy = new HashSet<>();
	private Set<LanguageDTO> language = new HashSet<>();
	private CountryDTO country;
	public CountryDTO getCountry() {
		return country;
	}
	public void setCountry(CountryDTO country) {
		this.country = country;
	}
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
	public Set<PersonDTO> getPersonDTO() {
		return personDTO;
	}
	public void setPersonDTO(Set<PersonDTO> personDTO) {
		this.personDTO = personDTO;
	}
	public String getMovieBasedOn() {
		return movieBasedOn;
	}
	public void setMovieBasedOn(String movieBasedOn) {
		this.movieBasedOn = movieBasedOn;
	}
	
	public Set<CompanyDTO> getProductionCompany() {
		return productionCompany;
	}
	public void setProductionCompany(Set<CompanyDTO> productionCompany) {
		this.productionCompany = productionCompany;
	}
	public Set<CompanyDTO> getDistributedBy() {
		return distributedBy;
	}
	public void setDistributedBy(Set<CompanyDTO> distributedBy) {
		this.distributedBy = distributedBy;
	}
	
	public Set<LanguageDTO> getLanguage() {
		return language;
	}
	public void setLanguage(Set<LanguageDTO> language) {
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
