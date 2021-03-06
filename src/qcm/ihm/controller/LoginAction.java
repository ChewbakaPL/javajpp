package qcm.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;
import qcm.bo.Epreuve;
import qcm.bo.Test;
import qcm.bo.Utilisateur;
import qcm.dal.dao.EpreuveDAO;
import qcm.dal.dao.impl.EpreuveDaoImpl;

public class LoginAction extends GenericServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 981257470919615518L;



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
        ValidationUtil.checkNotBlank(email);
        ValidationUtil.checkNotBlank(password);
        
        HttpSession session = request.getSession();
        
        Utilisateur currentUser = null;
        String errorMsg = "";
        try{
        	currentUser = utilisateurDao.checkLogin(email, password);
        }catch(Exception e){
        	errorMsg = e.getMessage();
        }
        
        
        if (currentUser == null){
        	if("".equals(errorMsg)){
        		errorMsg = "Ton login/mot de passe est incorrect!";
        	}
        	session.setAttribute("error", errorMsg);
        	request.getRequestDispatcher("login").forward(request, response);
        } else {
        	setCurrentUser(request, currentUser);
        	session.removeAttribute("error");
        	String typeUser = currentUser.getType();
            if(typeUser != null && "admin".equals(typeUser)){
            	request.getRequestDispatcher("/admin/homepage").forward(request, response);
            } else {
            	List<Epreuve> listEpreuve = new ArrayList<Epreuve>();
				try {
					System.out.println(currentUser.getIdUtilisateur());
					listEpreuve = epreuveDao.selectByUser(currentUser.getIdUtilisateur());
					
				} catch (DaoException e) {
					e.printStackTrace();
				}
            	request.setAttribute("epreuves", listEpreuve);
            	request.getRequestDispatcher("/homepage").forward(request, response);
            }
        }
        
    }

    
}
