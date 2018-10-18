package qcm.bo;

import java.util.ArrayList;
import java.util.Date;

public class Epreuve {

	private Integer idEpreuve;
	private Integer dateDebutValidite;
	private Integer dateFinValidite;
	private Date dateDebutTest;
	private Integer tempsEcoule;
	private Integer etat;
	private Double noteObtenu;
	private Integer niveauObtenu;
	private Test test;
	private Utilisateur utilisateur;
	private ArrayList<QuestionTirage> questionTirages;
	
	public Epreuve() {
		super();
	}
	
	public Date getDateDebutTest() {
		return dateDebutTest;
	}
	public void setDateDebutTest(Date dateDebutTest) {
		this.dateDebutTest = dateDebutTest;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Integer getIdEpreuve() {
		return idEpreuve;
	}
	public void setIdEpreuve(Integer idEpreuve) {
		this.idEpreuve = idEpreuve;
	}
	public Integer getDateDebutValidite() {
		return dateDebutValidite;
	}
	public void setDateDebutValidite(Integer dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}
	public Integer getDateFinValidite() {
		return dateFinValidite;
	}
	public void setDateFinValidite(Integer dateFinValidite) {
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
