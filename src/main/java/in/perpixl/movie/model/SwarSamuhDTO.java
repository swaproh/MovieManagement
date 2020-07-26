package in.perpixl.movie.model;

import in.perpixl.movie.annotations.SwarConstraint;

public class SwarSamuhDTO {
	@SwarConstraint
	private String sthayi;
	
	@SwarConstraint
	private String antara;
	public String getSthayi() {
		return sthayi;
	}
	public void setSthayi(String sthayi) {
		this.sthayi = sthayi;
	}
	public String getAntara() {
		return antara;
	}
	public void setAntara(String antara) {
		this.antara = antara;
	}
	
}
