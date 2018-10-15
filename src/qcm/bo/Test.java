package qcm.bo;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {

    private static final long serialVersionUID = 5316335132571438735L;
   
    private Integer idTest;
    private String libelle;
    private String description;
    private Integer duree;
    private Double seuilHaut;
    private Double seuilBas;
    private ArrayList<SectionTest> sectionTests;
    
   	public Test() {
   		super();
   		sectionTests = new ArrayList<SectionTest>();
   	}
   	
    
	public Test(String libelle, String description, Integer duree) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.duree = duree;
	}
	
	public Integer getIdTest() {
		return idTest;
	}
	
	public void setIdTest(Integer idTest) {
		this.idTest = idTest;
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
	
	public Integer getDuree() {
		return duree;
	}
	
	public void setDuree(Integer duree) {
		this.duree = duree;
	}
	
	public Double getSeuilHaut() {
		return seuilHaut;
	}
	
	public void setSeuilHaut(Double seuilHaut) {
		this.seuilHaut = seuilHaut;
	}
	
	public Double getSeuilBas() {
		return seuilBas;
	}
	
	public void setSeuilBas(Double seuilBas) {
		this.seuilBas = seuilBas;
	}
	
	public ArrayList<SectionTest> getSectionTests() {
		return sectionTests;
	}
	
	public void setSectionTests(ArrayList<SectionTest> sectionTests) {
		this.sectionTests = sectionTests;
	}
    
	
}
