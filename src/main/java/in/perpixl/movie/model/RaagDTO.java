package in.perpixl.movie.model;

import javax.validation.Valid;

import in.perpixl.movie.annotations.SwarConstraint;

public class RaagDTO {
	
	private Long id;
	private String name;
	private String thaat;
	
	@SwarConstraint
	private String varjyaSwar;
	private String jaati;
	
	@SwarConstraint
	private String vaadiSwar;
	
	@SwarConstraint
	private String samvaadiSwar;
	
	private String samay;
	
	@SwarConstraint
	private String aaroh;
	
	@SwarConstraint
	private String avroh;
	
	@SwarConstraint
	private String pakad;
	
	private String sandarbh;
	
	@SwarConstraint
	private String gat;
	
	@Valid
	private SwarSamuhDTO swarVistar;
	
	@Valid
	private SwarSamuhDTO aalapi;
	
	@Valid
	private SwarSamuhDTO taana;
	
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
	public SwarSamuhDTO getSwarVistar() {
		return swarVistar;
	}
	public void setSwarVistar(SwarSamuhDTO swarVistar) {
		this.swarVistar = swarVistar;
	}
	public SwarSamuhDTO getAalapi() {
		return aalapi;
	}
	public void setAalapi(SwarSamuhDTO aalapi) {
		this.aalapi = aalapi;
	}
	public SwarSamuhDTO getTaana() {
		return taana;
	}
	public void setTaana(SwarSamuhDTO taana) {
		this.taana = taana;
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
		RaagDTO other = (RaagDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RaagDTO [id=" + id + ", name=" + name + ", thaat=" + thaat + ", varjyaSwar=" + varjyaSwar + ", jaati="
				+ jaati + ", vaadiSwar=" + vaadiSwar + ", samvaadiSwar=" + samvaadiSwar + ", samay=" + samay
				+ ", aaroh=" + aaroh + ", avroh=" + avroh + ", pakad=" + pakad + ", sandarbh=" + sandarbh
				+ ", swarVistar=" + swarVistar + ", aalapi=" + aalapi + ", taana=" + taana + "]";
	}
	public String getGat() {
		return gat;
	}
	public void setGat(String gat) {
		this.gat = gat;
	}
	
}
