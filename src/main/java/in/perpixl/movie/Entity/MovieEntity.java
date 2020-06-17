package in.perpixl.movie.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class MovieEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long movieId;
	private String movieName;
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="movieEntity")
	private Set<PersonEntity> person =new HashSet<>();
	private String movieBasedOn;
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="movieEntity")
	private Set<CompanyEntity> productionCompany=new HashSet<CompanyEntity>();
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="movieEntity")
	private Set<CompanyEntity> distributedBy=new HashSet<CompanyEntity>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="movie_language",
	joinColumns= @JoinColumn(name="movieId"),
	inverseJoinColumns= @JoinColumn(name="id"))
	private Set<LanguageEntity> language=new HashSet<LanguageEntity>();
	
	@ManyToOne(cascade=CascadeType.ALL)
	private CountryEntity country;
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
	public Set<PersonEntity> getPerson() {
		return person;
	}
	public void addPerson(PersonEntity person) {
		this.person.add(person);
		person.getMovieEntity().add(this);
	}
	public String getMovieBasedOn() {
		return movieBasedOn;
	}
	public void setMovieBasedOn(String movieBasedOn) {
		this.movieBasedOn = movieBasedOn;
	}
	public Set<CompanyEntity> getProductionCompany() {
		return productionCompany;
	}
	public void addProductionCompany(CompanyEntity productionCompany) {
		this.productionCompany.add(productionCompany);
		productionCompany.getMovieEntity().add(this);
	}
	public Set<CompanyEntity> getDistributedBy() {
		return distributedBy;
	}
	public void addDistributedBy(CompanyEntity distributedBy) {
		this.distributedBy.add(distributedBy);
		distributedBy.getMovieEntity().add(this);
	}
	public Set<LanguageEntity> getLanguage() {
		return language;
	}
	public void addLanguage(LanguageEntity language) {
		this.language.add(language);
		language.getMovieEntityForLanguages().add(this);
	}
	public CountryEntity getCountry() {
		return country;
	}
	public void setCountry(CountryEntity country) {
		this.country = country;
		country.getMovieEntity().add(this);
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
		return "MovieEntity [movieId=" + movieId + ", movieName=" + movieName + ", person=" + person + ", movieBasedOn="
				+ movieBasedOn + ", productionCompany=" + productionCompany + ", distributedBy=" + distributedBy
				+ ", language=" + language + ", country=" + country + ", releaseDate=" + releaseDate + ", watchDate="
				+ watchDate + "]";
	}
	
	
	

}
