package in.perpixl.movie.model;

import java.util.List;
import java.util.UUID;

public class PersonDTO {
	
	private Long personId;
	private String firstName;
	private String lastName;
	private String dob;
	private String country;
	private List<RoleDTO> roleDTO;
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
	public List<RoleDTO> getRoleDTO() {
		return roleDTO;
	}
	public void setRoleDTO(List<RoleDTO> roleDTO) {
		this.roleDTO = roleDTO;
	}
	@Override
	public String toString() {
		return "PersonDTO [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob="
				+ dob + ", country=" + country + ", roleDTO=" + roleDTO + "]";
	}
	
	
	

}
