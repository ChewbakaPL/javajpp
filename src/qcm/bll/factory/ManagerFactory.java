package qcm.bll.factory;

import qcm.bll.manager.TestManager;
import qcm.bll.manager.UtilisateurManager;
import qcm.bll.manager.impl.UtilisateurManagerImpl;
import qcm.bll.manager.impl.TestManagerImpl;;

public class ManagerFactory {

    public static UtilisateurManager utilisateurManager() {
        return UtilisateurManagerImpl.getInstance();
    }
    
    public static TestManager testManager() {
        return TestManagerImpl.getInstance();
    }
}
