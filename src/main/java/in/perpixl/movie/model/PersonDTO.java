package in.perpixl.movie.model;

import java.util.HashSet;
import java.util.Set;

public class PersonDTO {
	
	private Long personId;
	private String firstName;
	private String lastName;
	private String dob;
	private CountryDTO country;
	private Set<RoleDTO> roles;
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
	
	
	public CountryDTO getCountry() {
		return country;
	}
	public void setCountry(CountryDTO country) {
		this.country = country;
	}
	public Set<RoleDTO> getRoles() {
		return roles;
	}
	public void addRole(RoleDTO roles) {
		this.roles.add(roles);
	}
	
	

}
