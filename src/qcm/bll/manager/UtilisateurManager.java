package qcm.bll.manager;

import java.util.List;

import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;
import qcm.bo.Utilisateur;

public interface UtilisateurManager {

    List<Utilisateur> findAll() throws ManagerException;
    
    Utilisateur find(Integer id) throws ManagerException, ElementNotFoundException;
    
    void delete(Integer id) throws ManagerException, FunctionalException;
    
    Utilisateur save(Utilisateur note) throws ManagerException, FunctionalException;
    
    Utilisateur checkLogin(String email, String password) throws ManagerException;

	Utilisateur findByEmail(String email) throws ManagerException, ElementNotFoundException;
}
