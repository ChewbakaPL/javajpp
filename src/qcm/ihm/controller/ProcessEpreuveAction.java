package qcm.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProcessEpreuveAction extends GenericServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 981257470919615518L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessEpreuveAction.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String idQuestion = request.getParameter("idQuestion");
    	String idQuestionTirage = request.getParameter("idQuestionTirage");
    	
        String idPropositions = request.getParameter("reponseUtilisateur");
    	System.out.println("idquestion " + idQuestion);
    	System.out.println("idquestiontirage" + idQuestionTirage);
    	System.out.println("proposition" + idPropositions);
    }
}
