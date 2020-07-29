package in.perpixl.movie.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long roleId;
	private String roleName;
	/*
	 * @ManyToMany
	 * 
	 * @JoinTable(name="role_person", joinColumns=@JoinColumn(name="Role_Id",
	 * referencedColumnName="roleId"),
	 * inverseJoinColumns=@JoinColumn(name="Person_Id",
	 * referencedColumnName="personId")) private Set<PersonEntity> personEntityList
	 * = new HashSet<>();
	 */
	@OneToMany(mappedBy="role", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<MoviePersonRoleLinkEntity> mprLink=new HashSet<>();
	
	@OneToMany(mappedBy="role", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<SongPersonRoleLinkEntity> sprLink=new HashSet<>();
	
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

	/*
	 * public Set<PersonEntity> getPersonEntityList() { return personEntityList; }
	 * public void addPerson(PersonEntity pe) { this.personEntityList.add(pe);
	 * pe.getRoleEntity().add(this); }
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		RoleEntity other = (RoleEntity) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
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
		moviePersonRoleLinkEntity.setRole(this);
	}
	public void removeMprLink(MoviePersonRoleLinkEntity moviePersonRoleLinkEntity) {
		// prevent endless loop
		if(!this.mprLink.contains(moviePersonRoleLinkEntity)) {
			return;
		}
		// remove from existing
		this.mprLink.remove(moviePersonRoleLinkEntity);
		
		// remove myself from link
		moviePersonRoleLinkEntity.setRole(null);
	}
	
	public Set<SongPersonRoleLinkEntity> getSprLink() {
		return sprLink;
	}
	
	public void addSprLink(SongPersonRoleLinkEntity songPersonRoleLinkEntity) {
		// prevent endless loop
		if(this.sprLink.contains(songPersonRoleLinkEntity)) {
			return;
		}
		// add to existing
		this.sprLink.add(songPersonRoleLinkEntity);
		
		// add myself to link
		songPersonRoleLinkEntity.setRole(this);
	}
	
	public void removeSprLink(SongPersonRoleLinkEntity songPersonRoleLinkEntity) {
		// prevent endless loop
		if(!this.sprLink.contains(songPersonRoleLinkEntity)) {
			return;
		}
		// remove from existing
		this.sprLink.remove(songPersonRoleLinkEntity);
		
		// remove myself from link
		songPersonRoleLinkEntity.setRole(null);
	}
}
