package qcm.bll.factory;

import qcm.bll.manager.UtilisateurManager;
import qcm.bll.manager.impl.UtilisateurManagerImpl;

public class ManagerFactory {

    public static UtilisateurManager utilisateurManager() {
        return UtilisateurManagerImpl.getInstance();
    }
}
