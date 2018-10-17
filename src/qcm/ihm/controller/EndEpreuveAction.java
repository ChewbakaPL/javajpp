package qcm.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.Epreuve;
import qcm.bo.Proposition;
import qcm.bo.Question;
import qcm.bo.QuestionTirage;
import qcm.bo.ReponseUtilisateur;



public class EndEpreuveAction extends GenericServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 981257470919615518L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String parameter = request.getParameter("id");
    	Integer paramId = Integer.parseInt(parameter);
    	
    	Epreuve epreuve = null;
		try {
			epreuve = epreuveDao.selectById(paramId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
    	
		Map<Question,Boolean> resultats = new HashMap<Question,Boolean>();
		
		ArrayList<QuestionTirage> questionTirages = epreuve.getQuestionTirages();
		ArrayList<Proposition> propositions = null;
		List<ReponseUtilisateur> reponses = null;
		for(QuestionTirage qt : questionTirages){
			Question question = qt.getQuestion();
			propositions = question.getPropositions();
			try {
				reponses = reponseUtilisateurDao.selectByQuestionTirage(qt.getIdQuestionTirage());
			} catch (DaoException e) {
				e.printStackTrace();
			}
			
			for(Proposition proposition : propositions){
				if(proposition.getEstBonne()){
					Integer idBonneReponse = proposition.getIdProposition();
					
					for(ReponseUtilisateur reponse : reponses){
						if(idBonneReponse == reponse.getIdProposition()){
							resultats.put(question, true);
						}else{
							resultats.put(question, false);
						}
					}
				}
			}
		}
		
		Double totalPoints = 0.0;
		Iterator it = resultats.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        if(((Boolean)pair.getValue())){
	        	totalPoints += ((Question)pair.getKey()).getPoints();
	        }
	        it.remove();
	    }
		
	    System.out.println("Total: "+ totalPoints);
	    
		
    	request.setAttribute("epreuve", epreuve);
    	request.setAttribute("resultats", resultats);
    	
    	
		request.getRequestDispatcher("/endEpreuveJSP").forward(request, response);
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doPost(request, response);
    }
    
}
