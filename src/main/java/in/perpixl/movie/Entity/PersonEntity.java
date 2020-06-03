package in.perpixl.movie.Entity;

import java.util.List;
import java.util.UUID;

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
public class PersonEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long personId;
	private String firstName;
	private String lastName;
	private String dob;
	private String country;
	@ManyToOne//(cascade=CascadeType.ALL)
	@JoinColumn(name="movieId", nullable=false)
	private MovieEntity movieEntity;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="role_like",
	joinColumns=@JoinColumn(name="personId"),
	inverseJoinColumns=@JoinColumn(name="roleId"))
	private List<RoleEntity> roleEntity;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<RoleEntity> getRoleEntity() {
		return roleEntity;
	}
	public void setRoleEntity(List<RoleEntity> roleEntity) {
		this.roleEntity = roleEntity;
	}
	public MovieEntity getMovieEntity() {
		return movieEntity;
	}
	public void setMovieEntity(MovieEntity movieEntity) {
		this.movieEntity = movieEntity;
	}
	
	
	
	

}
