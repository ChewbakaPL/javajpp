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
import qcm.bo.QuestionTirage;
import qcm.bo.SectionTest;
import qcm.bo.Test;
import qcm.common.JdbcTools;
import qcm.dal.dao.TestDAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class TestDaoImpl implements TestDAO {
	
	private static final String SELECT_ALL_QUERY = "SELECT * FROM Test";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM Test WHERE idTest = ?";
	
    private static TestDaoImpl instance;
    
    public TestDaoImpl() {
        
    }
    
    public Connection getConnection() throws SQLException{
    	return JdbcTools.getConnection();
    }
    
    public void setDataSource(DataSource dataSource) {
        new NamedParameterJdbcTemplate(dataSource);
    }
    
    public static TestDaoImpl getInstance() {
        if(instance == null) {
            instance = new TestDaoImpl();
        }
        return instance;
    }
 
    @Override
    public List<Test> selectAll() throws DaoException {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Test> list = new ArrayList<>();
        
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
    
    private Test resultSetToObject(ResultSet resultSet) throws SQLException, DaoException {
        
        Test object = new Test();
        object.setIdTest(resultSet.getInt("idTest"));
        object.setLibelle(resultSet.getString("libelle"));
        object.setDescription(resultSet.getString("description"));
        object.setDuree(resultSet.getInt("duree"));
        object.setSeuilBas(resultSet.getDouble("seuilBas"));
        object.setSeuilHaut(resultSet.getDouble("seuilHaut"));
        
        SectionTestDaoImpl sectionTestDao = new SectionTestDaoImpl();
        List<SectionTest> sectionTests = sectionTestDao.selectByIdTest(object.getIdTest());
        object.setSectionTests((ArrayList<SectionTest>) sectionTests);
        
        return object;
        
    }

	@Override
	public Test insert(Test element) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Test element) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Test selectById(Integer id) throws DaoException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    Test object = null;
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
}
