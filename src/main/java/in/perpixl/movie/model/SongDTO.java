package in.perpixl.movie.model;

import java.util.Set;

public class SongDTO {
	Long id;
	String title;
	MovieDTO movie;
	String lyrics;
	private Set<PersonDTO> personDTO;
	RaagDTO raag;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public MovieDTO getMovie() {
		return movie;
	}
	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	public Set<PersonDTO> getPersonDTO() {
		return personDTO;
	}
	public void setPersonDTO(Set<PersonDTO> personDTO) {
		this.personDTO = personDTO;
	}
	
	public RaagDTO getRaag() {
		return raag;
	}
	public void setRaag(RaagDTO raag) {
		this.raag = raag;
	}
	@Override
	public String toString() {
		return "SongDTO [id=" + id + ", title=" + title + ", movie=" + movie + ", lyrics=" + lyrics + ", personDTO="
				+ personDTO + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SongDTO other = (SongDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
