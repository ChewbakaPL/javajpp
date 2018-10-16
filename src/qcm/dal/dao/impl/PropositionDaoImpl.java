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
import qcm.bo.Proposition;
import qcm.common.JdbcTools;
import qcm.dal.dao.PropositionDAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class PropositionDaoImpl implements PropositionDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final String SELECT_ALL_QUERY = "SELECT * FROM Proposition t";
	private static final String SELECT_BY_QUESTION_QUERY = "SELECT * FROM Proposition t WHERE t.idQuestion=?";
       
    
    
    private static PropositionDaoImpl instance;
    
    public PropositionDaoImpl() {
        
    }
    
    public Connection getConnection() throws SQLException{
    	return JdbcTools.getConnection();
    }
    
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public static PropositionDaoImpl getInstance() {
        if(instance == null) {
            instance = new PropositionDaoImpl();
        }
        return instance;
    }
    
    @Override
    public ArrayList<Proposition> selectByIdQuestion(Integer idQuestion) throws DaoException {
    	ArrayList<Proposition> list = new ArrayList<Proposition>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_BY_QUESTION_QUERY);
            
            statement.setInt(1, idQuestion);
            resultSet = statement.executeQuery();

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
    
    private Proposition resultSetToObject(ResultSet resultSet) throws SQLException {
        Proposition object = new Proposition();
        object.setIdProposition(resultSet.getInt("idProposition"));
        object.setEnonce(resultSet.getString("enonce"));
        object.setEstBonne(JdbcTools.IntToBoolean(resultSet.getInt("estBonne")));
        object.setIdQuestion(resultSet.getInt("idQuestion"));
        return object;
    }

	
	
    @Override
    public List<Proposition> selectAll() throws DaoException {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Proposition> list = new ArrayList<>();
        
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

	@Override
	public Proposition insert(Proposition element) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Proposition element) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Proposition selectById(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
