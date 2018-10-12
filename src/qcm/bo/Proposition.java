package qcm.bo;

public class Proposition {

	private Integer idProposition;
	private String enonce;
	private Boolean estBonne;
	private Integer idQuestion;  
	//(pas question pour éviter boucle chargement proposition<-->question)
	
	public Proposition(String enonce, Boolean estBonne) {
		super();
		this.enonce = enonce;
		this.estBonne = estBonne;
	}
	
	public Proposition() {
		super();
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

	public Integer getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}
	
	

}
