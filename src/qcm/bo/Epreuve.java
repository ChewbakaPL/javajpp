package qcm.bo;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Epreuve {

	private Integer idEpreuve;
	private Timestamp dateDebutValidite;
	private Timestamp dateFinValidite;
	private Integer tempsEcoule;
	private Integer etat;
	private Double noteObtenu;
	private Integer niveauObtenu;
	private Test test;
	private ArrayList<QuestionTirage> questionTirages;
	
	public Epreuve() {
		super();
	}
	
	public Integer getIdEpreuve() {
		return idEpreuve;
	}
	public void setIdEpreuve(Integer idEpreuve) {
		this.idEpreuve = idEpreuve;
	}
	public Timestamp getDateDebutValidite() {
		return dateDebutValidite;
	}
	public void setDateDebutValidite(Timestamp dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}
	public Timestamp getDateFinValidite() {
		return dateFinValidite;
	}
	public void setDateFinValidite(Timestamp dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}
	public Integer getTempsEcoule() {
		return tempsEcoule;
	}
	public void setTempsEcoule(Integer tempsEcoule) {
		this.tempsEcoule = tempsEcoule;
	}
	public Integer getEtat() {
		return etat;
	}
	public void setEtat(Integer etat) {
		this.etat = etat;
	}
	public Double getNoteObtenu() {
		return noteObtenu;
	}
	public void setNoteObtenu(Double noteObtenu) {
		this.noteObtenu = noteObtenu;
	}
	public Integer getNiveauObtenu() {
		return niveauObtenu;
	}
	public void setNiveauObtenu(Integer niveauObtenu) {
		this.niveauObtenu = niveauObtenu;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public ArrayList<QuestionTirage> getQuestionTirages() {
		return questionTirages;
	}
	public void setQuestionTirages(ArrayList<QuestionTirage> questionTirages) {
		this.questionTirages = questionTirages;
	}

	@Override
	public String toString() {
		return "Epreuve [idEpreuve=" + idEpreuve + ", dateDebutValidite=" + dateDebutValidite + ", dateFinValidite="
				+ dateFinValidite + ", tempsEcoule=" + tempsEcoule + ", etat=" + etat + ", noteObtenu=" + noteObtenu
				+ ", niveauObtenu=" + niveauObtenu + ", test=" + test + ", questionTirages=" + questionTirages + "]";
	}
	
	
}
