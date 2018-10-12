package qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ResourceUtil;
import qcm.bo.Question;
import qcm.common.JdbcTools;
import qcm.dal.dao.QuestionDAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class QuestionDaoImpl implements QuestionDAO {
	
	private static final String SELECT_ONE_QUERY = "SELECT * FROM Question t WHERE t.idQuestion = ?";
	
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
    
    private Question resultSetToObject(ResultSet resultSet) throws SQLException {
        
        Question object = new Question();
        object.setIdQuestion(resultSet.getInt("idQuestion"));
        object.setEnonce(resultSet.getString("enonce"));
        object.setMedia(resultSet.getString("media"));
        object.setPoints(resultSet.getDouble("points"));
        
        /*
        //TODO ThemeDao
        ThemeDaoImpl themeDao = new ThemeDao();
        Theme theme = ThemeDao.selectById(resultSet.getInt("idTheme"));
        object.setTheme(theme);
    	
        //TODO PropositionDao
    	PropositionDaoImpl propositionDao = new PropositionDaoImpl();
    	ArrayList<Proposition> propositions = propositionDao.selectAll();
        object.setPropositions(propositions);
        */
        
        return object;
    }

	@Override
	public Question insert(Question element) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Question element) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Question> selectAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
