package qcm.dal.factory;

import qcm.dal.dao.TestDAO;
import qcm.dal.dao.UtilisateurDAO;
import qcm.dal.dao.impl.TestDaoImpl;
import qcm.dal.dao.impl.UtilisateurDaoImpl;

public class DAOFactory {

	public static UtilisateurDAO utilisateurDAO() {
		return UtilisateurDaoImpl.getInstance();
	}
    
    public static TestDAO testDAO() {
        return TestDaoImpl.getInstance();
    }


}
