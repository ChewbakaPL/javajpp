package qcm.dal.dao;

import fr.eni.tp.web.common.dal.exception.DaoException;
import qcm.bo.Utilisateur;

public interface UtilisateurDAO extends GenericDAO<Utilisateur, Integer> {
	
	Utilisateur checkLogin(String email, String password) throws DaoException;

	Utilisateur selectByEmail(String email) throws DaoException;
}
