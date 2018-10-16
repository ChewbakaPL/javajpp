package qcm.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.tp.web.common.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProcessEpreuveAction extends GenericServlet {
    
    /**
     * 
     */
    private static final long serialVersionUID = 981257470919615518L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessEpreuveAction.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
