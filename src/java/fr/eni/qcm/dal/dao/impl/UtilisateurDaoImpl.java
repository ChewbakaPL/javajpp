package java.fr.eni.qcm.dal.dao.impl;

import java.fr.eni.qcm.bo.Utilisateur;
import java.fr.eni.qcm.dal.dao.UtilisateurDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.dal.factory.MSSQLConnectionFactory;
import fr.eni.tp.web.common.util.ResourceUtil;

public class UtilisateurDaoImpl implements UtilisateurDAO {

	//private static final String INSERT_QUERY = "INSERT INTO Utilisateur(idUtilisateur, nom, email, password, type) VALUES (:idUtilisateur, :nom, :prenom, :email, :password, :type)";
	//private static final String UPDATE_QUERY = "UPDATE Utilisateur t SET t.nom=:nom, t.prenom=:prenom, email=:email, password=:password, type=:type WHERE t.idUtilisateur=:idUtilisateur";
    
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Utilisateur t ORDER BY t.idUtilisateur DESC";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM Utilisateur t WHERE t.idUtilisateur = ?";
    private static final String INSERT_QUERY = "INSERT INTO Utilisateur(idUtilisateur, nom, email, password, type) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM Utilisateur t WHERE t.idUtilisateur = ?";
    private static final String UPDATE_QUERY = "UPDATE Utilisateur t SET t.nom=?, t.prenom=?, email=?, password=?, type=? WHERE t.idUtilisateur=?";
    
    private static final String SELECT_ONE_BY_EMAIL_AND_PASSWORD_QUERY = "SELECT * FROM Utilisateur WHERE email=? AND password=?";
    
    private static UtilisateurDaoImpl instance;
    
    private UtilisateurDaoImpl() {
        
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
            connection = MSSQLConnectionFactory.get();
            
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
    public void update(Utilisateur object) throws DaoException {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, object.getNom());
            statement.setString(2, object.getPrenom());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getPassword());
            statement.setString(5, object.getType());
            statement.setInt(6, object.getIdUtilisateur());
            
            //NamedParameterStatement p = new NamedParameterStatement(connection, UPDATE_QUERY);
            //statement.setString("nom", object.getNom());
            //statement.setString("prenom", object.getPrenom());
            //statement.setString("email", object.getEmail());
            //statement.setString("password", object.getPassword());
            //statement.setString("type", object.getType());
            //statement.setInt("idUtilisateur", object.getIdUtilisateur());
            
            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(resultSet, statement, connection);
        }
        
    }

    @Override
    public void delete(Integer id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = MSSQLConnectionFactory.get();
            
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
            connection = MSSQLConnectionFactory.get();
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
    public List<Utilisateur> selectAll() throws DaoException {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Utilisateur> list = new ArrayList<>();
        
        try {
            connection = MSSQLConnectionFactory.get();
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
            connection = MSSQLConnectionFactory.get();
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
