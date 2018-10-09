package java.fr.eni.qcm.dal.factory;

import java.fr.eni.qcm.dal.dao.UtilisateurDAO;
import java.fr.eni.qcm.dal.dao.impl.UtilisateurDaoImpl;

public class DAOFactory {

    public static UtilisateurDAO noteDAO() {
        return UtilisateurDaoImpl.getInstance();
    }
}
