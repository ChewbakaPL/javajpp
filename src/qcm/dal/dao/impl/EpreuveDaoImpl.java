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
import qcm.bo.Question;
import qcm.bo.QuestionTirage;
import qcm.bo.Test;
import qcm.bo.Utilisateur;
import qcm.common.JdbcTools;
import qcm.dal.dao.EpreuveDAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class EpreuveDaoImpl implements EpreuveDAO {
	
	private static final String SELECT_ALL_QUERY = "SELECT * FROM Epreuve t";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM Epreuve t WHERE t.idEpreuve = ?";
    private static final String SELECT_QUESTION_TIRAGE = "SELECT * FROM QuestionTirage t WHERE t.idEpreuve = ?";
    private static final String SELECT_BY_USER_QUERY = "SELECT * FROM Epreuve t WHERE t.idUtilisateur = ?";
    
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
            	ArrayList<QuestionTirage> questionTirages = buildQuestionTirages(object.getIdEpreuve());
            	object.setQuestionTirages(questionTirages);
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
    
    @SuppressWarnings("null")
	public List<Epreuve> selectByUser(Integer id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Epreuve> list = new ArrayList<>();
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_BY_USER_QUERY);
            statement.setInt(1, id);
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
    
    private Epreuve resultSetToObject(ResultSet resultSet) throws SQLException, DaoException {
    	Epreuve object = new Epreuve();
        object.setIdEpreuve(resultSet.getInt("idEpreuve"));
        object.setDateDebutValidite(resultSet.getInt("dateDebutValidite"));
        object.setDateFinValidite(resultSet.getInt("dateFinValidite"));
        object.setTempsEcoule(resultSet.getInt("tempsEcoule"));
        object.setEtat(resultSet.getInt("etat"));
        object.setNoteObtenu(resultSet.getDouble("noteObtenu"));
        object.setNiveauObtenu(resultSet.getInt("niveauObtenu"));
        UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl();
        Utilisateur utilisateur = utilisateurDao.selectById(resultSet.getInt("idUtilisateur"));
        object.setUtilisateur(utilisateur);
        TestDaoImpl testDao = new TestDaoImpl();
        Test test = testDao.selectById(resultSet.getInt("idTest"));
        object.setTest(test);
        return object;
    }
    
    private QuestionTirage resultSetToQuestionTirage(ResultSet resultSet) throws SQLException, DaoException {
    	QuestionTirage object = new QuestionTirage();
    	object.setIdEpreuve(resultSet.getInt("idTest"));
    	
    	QuestionDaoImpl questionDao = new QuestionDaoImpl();
        Question question = questionDao.selectById(resultSet.getInt("idQuestion"));
        object.setQuestion(question);
    	
    	object.setEstMarquee(JdbcTools.IntToBoolean(resultSet.getInt("estMarquee")));
    	object.setNumOrdre(resultSet.getInt("numOrdre"));
    	return object;
    }

	private ArrayList<QuestionTirage> buildQuestionTirages(Integer idEpreuve) throws DaoException{
		
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
		ArrayList<QuestionTirage> list = new ArrayList<QuestionTirage>();
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_QUESTION_TIRAGE);        
            statement.setInt(1, idEpreuve);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                list.add(resultSetToQuestionTirage(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
		
		
		return list;
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
