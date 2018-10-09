package qcm.ihm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.tp.web.common.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.tp.web.common.util.ValidationUtil;
import qcm.bll.factory.ManagerFactory;
import qcm.bll.manager.UtilisateurManager;
import qcm.bo.Utilisateur;

public class LoginController extends HttpServlet {

    private UtilisateurManager utilisateurManager = ManagerFactory.utilisateurManager();
    
    /**
     * 
     */
    private static final long serialVersionUID = 981257470919615518L;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            ValidationUtil.checkNotBlank(email);
            ValidationUtil.checkNotBlank(password);
            
            utilisateurManager.checkLogin(email, password);
         
            
        } catch (IllegalArgumentException e) {
        	LOGGER.error("Validation error", e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        } catch (Exception e) {
        	LOGGER.error("Technical error", e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        Utilisateur currentUser = utilisateurManager.checkLogin(email, password);
        if (currentUser != null){
        	response.getWriter().append("utilisateur" + currentUser.toString());
        }
    }

    
}
