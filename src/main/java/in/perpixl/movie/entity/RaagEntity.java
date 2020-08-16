package in.perpixl.movie.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RaagEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},mappedBy="raag")
	private Set<SongEntity> songs =new HashSet<>();
	
	private String thaat;        
	private String varjyaSwar;   
	private String jaati;         
	private String vaadiSwar;    
	private String samvaadiSwar; 
	private String samay;        
	private String aaroh;        
	private String avroh;        
	private String pakad;        
	private String sandarbh; 
	private String gat;
	
	public String getGat() {
		return gat;
	}
	public void setGat(String gat) {
		this.gat = gat;
	}
	@Embedded
    @AttributeOverrides({
            @AttributeOverride(name="sthayi", 
                               column=@Column(name="SV_STHAYI")),
            @AttributeOverride(name="antara", 
                               column=@Column(name="SV_ANTARA"))
    })
	private SwarSamuhEntity swarVistar;
	
	@Embedded
	@AttributeOverrides({
        @AttributeOverride(name="sthayi", 
                           column=@Column(name="AL_STHAYI")),
        @AttributeOverride(name="antara", 
                           column=@Column(name="AL_ANTARA"))
	})
	private SwarSamuhEntity aalapi; 
	
	@Embedded
	@AttributeOverrides({
        @AttributeOverride(name="sthayi", 
                           column=@Column(name="TN_STHAYI")),
        @AttributeOverride(name="antara", 
                           column=@Column(name="TN_ANTARA"))
	})
	private SwarSamuhEntity taana;  
	
	public String getThaat() {
		return thaat;
	}
	public void setThaat(String thaat) {
		this.thaat = thaat;
	}
	public String getVarjyaSwar() {
		return varjyaSwar;
	}
	public void setVarjyaSwar(String varjyaSwar) {
		this.varjyaSwar = varjyaSwar;
	}
	
	public String getJaati() {
		return jaati;
	}
	public void setJaati(String jaati) {
		this.jaati = jaati;
	}
	public String getVaadiSwar() {
		return vaadiSwar;
	}
	public void setVaadiSwar(String vaadiSwar) {
		this.vaadiSwar = vaadiSwar;
	}
	public String getSamvaadiSwar() {
		return samvaadiSwar;
	}
	public void setSamvaadiSwar(String samvaadiSwar) {
		this.samvaadiSwar = samvaadiSwar;
	}
	public String getSamay() {
		return samay;
	}
	public void setSamay(String samay) {
		this.samay = samay;
	}
	public String getAaroh() {
		return aaroh;
	}
	public void setAaroh(String aaroh) {
		this.aaroh = aaroh;
	}
	public String getAvroh() {
		return avroh;
	}
	public void setAvroh(String avroh) {
		this.avroh = avroh;
	}
	public String getPakad() {
		return pakad;
	}
	public void setPakad(String pakad) {
		this.pakad = pakad;
	}
	public String getSandarbh() {
		return sandarbh;
	}
	public void setSandarbh(String sandarbh) {
		this.sandarbh = sandarbh;
	}
	public SwarSamuhEntity getSwarVistar() {
		return swarVistar;
	}
	public void setSwarVistar(SwarSamuhEntity swarVistar) {
		this.swarVistar = swarVistar;
	}
	public SwarSamuhEntity getAalapi() {
		return aalapi;
	}
	public void setAalapi(SwarSamuhEntity aalapi) {
		this.aalapi = aalapi;
	}
	public SwarSamuhEntity getTaana() {
		return taana;
	}
	public void setTaana(SwarSamuhEntity taana) {
		this.taana = taana;
	}
	public Set<SongEntity> getSongs() {
		return new HashSet<>(songs);
	}
	public void addSong(SongEntity song) {
		// prevent endless loop
		if(this.songs.contains(song)) {
			return;
		}
		
		// add song to existing list
		this.songs.add(song);
		
		// add myself to this song
		song.addRaag(this);
	}
	public void removeSong(SongEntity song) {
		// prevent endless loop
		if(!this.songs.contains(song)) {
			return;
		}
		// remove raag from list
		this.songs.remove(song);
		
		// remove myself from this song
		song.addRaag(null);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		RaagEntity other = (RaagEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
