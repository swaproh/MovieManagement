package com.movie.model;

public class Movie {
	
	private int movieId;
	private String movieName;
	private String actor;
	private String actress;
	private String director;
	private String producer;
	private String writter;
	private String scenametographer;
	private String screenplay;
	private String musicDirector;
	private String movieOrigin;
	private String movieBasedOn;
	private String releaseDate;
	private String watchDate;
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getActress() {
		return actress;
	}
	public void setActress(String actress) {
		this.actress = actress;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getWritter() {
		return writter;
	}
	public void setWritter(String writter) {
		this.writter = writter;
	}
	public String getScenametographer() {
		return scenametographer;
	}
	public void setScenametographer(String scenametographer) {
		this.scenametographer = scenametographer;
	}
	public String getScreenplay() {
		return screenplay;
	}
	public void setScreenplay(String screenplay) {
		this.screenplay = screenplay;
	}
	public String getMusicDirector() {
		return musicDirector;
	}
	public void setMusicDirector(String musicDirector) {
		this.musicDirector = musicDirector;
	}
	public String getMovieOrigin() {
		return movieOrigin;
	}
	public void setMovieOrigin(String movieOrigin) {
		this.movieOrigin = movieOrigin;
	}
	public String getMovieBasedOn() {
		return movieBasedOn;
	}
	public void setMovieBasedOn(String movieBasedOn) {
		this.movieBasedOn = movieBasedOn;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getWatchDate() {
		return watchDate;
	}
	public void setWatchDate(String watchDate) {
		this.watchDate = watchDate;
	}
	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", actor=" + actor + ", actress=" + actress
				+ ", director=" + director + ", producer=" + producer + ", writter=" + writter + ", scenametographer="
				+ scenametographer + ", screenplay=" + screenplay + ", musicDirector=" + musicDirector
				+ ", movieOrigin=" + movieOrigin + ", movieBasedOn=" + movieBasedOn + ", releaseDate=" + releaseDate
				+ ", watchDate=" + watchDate + "]";
	}
	
	
	
	
	
	

}
