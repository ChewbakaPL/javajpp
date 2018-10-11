package qcm.ihm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qcm.bll.factory.ManagerFactory;
import qcm.bll.manager.TestManager;
import qcm.bll.manager.UtilisateurManager;
import qcm.bo.Utilisateur;

/**
 * Servlet implementation class GenericServlet
 */
@WebServlet("/GenericServlet")
public class GenericServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAction.class);
    //Managers:
    protected UtilisateurManager utilisateurManager = ManagerFactory.utilisateurManager();
    protected TestManager testManager = ManagerFactory.testManager();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenericServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("GenericServlet, server at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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


}
