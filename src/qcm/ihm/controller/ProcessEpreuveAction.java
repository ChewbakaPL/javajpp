package qcm.ihm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.Question;
import qcm.bo.QuestionTirage;
import qcm.bo.ReponseUtilisateur;


public class ProcessEpreuveAction extends GenericServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 981257470919615518L;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String idQuestionTirageStr = request.getParameter("idQuestionTirage");
    	Integer idQuestionTirage = Integer.parseInt(idQuestionTirageStr);
    	
    	QuestionTirage questionTirage = null;
		try {
			questionTirage = questionTirageDao.selectById(idQuestionTirage);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}
    	
    	String[] reponses = request.getParameterValues("reponseUtilisateur");
    	try {
			reponseUtilisateurDao.delete(idQuestionTirage);
		} catch (DaoException e2) {
			e2.printStackTrace();
		}
    	
    	for(String reponse: reponses){
    		Integer idProposition = Integer.parseInt(reponse);
        	
        	//enregistrement de la reponse de l'utilisateur (propositions coches pour cette question) :
        	ReponseUtilisateur reponseUtilisateur = new ReponseUtilisateur();
        	reponseUtilisateur.setIdQuestionTirage(idQuestionTirage);
        	reponseUtilisateur.setIdProposition(idProposition);
        	//recuperation de la question (non indispensable) :
        	Question question = questionTirage.getQuestion();
        	reponseUtilisateur.setIdQuestion(question.getIdQuestion());
        	
        	try {
				reponseUtilisateurDao.insert(reponseUtilisateur);
			} catch (DaoException e) {
				e.printStackTrace();
			}
    	}
    }
}
