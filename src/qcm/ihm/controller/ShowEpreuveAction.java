package qcm.ihm.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.Epreuve;
import qcm.bo.Question;
import qcm.bo.QuestionTirage;
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
    		ArrayList<QuestionTirage> questionTirages = new ArrayList<QuestionTirage>();
    		List<Question> randomQuestions =  new ArrayList<Question>();
    		
			Epreuve epreuve = epreuveDao.selectById(paramId);
			epreuve.setDateDebutTest(this.getCurrentDate());

			questionTirages = questionTirageDao.selectByIdEpreuve(epreuve.getIdEpreuve());
			
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
