package src.main.java.fr.eni.qcm.bo;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5316335132571438735L;
   
    private Integer idTest;
    private String libelle;
    private String description;
    private int duree;
    private double seuilHaut;
    private double seuilBas;
    private ArrayList<SectionTest> sectionTests;
    
    
	
   
    
}
