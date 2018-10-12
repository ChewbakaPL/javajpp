package qcm.ihm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.Epreuve;
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
			EpreuveDaoImpl epreuveDAO = new EpreuveDaoImpl();
			Epreuve epreuve = epreuveDAO.selectById(paramId);
			
			request.setAttribute("epreuve", epreuve);
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
