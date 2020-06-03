package in.perpixl.movie.Entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class RoleEntity {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long roleId;
	private String roleName;
	@ManyToMany(mappedBy="roleEntity", cascade=CascadeType.ALL)
	private List<PersonEntity> personEntityList;
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
	public List<PersonEntity> getPersonEntityList() {
		return personEntityList;
	}
	public void setPersonEntityList(List<PersonEntity> personEntityList) {
		this.personEntityList = personEntityList;
	}
	
	
	

}
