package qcm.ihm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class EndEpreuveAction extends GenericServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 981257470919615518L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String parameter = request.getParameter("id");
    	Integer paramId = Integer.parseInt(parameter);
    	
    	
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doPost(request, response);
    }
    
}
