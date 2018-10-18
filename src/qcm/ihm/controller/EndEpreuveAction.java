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
		
		Integer minutesRestantes = epreuve.getTest().getDuree() - epreuve.getTempsEcoule();
    	
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
			
			Boolean goodAnswer = true;
			for(Proposition proposition : propositions){
				Integer idProposition = proposition.getIdProposition();
				Boolean userCheckedIt = false;
				for(ReponseUtilisateur reponse : reponses){
					if(reponse.getIdProposition() == idProposition){
						userCheckedIt = true;
					}
				}
		
				if(userCheckedIt != proposition.getEstBonne()){
					goodAnswer = false;
				}
			}
			
			resultats.put(question, goodAnswer);
		}
		
		Double total = 0.0;
		Double totalEpreuve = 0.0;
		Iterator it = resultats.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        if(((Boolean)pair.getValue())){
	        	total += ((Question)pair.getKey()).getPoints();
	        }
	        totalEpreuve += ((Question)pair.getKey()).getPoints();
	    }
	    
    	request.setAttribute("epreuve", epreuve);
    	request.setAttribute("resultats", resultats);
    	request.setAttribute("total", total);
    	request.setAttribute("totalEpreuve", totalEpreuve);
    	request.setAttribute("minutesRestantes", minutesRestantes);
		request.getRequestDispatcher("/endEpreuveJSP").forward(request, response);
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doPost(request, response);
    }
    
}
