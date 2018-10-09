package src.main.java.fr.eni.qcm.dal.factory;

import src.main.java.fr.eni.qcm.dal.dao.UtilisateurDAO;
import src.main.java.fr.eni.qcm.dal.dao.impl.UtilisateurDaoImpl;

public class DAOFactory {

    public static UtilisateurDAO noteDAO() {
        return UtilisateurDaoImpl.getInstance();
    }
}
