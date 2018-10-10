package qcm.dal.dao;
import java.sql.Connection;
import java.util.List;

import fr.eni.tp.web.common.dal.exception.DaoException;

public interface GenericDAO<T, ID> {

	Connection getConnection();
	
    T insert(T element) throws DaoException;
    
    void update(T element) throws DaoException; 
    
    void delete(ID id) throws DaoException;
    
    T selectById(ID id) throws DaoException;
    
    List<T> selectAll() throws DaoException;
    
}

