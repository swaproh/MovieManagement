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

@Entity
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long roleId;
	private String roleName;
	@ManyToMany
	@JoinTable(name="role_person",
	joinColumns=@JoinColumn(name="roleId"),
	inverseJoinColumns=@JoinColumn(name="personId"))
	private Set<PersonEntity> personEntityList = new HashSet<>();
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<PersonEntity> getPersonEntityList() {
		return personEntityList;
	}
	public void addPerson(PersonEntity pe) {
		this.personEntityList.add(pe);
		pe.getRoleEntity().add(this);
	}
	
	
	

}
