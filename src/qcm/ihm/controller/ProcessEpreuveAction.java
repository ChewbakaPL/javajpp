package qcm.ihm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;
import qcm.bo.Epreuve;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        
    	Integer idEpreuve = Integer.parseInt(request.getParameter("idEpreuve"));
    	Integer nbQuestion = Integer.parseInt(request.getParameter("nbQuestion"));
    	
    	System.out.println("idEpreuve: "+idEpreuve);
    	
    	for (int i=1; i<=nbQuestion; i++) {
    		String key = "question"+i;
    		
    		String valeurQuestion = request.getParameter(key);
            System.out.println(key+": "+valeurQuestion);
         }
   
    	
        
    }

    
}
