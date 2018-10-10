package qcm.ihm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qcm.bo.Utilisateur;

//import qcm.common.AppConstants;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        //Récupération de l'objet utilisateur connecté depuis la session :
        HttpSession session = httpRequest.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( "utilisateur" );
        
        if(utilisateur == null) {
            httpResponse.sendRedirect(String.format("%s/login", httpRequest.getContextPath()));
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
       
    }

}
