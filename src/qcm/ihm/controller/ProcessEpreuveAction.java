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
        
    	/* JSON FAIL
    	String jsonString = request.getParameter("json");
    	System.out.println("JAVA INPUT questions[]:");
    	System.out.println(jsonString);
    	
    	JSONParser parser = new JSONParser();
    	JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(jsonString);
			
			System.out.println("AVANT ITERATION");
			for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
			    String key = (String) iterator.next();
			    
			    System.out.println(key);
			    System.out.println(jsonObject.get(key));
			}
			System.out.println("APRES ITERATION");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	System.out.println(jsonObject);
    	*/
    	
    	Integer nbQuestion = Integer.parseInt(request.getParameter("nbQuestion"));
    	for (int i=1; i<=nbQuestion; i++) {
    		String key = "question"+i;
    		
    		String valeurQuestion = request.getParameter(key);
            System.out.println(key+": "+valeurQuestion);
         }
   
    	
        
    }

    
}
