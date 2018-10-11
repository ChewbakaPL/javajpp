package qcm.ihm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.tp.web.common.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.tp.web.common.util.ValidationUtil;
import qcm.bll.factory.ManagerFactory;
import qcm.bll.manager.TestManager;
import qcm.bll.manager.UtilisateurManager;
import qcm.bo.Test;
import qcm.bo.Utilisateur;

public class DisconnectAction extends GenericServlet {
    
    /**
     * 
     */
    private static final long serialVersionUID = 981257470919615518L;

    private static final Logger LOGGER = LoggerFactory.getLogger(DisconnectAction.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
	    	errorClear(request);
	    	setCurrentUser(request, null);
        	request.getRequestDispatcher("/login").forward(request, response);
        } catch (IllegalArgumentException e) {
        	LOGGER.error("Validation error", e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
