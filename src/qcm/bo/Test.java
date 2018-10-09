package qcm.bo;

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
    private Integer duree;
    private Double seuilHaut;
    private Double seuilBas;
    private ArrayList<SectionTest> sectionTests;
    
    
	
   
    
}
