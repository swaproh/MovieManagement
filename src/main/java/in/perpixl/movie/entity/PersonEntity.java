package in.perpixl.movie.entity;

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
public class PersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personId;
	private String firstName;
	private String lastName;
	private String dob;
	/*
	 * @ManyToMany(cascade=CascadeType.ALL, mappedBy="person") private
	 * Set<MovieEntity> movieEntity = new HashSet<MovieEntity>();
	 */
	/*
	 * @ManyToMany(mappedBy="personEntityList") private Set<RoleEntity>
	 * roleEntity=new HashSet<RoleEntity>();
	 */

	@OneToMany(mappedBy = "person", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<MoviePersonRoleLinkEntity> mprLink = new HashSet<>();

	@ManyToOne(cascade = CascadeType.ALL)
	private CountryEntity country;

	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "personDTO")
	private Set<SongEntity> songs = new HashSet<>();

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

	/*
	 * public void setRoleEntity(Set<RoleEntity> roleEntity) { this.roleEntity =
	 * roleEntity; }
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity newCountry) {
		// prevent endless loop
		if (this.country == null ? newCountry == null : this.country.equals(newCountry)) {
			return;
		}
		// set new country keeping old as a backup
		CountryEntity oldCountry = this.country;
		this.country = newCountry;

		// remove person from old country
		if (oldCountry != null) {
			oldCountry.removePersonEntity(null);
		}

		// set myself into new country
		if (newCountry != null) {
			newCountry.addPersonEntity(this);
		}
	}

	/*
	 * public Set<RoleEntity> getRoleEntity() { return roleEntity; }
	 */

	/*
	 * public Set<MovieEntity> getMovieEntity() { return movieEntity; }
	 * 
	 * 
	 * public void addRole(RoleEntity re) { this.roleEntity.add(re);
	 * re.getPersonEntityList().add(this); }
	 * 
	 * public void setMovieEntity(Set<MovieEntity> movieEntity) { this.movieEntity =
	 * movieEntity; }
	 */
	public Set<SongEntity> getSongs() {
		return songs;
	}

	public void addSong(SongEntity song) {
		// prevent endless loop
		if (this.songs.contains(song)) {
			return;
		}

		// add song to the list
		this.songs.add(song);

		// add myself to the song
		song.addPersonDTO(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
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
		PersonEntity other = (PersonEntity) obj;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		return true;
	}

	public Set<MoviePersonRoleLinkEntity> getMprLink() {
		return mprLink;
	}

	public void addMprLink(MoviePersonRoleLinkEntity moviePersonRoleLinkEntity) {
		// prevent endless loop
		if (this.mprLink.contains(moviePersonRoleLinkEntity)) {
			return;
		}
		// add to existing
		this.mprLink.add(moviePersonRoleLinkEntity);

		// add myself to link
		moviePersonRoleLinkEntity.setPerson(this);
	}

	public void removeMprLink(MoviePersonRoleLinkEntity moviePersonRoleLinkEntity) {
		// prevent endless loop
		if (!this.mprLink.contains(moviePersonRoleLinkEntity)) {
			return;
		}
		// remove from existing
		this.mprLink.remove(moviePersonRoleLinkEntity);

		// remove myself from link
		moviePersonRoleLinkEntity.setPerson(null);
	}
}
