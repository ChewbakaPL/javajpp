package src.main.java.fr.eni.qcm.dal.dao;

import src.main.java.fr.eni.qcm.bo.Utilisateur;
import fr.eni.tp.web.common.dal.dao.GenericDAO;
import fr.eni.tp.web.common.dal.exception.DaoException;

public interface UtilisateurDAO extends GenericDAO<Utilisateur, Integer> {
	Utilisateur checkLogin(String email, String password) throws DaoException;
}
