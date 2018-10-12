package qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ResourceUtil;
import qcm.bo.Epreuve;
import qcm.dal.dao.EpreuveDAO;
import qcm.dal.factory.JdbcTools;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class EpreuveDaoImpl implements EpreuveDAO {
	
	private static final String SELECT_ALL_QUERY = "SELECT * FROM Epreuve t";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM Epreuve t WHERE t.idEpreuve = ?";
	
    private static final String JOIN_QUESTION_TIRAGE = " JOIN QuestionTirage t2 ON t.idEpreuve = t2.idEpreuve";
    
    private static EpreuveDaoImpl instance;
    
    public EpreuveDaoImpl() {
        
    }
    
    public Connection getConnection() throws SQLException{
    	return JdbcTools.getConnection();
    }
    
    public void setDataSource(DataSource dataSource) {
        new NamedParameterJdbcTemplate(dataSource);
    }
    
    public static EpreuveDaoImpl getInstance() {
        if(instance == null) {
            instance = new EpreuveDaoImpl();
        }
        return instance;
    }
    
    
    @Override
    public Epreuve selectById(Integer id) throws DaoException {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Epreuve object = null;
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_ONE_QUERY);
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	object = resultSetToObject(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return object;
    }
 
    
    @Override
    public List<Epreuve> selectAll() throws DaoException {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Epreuve> list = new ArrayList<>();
        
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_QUERY);

            while (resultSet.next()) {
                list.add(resultSetToObject(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return list;
    }
    
    private Epreuve resultSetToObject(ResultSet resultSet) throws SQLException {
        
    	Epreuve object = new Epreuve();
        object.setIdEpreuve(resultSet.getInt("idTest"));

        //TODO
        //...
        
        return object;
        
    }

	@Override
	public Epreuve insert(Epreuve element) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Epreuve element) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		
	}




}
