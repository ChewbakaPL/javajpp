package qcm.bo;

public class QuestionTirage {
	private int idEpreuve;
	private Integer idQuestion;
	private Boolean estMarquee;
	private Integer numOrdre;
	
	public QuestionTirage() {
		super();
	}
	
	public int getIdEpreuve() {
		return idEpreuve;
	}
	public void setIdEpreuve(int idEpreuve) {
		this.idEpreuve = idEpreuve;
	}
	public Integer getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}
	public Boolean getEstMarquee() {
		return estMarquee;
	}
	public void setEstMarquee(Boolean estMarquee) {
		this.estMarquee = estMarquee;
	}
	public Integer getNumOrdre() {
		return numOrdre;
	}
	public void setNumOrdre(Integer numOrdre) {
		this.numOrdre = numOrdre;
	}
	
	
	
}
