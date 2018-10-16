package qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ResourceUtil;
import qcm.bo.Question;
import qcm.bo.QuestionTirage;
import qcm.common.JdbcTools;
import qcm.dal.dao.QuestionTirageDAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class QuestionTirageDaoImpl implements QuestionTirageDAO {
	
	private static final String SELECT_BY_EPREUVE = "SELECT * FROM QuestionTirage t WHERE t.idEpreuve = ?";
	
    private static QuestionTirageDaoImpl instance;
    
    public QuestionTirageDaoImpl() {
        
    }
    
    public Connection getConnection() throws SQLException{
    	return JdbcTools.getConnection();
    }
    
    public void setDataSource(DataSource dataSource) {
        new NamedParameterJdbcTemplate(dataSource);
    }
    
    public static QuestionTirageDaoImpl getInstance() {
        if(instance == null) {
            instance = new QuestionTirageDaoImpl();
        }
        return instance;
    }
    
    
    @Override
	public ArrayList<QuestionTirage> selectByIdEpreuve(Integer idEpreuve) throws DaoException{
		
		System.out.println("buildQuestionTirages idEpreuve:"+idEpreuve);
		
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
		ArrayList<QuestionTirage> list = new ArrayList<QuestionTirage>();
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_BY_EPREUVE);        
            statement.setInt(1, idEpreuve);
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
    
    private QuestionTirage resultSetToObject(ResultSet resultSet) throws SQLException, DaoException {
        
    	QuestionTirage object = new QuestionTirage();
    	object.setIdQuestionTirage(resultSet.getInt("idQuestionTirage"));
    	object.setIdEpreuve(resultSet.getInt("idEpreuve"));
    	
    	QuestionDaoImpl questionDao = new QuestionDaoImpl();
        Question question = questionDao.selectById(resultSet.getInt("idQuestion"));
        object.setQuestion(question);
    	
    	object.setEstMarquee(JdbcTools.IntToBoolean(resultSet.getInt("estMarquee")));
    	object.setNumOrdre(resultSet.getInt("numOrdre"));
    	return object;
    }

	@Override
	public QuestionTirage insert(QuestionTirage element) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(QuestionTirage element) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public QuestionTirage selectById(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionTirage> selectAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	

    
	
	
	

}
