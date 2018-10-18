package qcm.bo;

import java.util.ArrayList;

public class Question {

	private Integer idQuestion;
	private String enonce;
	private String media;
	private Double points;
	private Theme theme;
	private ArrayList<Proposition> propositions;
	
	public Question() {
		super();
	}
	public Integer getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}
	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public Double getPoints() {
		return points;
	}
	public void setPoints(Double points) {
		this.points = points;
	}
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public ArrayList<Proposition> getPropositions() {
		return propositions;
	}
	public void setPropositions(ArrayList<Proposition> propositions) {
		this.propositions = propositions;
	}
	
}
