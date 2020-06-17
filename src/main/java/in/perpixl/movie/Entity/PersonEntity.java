package in.perpixl.movie.Entity;

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
import javax.persistence.OneToOne;

@Entity
public class PersonEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long personId;
	private String firstName;
	private String lastName;
	private String dob;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="movie_crew",
	joinColumns= @JoinColumn(name="personId"),
	inverseJoinColumns= @JoinColumn(name="movieId"))
	private Set<MovieEntity> movieEntity = new HashSet<MovieEntity>();
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="personEntityList")
	private Set<RoleEntity> roleEntity=new HashSet<RoleEntity>();
	
	@OneToOne(cascade=CascadeType.ALL)
	private CountryEntity country;
	
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public CountryEntity getCountry() {
		return country;
	}
	public void setCountry(CountryEntity country) {
		this.country = country;
	}
	public Set<RoleEntity> getRoleEntity() {
		return roleEntity;
	}
	
	public Set<MovieEntity> getMovieEntity() {
		return movieEntity;
	}

	public void addRole(RoleEntity re) {
		this.roleEntity.add(re);
		re.getPersonEntityList().add(this);
	}
	public void setMovieEntity(Set<MovieEntity> movieEntity) {
		this.movieEntity = movieEntity;
	}
}
