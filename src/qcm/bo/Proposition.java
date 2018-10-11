package qcm.bo;

public class Proposition {

	private Integer idProposition;
	private String enonce;
	private Boolean estBonne;
	private Question quesiton;
	
	public Proposition(String enonce, Boolean estBonne, Question quesiton) {
		super();
		this.enonce = enonce;
		this.estBonne = estBonne;
		this.quesiton = quesiton;
	}
	
	public Integer getIdProposition() {
		return idProposition;
	}
	public void setIdProposition(Integer idProposition) {
		this.idProposition = idProposition;
	}
	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	public Boolean getEstBonne() {
		return estBonne;
	}
	public void setEstBonne(Boolean estBonne) {
		this.estBonne = estBonne;
	}
	public Question getQuesiton() {
		return quesiton;
	}
	public void setQuesiton(Question quesiton) {
		this.quesiton = quesiton;
	}

}
