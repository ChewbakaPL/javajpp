package qcm.ihm.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.Utilisateur;
import qcm.dal.dao.EpreuveDAO;
import qcm.dal.dao.impl.EpreuveDaoImpl;
import qcm.dal.dao.impl.QuestionDaoImpl;
import qcm.dal.dao.impl.QuestionTirageDaoImpl;
import qcm.dal.dao.impl.ReponseUtilisateurDaoImpl;
import qcm.dal.dao.impl.TestDaoImpl;
import qcm.dal.dao.impl.UtilisateurDaoImpl;

/**
 * Servlet implementation class GenericServlet
 */
@WebServlet("/GenericServlet")
public class GenericServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //Managers: 
    protected UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl();
	protected TestDaoImpl testDao = new TestDaoImpl();
	protected EpreuveDaoImpl epreuveDao = new EpreuveDaoImpl();
	protected QuestionDaoImpl questionDao = new QuestionDaoImpl();
	protected QuestionTirageDaoImpl questionTirageDao = new QuestionTirageDaoImpl();
	protected ReponseUtilisateurDaoImpl reponseUtilisateurDao = new ReponseUtilisateurDaoImpl();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenericServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("GenericServlet, server at: ").append(request.getContextPath());
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected Utilisateur getCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
		return currentUser;
	}
	
	protected void setCurrentUser(HttpServletRequest request, Utilisateur utilisateur){
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", utilisateur);
	}
	
	protected void errorClear(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("error");
	}

	protected Timestamp getCurrentTimestamp(){
	      long t = Calendar.getInstance().getTime().getTime();
	      return new java.sql.Timestamp(t);
	}
	
	protected Date getCurrentDate(){
	      long t = Calendar.getInstance().getTime().getTime();
	      return new java.sql.Date(t);
	}

}
