package src.main.java.fr.eni.qcm.bll.manager.impl;

import java.util.Date;
import java.util.List;

import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.exception.FunctionalException;
import fr.eni.tp.web.common.util.ValidationUtil;
import src.main.java.fr.eni.qcm.bll.manager.UtilisateurManager;
import src.main.java.fr.eni.qcm.bo.Utilisateur;
import src.main.java.fr.eni.qcm.dal.dao.UtilisateurDAO;
import src.main.java.fr.eni.qcm.dal.factory.DAOFactory;

public class UtilisateurManagerImpl implements UtilisateurManager {

    private UtilisateurDAO dao = DAOFactory.noteDAO();
    
    private static UtilisateurManagerImpl instance;
    
    private UtilisateurManagerImpl() {
        
    }
    
    public static UtilisateurManagerImpl getInstance() {
        if(instance == null) {
            instance = new UtilisateurManagerImpl();
        }
        return instance;
    }
    

    
    @Override
    public List<Utilisateur> findAll() throws ManagerException {
        
        List<Utilisateur> list = null;
        
        try {
            list = dao.selectAll();
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        }
                
        return list;
    }

    @Override
    public Utilisateur find(Integer id) throws ManagerException, ElementNotFoundException {
        
        Utilisateur utilisateur = null;
        
        try {
            
            ValidationUtil.checkNotNull(id);
            
            utilisateur = dao.selectById(id);
            
            if(utilisateur == null) {
                throw new ElementNotFoundException("The user does not exist", null);
            }
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("The id cannot be null", e);
        }
                
        return utilisateur;
    }

    @Override
    public void delete(Integer id) throws ManagerException, FunctionalException {
        
        try {
            
            ValidationUtil.checkNotNull(id);
            
            if(dao.selectById(id) == null) {
        		throw new ElementNotFoundException("This note does not exist", null);
        	}
            
            dao.delete(id);
            
        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new ManagerException("The id cannot be null", e);
        }
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur) throws ManagerException, FunctionalException {
        
        try {

            ValidationUtil.checkNotNull(utilisateur);
            ValidationUtil.checkNotBlank(utilisateur.getEmail());
            ValidationUtil.checkNotBlank(utilisateur.getPassword());
            
            if(utilisateur.getIdUtilisateur() != null) {
            	
            	if(dao.selectById(utilisateur.getIdUtilisateur()) == null) {
            		throw new ElementNotFoundException("This note does not exist", null);
            	}
            	dao.update(utilisateur);                
            } else {
                
            	//if(dao.checkExistenceWithEmail(utilisateur.getEmail())) {
                //    throw new FunctionalException("This user already exists", null);
                //}
            	
                dao.insert(utilisateur);
            }

        } catch (DaoException e) {
            throw new ManagerException(e.getMessage(), e);

        } catch (IllegalArgumentException e) {
            throw new FunctionalException("The user is not valid", e);
        }
        
        return utilisateur;
    }

}
