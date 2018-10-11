package qcm.bll.manager.impl;

import java.util.List;

import fr.eni.tp.web.common.bll.exception.ManagerException;
import qcm.bll.manager.TestManager;
import qcm.bo.Test;
import qcm.dal.dao.TestDAO;
import qcm.dal.factory.DAOFactory;

public class TestManagerImpl implements TestManager {

	private TestDAO dao = DAOFactory.testDAO();
	
    private static TestManagerImpl instance;
    
    private TestManagerImpl() {
        
    }
    
    public static TestManagerImpl getInstance() {
        if(instance == null) {
            instance = new TestManagerImpl();
        }
        return instance;
    }

    
    @Override
    public List<Test> findAll() throws ManagerException {
        
        List<Test> list = null;
        
        try {
            list = dao.selectAll();
            
        } catch (Exception e) {
            throw new ManagerException(e.getMessage(), e);
        }
                
        return list;
    }

}
