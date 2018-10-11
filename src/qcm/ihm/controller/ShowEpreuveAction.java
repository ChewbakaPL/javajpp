package qcm.ihm.controller;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import fr.eni.tp.web.common.util.ValidationUtil;
import qcm.bo.Epreuve;
import qcm.bo.Test;

public class ShowEpreuveAction extends GenericServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 981257470919615518L;



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	System.out.println("AAAAAAAAAAA");
    	
    	errorClear(request);
    	request.setAttribute("currentUser", getCurrentUser(request));
    	
    	//======= GENERATION D'UNE EPREUVE ========
    	//==== TEMPORAIREMENT ICI (A DEPLACER) ====
    	Test fakeTest = new Test();
    	fakeTest.setLibelle("The Test");
    	
        Epreuve epreuve = new Epreuve();
        epreuve.setTest(fakeTest);
    	//==========================================
    	
    	//Read request parameters :
    	String parameter = request.getParameter("id");
    	ValidationUtil.checkNotBlank(parameter);
    	Integer id = Integer.parseInt(parameter);
    	//TODO
    	//Epreuve epreuve = epreuveManager.find(id);

    	
        request.setAttribute("epreuve", epreuve);
        
        
        request.getRequestDispatcher("showEpreuveJSP").include(request, response);
        

    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
}
