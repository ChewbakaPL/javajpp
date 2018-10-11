package qcm.bll.manager;

import java.util.List;

import fr.eni.tp.web.common.bll.exception.ManagerException;
import qcm.bo.Test;

public interface TestManager {

    List<Test> findAll() throws ManagerException;
}
