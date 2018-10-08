package src.main.java.fr.eni.qcm.bo;

import java.io.Serializable;

public class Test implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5316335132571438735L;
   
    private Integer id;
    private String libelle;
    private String description;
    private int duree;
    private double seuil_haut;
    private double seuil_bas;
    
    public Test() {
        super();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public double getSeuil_haut() {
		return seuil_haut;
	}

	public void setSeuil_haut(double seuil_haut) {
		this.seuil_haut = seuil_haut;
	}

	public double getSeuil_bas() {
		return seuil_bas;
	}

	public void setSeuil_bas(double seuil_bas) {
		this.seuil_bas = seuil_bas;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", libelle=" + libelle + ", description=" + description + ", duree=" + duree
				+ ", seuil_haut=" + seuil_haut + ", seuil_bas=" + seuil_bas + "]";
	}
    
    
	
   
    
}
