package qcm.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.Epreuve;
import qcm.bo.Question;
import qcm.bo.QuestionTirage;
import qcm.bo.SectionTest;
import qcm.bo.Test;
import qcm.dal.dao.impl.EpreuveDaoImpl;


public class ShowEpreuveAction extends GenericServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 981257470919615518L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String parameter = request.getParameter("id");
    	Integer paramId = Integer.parseInt(parameter);
    	
    	try {
			EpreuveDaoImpl epreuveDAO = new EpreuveDaoImpl();
			Epreuve epreuve = epreuveDAO.selectById(paramId);
			
			//==== GENERATION questionTirages =====
			ArrayList<QuestionTirage> questionTirages = new ArrayList<QuestionTirage>();
			Test test = epreuve.getTest();
			ArrayList<SectionTest> sectionTests = test.getSectionTests();

			System.out.println("--SHOWEPREUVEACTION sectionTests.size() => "+sectionTests.size());
			
			for (SectionTest st : sectionTests) {
				
				System.out.println("--SHOWEPREUVEACTION PARCOURT SECTION TEST--");
				
				Integer idTheme = st.getTheme().getIdTheme();
				Integer nbQuestionATirer = st.getNbQuestionATirer();
				
				List<Question> randomQuestions = questionDao.selectByTheme(idTheme, nbQuestionATirer);
				Integer numOrdre = 1;
				for (Question question : randomQuestions) {
					QuestionTirage qt = new QuestionTirage();
					qt.setIdEpreuve(epreuve.getIdEpreuve());
					qt.setNumOrdre(numOrdre);
					qt.setQuestion(question);
					qt.setEstMarquee(false);
					questionTirages.add(qt);
					numOrdre ++;
				}
			}
			//=====================================
			
			
			request.setAttribute("epreuve", epreuve);
			request.setAttribute("questionTirages", questionTirages);
			request.getRequestDispatcher("/showEpreuveJSP").forward(request, response);
		} catch (DaoException e) {
			e.printStackTrace();
		}
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doPost(request, response);
    }
    
}
