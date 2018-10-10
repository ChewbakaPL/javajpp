package qcm.dal.factory;

import qcm.dal.dao.UtilisateurDAO;
import qcm.dal.dao.impl.UtilisateurDaoImpl;

public class DAOFactory {

    public static UtilisateurDAO noteDAO() {
        return UtilisateurDaoImpl.getInstance();
    }
}
