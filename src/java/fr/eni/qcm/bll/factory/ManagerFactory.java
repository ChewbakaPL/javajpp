package java.fr.eni.qcm.bll.factory;

import java.fr.eni.qcm.bll.manager.UtilisateurManager;
import java.fr.eni.qcm.bll.manager.impl.UtilisateurManagerImpl;

public class ManagerFactory {

    public static UtilisateurManager utilisateurManager() {
        return UtilisateurManagerImpl.getInstance();
    }
}
