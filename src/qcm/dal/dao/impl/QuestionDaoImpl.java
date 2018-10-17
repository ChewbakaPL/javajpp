package qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ResourceUtil;
import qcm.bo.Proposition;
import qcm.bo.Question;
import qcm.bo.Theme;
import qcm.common.JdbcTools;
import qcm.dal.dao.QuestionDAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class QuestionDaoImpl implements QuestionDAO {
	
	private static final String SELECT_ONE_QUERY = "SELECT * FROM Question t WHERE t.idQuestion = ?";
	private static final String SELECT_BY_THEME_AND_LIMIT_QUERY = "SELECT TOP LIMIT * FROM Question t WHERE t.idTheme=?;";
	
    private static QuestionDaoImpl instance;
    
    public QuestionDaoImpl() {
        
    }
    
    public Connection getConnection() throws SQLException{
    	return JdbcTools.getConnection();
    }
    
    public void setDataSource(DataSource dataSource) {
        new NamedParameterJdbcTemplate(dataSource);
    }
    
    public static QuestionDaoImpl getInstance() {
        if(instance == null) {
            instance = new QuestionDaoImpl();
        }
        return instance;
    }
    
    @Override
    public Question selectById(Integer id) throws DaoException {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Question object = null;
        
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
    
    private Question resultSetToObject(ResultSet resultSet) throws SQLException, DaoException {
        
    	Integer idQuestion = resultSet.getInt("idQuestion");
    	
        Question object = new Question();
        object.setIdQuestion(idQuestion);
        object.setEnonce(resultSet.getString("enonce"));
        object.setMedia(resultSet.getString("media"));
        object.setPoints(resultSet.getDouble("points"));
        
        ThemeDaoImpl themeDao = new ThemeDaoImpl();
        Theme theme = themeDao.selectById(resultSet.getInt("idTheme"));
        object.setTheme(theme);
    	
    	PropositionDaoImpl propositionDao = new PropositionDaoImpl();
    	ArrayList<Proposition> propositions = (ArrayList<Proposition>) propositionDao.selectByIdQuestion(idQuestion);
        object.setPropositions(propositions);
        
        return object;
    }

	@Override
	public Question insert(Question element) throws DaoException {
		throw new DaoException("NOT IMPLEMENTED");
	}

	@Override
	public void update(Question element) throws DaoException {
		throw new DaoException("NOT IMPLEMENTED");
	}

	@Override
	public void delete(Integer id) throws DaoException {
		throw new DaoException("NOT IMPLEMENTED");
	}

	@Override
	public List<Question> selectAll() throws DaoException {
		throw new DaoException("NOT IMPLEMENTED");
	}
	
    @Override
    public List<Question> selectByTheme(Integer idTheme, Integer nbQuestionATirer) throws DaoException {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Question> list = new ArrayList<>();
        
        try {
            connection = getConnection();
            
            String newReqMerciSQLServer = SELECT_BY_THEME_AND_LIMIT_QUERY.replaceFirst("LIMIT",nbQuestionATirer.toString());
            statement = connection.prepareStatement(newReqMerciSQLServer);
            
            //statement.setInt(1, nbQuestionATirer);
            statement.setInt(1, idTheme);
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
	
	
	

}
