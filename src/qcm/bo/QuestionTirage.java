package qcm.bo;

public class QuestionTirage {
	private int idEpreuve;
	private Question question;
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
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
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
