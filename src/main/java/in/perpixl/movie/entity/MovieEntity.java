package in.perpixl.movie.entity;

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
import javax.persistence.OneToMany;

@Entity
public class MovieEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long movieId;
	private String movieName;
	
	/*
	 * @ManyToMany(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
	 * 
	 * @JoinTable(name="movie_crew", joinColumns= @JoinColumn(name="Movie_Id",
	 * referencedColumnName="movieId"),
	 * inverseJoinColumns= @JoinColumn(name="Person_Id",
	 * referencedColumnName="personId")) private Set<PersonEntity> person =new
	 * HashSet<>();
	 */
	
	@OneToMany(mappedBy="movie", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<MoviePersonRoleLinkEntity> mprLink=new HashSet<>();
	
	private String movieBasedOn;
	
	@ManyToMany(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name="movie_prod_company",
	joinColumns= @JoinColumn(name="Movie_Id", referencedColumnName="movieId"),
	inverseJoinColumns= @JoinColumn(name="Prod_Company_Id", referencedColumnName="id"))
	private Set<CompanyEntity> productionCompany=new HashSet<CompanyEntity>();
	
	@ManyToMany(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name="movie_dist_company",
	joinColumns= @JoinColumn(name="Movie_Id", referencedColumnName="movieId"),
	inverseJoinColumns= @JoinColumn(name="Dist_Company_Id", referencedColumnName="id"))
	private Set<CompanyEntity> distributedBy=new HashSet<CompanyEntity>();
	
	@ManyToMany(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name="movie_language",
	joinColumns= @JoinColumn(name="Movie_Id", referencedColumnName="movieId"),
	inverseJoinColumns= @JoinColumn(name="Language_Id", referencedColumnName="id"))
	private Set<LanguageEntity> language=new HashSet<LanguageEntity>();
	
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
	private CountryEntity country;
	
	@OneToMany(cascade= {CascadeType.MERGE, CascadeType.REFRESH}, mappedBy="movie")
	private Set<SongEntity> songs = new HashSet<>();
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

	/*
	 * public Set<PersonEntity> getPerson() { return person; } public void
	 * addPerson(PersonEntity person) { // prevent endless loop
	 * if(this.person.contains(person)) { return; } // add new person to existing
	 * this.person.add(person);
	 * 
	 * // set myself to this person person.getMovieEntity().add(this); }
	 */
	public String getMovieBasedOn() {
		return movieBasedOn;
	}
	public void setMovieBasedOn(String movieBasedOn) {
		this.movieBasedOn = movieBasedOn;
	}
	public Set<CompanyEntity> getProductionCompany() {
		return new HashSet<>(productionCompany);
	}
	public void addProductionCompany(CompanyEntity productionCompany) {
		// prevent endless loop
		if(this.productionCompany.contains(productionCompany)) {
			return;
		}
		// add new company to existing company list
		this.productionCompany.add(productionCompany);
		
		// set myself into this company
		productionCompany.addMovieEntityProd(this);
	}
	
	public void removeProductionCompany(CompanyEntity productionCompany) {
		// prevent endless loop
		if(!this.productionCompany.contains(productionCompany)) {
			return;
		}
		// remove new company from existing company list
		this.productionCompany.remove(productionCompany);
		
		// remove myself from this movie
		productionCompany.removeMovieEntityProd(this);
	}
	public Set<CompanyEntity> getDistributedBy() {
		return new HashSet<>(distributedBy);
	}
	public void addDistributedBy(CompanyEntity distributedBy) {
		// prevent endless loop
		if(this.distributedBy.contains(distributedBy)) {
			return;
		}
		// add new company to existing company list
		this.distributedBy.add(distributedBy);
		
		// set myself into this company
		distributedBy.addMovieEntityDist(this);
	}
	
	public void removeDistributedBy(CompanyEntity distributedBy) {
		// prevent endless loop
		if(!this.distributedBy.contains(distributedBy)) {
			return;
		}
		// remove new company from existing company list
		this.distributedBy.remove(distributedBy);
		
		// remove myself from this movie
		distributedBy.removeMovieEntityDist(this);
	}
	public Set<LanguageEntity> getLanguage() {
		return new HashSet<>(language);
	}
	public void addLanguage(LanguageEntity language) {
		// prevent endless loop
		if(this.language.contains(language)) {
			return;
		}
		// add new language to the list
		this.language.add(language);
		// add myself to the movie
		language.addMovieEntityForLanguages(this);
	}
	public void removeLanguage(LanguageEntity language) {
		// prevent endless loop
		if(!this.language.contains(language)) {
			return;
		}
		// remove new language from the list
		this.language.remove(language);
		// remove myself from the movie
		language.removeMovieEntityForLanguages(this);
	}
	public CountryEntity getCountry() {
		return country;
	}
	public void setCountry(CountryEntity country) {
		// prevent endless loop
		if(this.country==null ? country==null : this.country.equals(country))
		{
			return;
		}
		
		// set new keeping old as a backup
		CountryEntity oldCountry = this.country;
		this.country = country;
	
		// remove movie from old country
		if(oldCountry != null)
		{
			oldCountry.removeMovieEntity(this);
		}
		
		// set myself to new country
		if(country != null)
		{
			country.addMovieEntity(this);
		}
	}
	
	
	public Set<SongEntity> getSongs() {
		return songs;
	}
	public void addSong(SongEntity song) {
		// prevent endless loop
		if(this.songs.contains(song)) {
			return;
		}
		// add new song to the list
		this.songs.add(song);
		// add myself to the movie
		song.setMovie(this);
	}
	public void removeSong(SongEntity song) {
		// prevent endless loop
		if(!this.songs.contains(song)) {
			return;
		}
		// remove new language from the list
		this.songs.remove(song);
		// remove myself from the movie
		song.setMovie(null);
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
		return "MovieEntity [movieId=" + movieId + ", movieName=" + movieName + ", movieBasedOn="
				+ movieBasedOn + ", productionCompany=" + productionCompany + ", distributedBy=" + distributedBy
				+ ", language=" + language + ", country=" + country + ", releaseDate=" + releaseDate + ", watchDate="
				+ watchDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
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
		MovieEntity other = (MovieEntity) obj;
		if (movieId == null) {
			if (other.movieId != null)
				return false;
		} else if (!movieId.equals(other.movieId))
			return false;
		return true;
	}
	public Set<MoviePersonRoleLinkEntity> getMprLink() {
		return mprLink;
	}
	
	public void addMprLink(MoviePersonRoleLinkEntity moviePersonRoleLinkEntity) {
		// prevent endless loop
		if(this.mprLink.contains(moviePersonRoleLinkEntity)) {
			return;
		}
		// add to existing
		this.mprLink.add(moviePersonRoleLinkEntity);
		
		// add myself to link
		moviePersonRoleLinkEntity.setMovie(this);
	}
	
	public void removeMprLink(MoviePersonRoleLinkEntity moviePersonRoleLinkEntity) {
		// prevent endless loop
		if(!this.mprLink.contains(moviePersonRoleLinkEntity)) {
			return;
		}
		// remove from existing
		this.mprLink.remove(moviePersonRoleLinkEntity);
		
		// remove myself from link
		moviePersonRoleLinkEntity.setMovie(null);
	}
}
