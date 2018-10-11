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
import qcm.bo.Utilisateur;
import qcm.dal.dao.UtilisateurDAO;
import qcm.dal.factory.JdbcTools;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class UtilisateurDaoImpl implements UtilisateurDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	//private static final String INSERT_QUERY = "INSERT INTO Utilisateur(nom, email, password, type) VALUES (:nom, :prenom, :email, :password, :type)";
	private static final String UPDATE_QUERY = "UPDATE Utilisateur t SET t.nom=:nom, t.prenom=:prenom, email=:email, password=:password, type=:type WHERE t.idUtilisateur=:idUtilisateur";
    
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Utilisateur t ORDER BY t.idUtilisateur DESC";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM Utilisateur t WHERE t.idUtilisateur = ?";
    private static final String DELETE_QUERY = "DELETE FROM Utilisateur t WHERE t.idUtilisateur = ?";
    private static final String INSERT_QUERY = "INSERT INTO Utilisateur(nom, prenom, email, password, type) VALUES (?, ?, ?, ?, ?)";
    //private static final String UPDATE_QUERY = "UPDATE Utilisateur t SET t.nom=?, t.prenom=?, email=?, password=?, type=? WHERE t.idUtilisateur=?";
    
    private static final String SELECT_ONE_BY_MAIL_QUERY = "SELECT * FROM Utilisateur WHERE email=?";
    private static final String SELECT_ONE_BY_EMAIL_AND_PASSWORD_QUERY = "SELECT * FROM Utilisateur WHERE email=? AND password=?";
    
    
    private static UtilisateurDaoImpl instance;
    
    public UtilisateurDaoImpl() {
        
    }
    
    public Connection getConnection() throws SQLException{
    	return JdbcTools.getConnection();
    }
    
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public static UtilisateurDaoImpl getInstance() {
        if(instance == null) {
            instance = new UtilisateurDaoImpl();
        }
        return instance;
    }
    
    @Override
    public Utilisateur insert(Utilisateur object) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, object.getNom());
            statement.setString(2, object.getPrenom());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getPassword());
            statement.setString(5, object.getType());

            if (statement.executeUpdate() == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {            
                	object.setIdUtilisateur(resultSet.getInt(1));                    
                }
            }

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
        return object;
    }
    
    @Override
    public void update(Utilisateur object) {
        // Adding params using MapSqlParameterSource class
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
        
        parameters.addValue("nom", object.getNom())
        .addValue("prenom", object.getPrenom())
        .addValue("email", object.getEmail())
        .addValue("password", object.getPassword())
        .addValue("type", object.getType())
        .addValue("idUtilisateur", object.getIdUtilisateur());
        
        int status = namedParameterJdbcTemplate.update(UPDATE_QUERY, parameters); 
        if(status != 0){
            System.out.println("User data updated for ID " + object.getIdUtilisateur());
        }else{
            System.out.println("No User found with ID " + object.getIdUtilisateur());
        }
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
    public Utilisateur selectById(Integer id) throws DaoException {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utilisateur object = null;
        
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
    public Utilisateur selectByEmail(String email) throws DaoException {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utilisateur object = null;
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_ONE_BY_MAIL_QUERY);
            
            statement.setString(1, email);
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
    public List<Utilisateur> selectAll() throws DaoException {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Utilisateur> list = new ArrayList<>();
        
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
    
    private Utilisateur resultSetToObject(ResultSet resultSet) throws SQLException {
        
        Utilisateur object = new Utilisateur();
        object.setIdUtilisateur(resultSet.getInt("idUtilisateur"));
        object.setNom(resultSet.getString("nom"));
        object.setPrenom(resultSet.getString("prenom"));
        object.setEmail(resultSet.getString("email"));
        object.setPassword(resultSet.getString("password"));
        object.setType(resultSet.getString("type"));
        
        return object;
        
    }

    
    public Utilisateur checkLogin(String email, String password) throws DaoException {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Utilisateur object = null;
        
        try {
            new JdbcTools();
			connection = getConnection();
            statement = connection.prepareStatement(SELECT_ONE_BY_EMAIL_AND_PASSWORD_QUERY);
            
            statement.setString(1, email);
            statement.setString(2, password);
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
