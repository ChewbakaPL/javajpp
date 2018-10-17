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
import qcm.bo.ReponseUtilisateur;
import qcm.common.JdbcTools;
import qcm.dal.dao.ReponseUtilisateurDAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ReponseUtilisateurDaoImpl implements ReponseUtilisateurDAO {
	
    private static final String SELECT_BY_QUESTION_TIRAGE_QUERY = "SELECT * FROM ReponseUtilisateur t WHERE t.idQuestionTirage = ?";
	
    private static final String INSERT_QUERY = "INSERT INTO ReponseUtilisateur(idQuestionTirage, idProposition, idQuestion) VALUES (?, ?, ?)";
    
    private static final String DELETE_QUERY = "DELETE FROM ReponseUtilisateur WHERE idQuestionTirage = ?";
    
    
    private static ReponseUtilisateurDaoImpl instance;
    
    public ReponseUtilisateurDaoImpl() {
        
    }
    
    public Connection getConnection() throws SQLException{
    	return JdbcTools.getConnection();
    }
    
    public void setDataSource(DataSource dataSource) {
        new NamedParameterJdbcTemplate(dataSource);
    }
    
    public static ReponseUtilisateurDaoImpl getInstance() {
        if(instance == null) {
            instance = new ReponseUtilisateurDaoImpl();
        }
        return instance;
    }
 
    
    private ReponseUtilisateur resultSetToObject(ResultSet resultSet) throws SQLException, DaoException {
        
    	ReponseUtilisateur object = new ReponseUtilisateur();
    	object.setIdQuestionTirage(resultSet.getInt("idQuestionTirage"));
    	object.setIdProposition(resultSet.getInt("idProposition"));
    	object.setIdQuestion(resultSet.getInt("idQuestion"));
    	
        return object;
    }
    
	@Override
	public List<ReponseUtilisateur> selectByQuestionTirage() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<ReponseUtilisateur> list = new ArrayList<>();
        
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_BY_QUESTION_TIRAGE_QUERY);

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

	public void insertVoid(ReponseUtilisateur element) throws DaoException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    try {
	        connection = getConnection();
	        statement = connection.prepareStatement(INSERT_QUERY);
	        
	        statement.setInt(1, element.getIdQuestionTirage());
	        statement.setInt(2, element.getIdProposition());
	        statement.setInt(3, element.getIdQuestion());
	        statement.executeUpdate();
	        
	    } catch(SQLException e) {
	        throw new DaoException(e.getMessage(), e);
	    } finally {
	        ResourceUtil.safeClose(resultSet, statement, connection);
	    }
	}

	@Override
	public void update(ReponseUtilisateur element) throws DaoException {
		throw new DaoException("NOT IMPLEMENTED");
	}

	@Override
	public void delete(Integer id) throws DaoException {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
		try {
	        connection = getConnection();
	        statement = connection.prepareStatement(DELETE_QUERY);
	        
	        statement.setInt(1, id);
	        statement.executeUpdate();
	    } catch(SQLException e) {
	        throw new DaoException(e.getMessage(), e);
	    } finally {
	        ResourceUtil.safeClose(resultSet, statement, connection);
	    }
	}

	@Override
	public ReponseUtilisateur selectById(Integer id) throws DaoException {
		throw new DaoException("NOT IMPLEMENTED");
	}
	
	@Override
	public List<ReponseUtilisateur> selectAll() throws DaoException {
		throw new DaoException("NOT IMPLEMENTED");
	}

	@Override
	public ReponseUtilisateur insert(ReponseUtilisateur element) throws DaoException {
		throw new DaoException("NOT IMPLEMENTED");
	}


}
