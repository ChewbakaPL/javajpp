package java.fr.eni.qcm.bo;

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
	private ArrayList<QuestionTirage> questionTirages;
}
