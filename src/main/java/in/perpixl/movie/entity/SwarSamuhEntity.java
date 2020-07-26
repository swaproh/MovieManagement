package in.perpixl.movie.entity;

import javax.persistence.Embeddable;

@Embeddable
public class SwarSamuhEntity {
	private String sthayi;
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
