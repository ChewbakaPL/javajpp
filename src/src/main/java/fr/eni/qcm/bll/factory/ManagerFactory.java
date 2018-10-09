package src.main.java.fr.eni.qcm.bll.factory;

import src.main.java.fr.eni.qcm.bll.manager.UtilisateurManager;
import src.main.java.fr.eni.qcm.bll.manager.impl.UtilisateurManagerImpl;

public class ManagerFactory {

    public static UtilisateurManager utilisateurManager() {
        return UtilisateurManagerImpl.getInstance();
    }
}
