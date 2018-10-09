package src.main.java.fr.eni.qcm.bo;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Epreuve {

	private int idEpreuve;
	private Timestamp dateDebutValidite;
	private Timestamp dateFinValidite;
	private int tempsEcoule;
	private int etat;
	private double noteObtenu;
	private int niveauObtenu;
	private ArrayList<QuestionTirage> questionTirages;
}
