package qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.Date;
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
import qcm.bo.Test;
import qcm.bo.Utilisateur;
import qcm.common.JdbcTools;
import qcm.dal.dao.EpreuveDAO;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class EpreuveDaoImpl implements EpreuveDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final String SELECT_ALL_QUERY = "SELECT * FROM Epreuve t";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM Epreuve t WHERE t.idEpreuve = ?";
    private static final String SELECT_BY_USER_QUERY = "SELECT * FROM Epreuve t WHERE t.idUtilisateur = ?";

    //private static final String UPDATE_QUERY = "UPDATE Epreuve SET dateDebutValidite=:dateDebutValidite, dateFinValidite=:dateFinValidite, dateDebutTest=:dateDebutTest, tempsEcoule=:tempsEcoule, etat=:etat, noteObtenu=:noteObtenu, niveauObtenu=:niveauObtenu, idTest=:idTest, idUtilisateur=:idUtilisateur WHERE idEpreuve=:idEpreuve";
    private static final String UPDATE_QUERY = "UPDATE Epreuve SET dateDebutValidite='?', dateFinValidite='?', dateDebutTest='?', tempsEcoule='?', etat='?', noteObtenu='?', niveauObtenu='?', idTest='?', idUtilisateur='?' WHERE t.idEpreuve='?'";
    
    
    private static EpreuveDaoImpl instance;
    
    public EpreuveDaoImpl() {
        
    }
    
    public Connection getConnection() throws SQLException{
    	return JdbcTools.getConnection();
    }
    
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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

            QuestionTirageDaoImpl questionTirageDao = new QuestionTirageDaoImpl();
            
            while (resultSet.next()) {
            	object = resultSetToObject(resultSet);
            	ArrayList<QuestionTirage> questionTirages = questionTirageDao.selectByIdEpreuve(object.getIdEpreuve());
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
        
        java.util.Date javaUtilDate = resultSet.getDate("dateDebutTest");
        object.setDateDebutTest(javaUtilDate);
        
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
    
    
    @Override
    public void update(Epreuve object) throws DaoException {
          
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
    	System.out.println("EpreuveDao update idEpreuve: "+object.getIdEpreuve());
    	
        connection = getConnection();
        
        statement = connection.prepareStatement(UPDATE_QUERY);
        
        Date javaSqlDate = new java.sql.Date(object.getDateDebutTest().getTime());
        
        
        //DaoException: L'index 1 est hors limites :(
        statement.setInt(1, object.getDateDebutValidite());
        statement.setInt(2, object.getDateFinValidite());
        statement.setDate(3, javaSqlDate);
        statement.setInt(4, object.getTempsEcoule());
        statement.setLong(5, object.getEtat());
        statement.setDouble(6, object.getNoteObtenu());
        statement.setInt(7, object.getNiveauObtenu());
        statement.setInt(8, object.getTest().getIdTest());
        statement.setInt(9, object.getUtilisateur().getIdUtilisateur());
        statement.setInt(10, object.getIdEpreuve());
        
        statement.executeUpdate();

    } catch(SQLException e) {
        throw new DaoException(e.getMessage(), e);
    } finally {
        ResourceUtil.safeClose(resultSet, statement, connection);
    }
    
  }
    
    /*
	@Override
	public void update(Epreuve object) throws DaoException {
        // Adding params using MapSqlParameterSource class
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
        
    	Date javaSqlDate = new java.sql.Date(object.getDateDebutTest().getTime());
    	
        parameters.addValue("dateDebutValidite", object.getDateDebutValidite())
        .addValue("dateFinValidite", object.getDateFinValidite())
        .addValue("dateDebutTest", javaSqlDate)
        .addValue("tempsEcoule", object.getTempsEcoule())
        .addValue("etat", object.getEtat())
        .addValue("noteObtenu", object.getNoteObtenu())
        .addValue("niveauObtenu", object.getNoteObtenu())
        .addValue("idTest", object.getTest().getIdTest())
        .addValue("idUtilisateur", object.getUtilisateur().getIdUtilisateur());
        //.addValue("idEpreuve", object.getIdEpreuve());  //NOK?
        
        
        int status = namedParameterJdbcTemplate.update(UPDATE_QUERY, parameters); 
        if(status != 0){
            System.out.println("User data updated for ID " + object.getIdEpreuve());
        }else{
            System.out.println("No User found with ID " + object.getIdEpreuve());
        }
	}*/
	
	



	@Override
	public Epreuve insert(Epreuve element) throws DaoException {
		throw new DaoException("NOT IMPLEMENTED");
	}


	@Override
	public void delete(Integer id) throws DaoException {
		throw new DaoException("NOT IMPLEMENTED");
	}
}
