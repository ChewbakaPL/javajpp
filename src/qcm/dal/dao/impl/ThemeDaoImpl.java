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
import qcm.bo.Theme;
import qcm.common.JdbcTools;
import qcm.dal.dao.ThemeDAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ThemeDaoImpl implements ThemeDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final String INSERT_QUERY = "INSERT INTO Theme(libelle) VALUES (:libelle)";
	private static final String UPDATE_QUERY = "UPDATE Theme t SET t.libelle = :libelle WHERE t.idTheme = :idTheme";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Theme t ORDER BY t.idTheme DESC";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM Theme t WHERE t.idTheme = ?";
    private static final String DELETE_QUERY = "DELETE FROM Theme t WHERE t.idTheme = ?";    
    
    private static ThemeDaoImpl instance;
    
    public ThemeDaoImpl() {
        
    }
    
    public Connection getConnection() throws SQLException{
    	return JdbcTools.getConnection();
    }
    
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public static ThemeDaoImpl getInstance() {
        if(instance == null) {
            instance = new ThemeDaoImpl();
        }
        return instance;
    }
    
    @Override
    public Theme insert(Theme object) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, object.getLibelle());
            if (statement.executeUpdate() == 1) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {            
                	object.setIdTheme(resultSet.getInt(1));                    
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
    public void update(Theme object) {
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters
        .addValue("libelle", object.getLibelle())
        .addValue("idTheme", object.getIdTheme());
        int status = namedParameterJdbcTemplate.update(UPDATE_QUERY, parameters); 
        if(status != 0){
            System.out.println("Theme data updated for ID " + object.getIdTheme());
        }else{
            System.out.println("No Theme found with ID " + object.getIdTheme());
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
    public Theme selectById(Integer id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Theme object = null;
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
    public List<Theme> selectAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Theme> list = new ArrayList<>();
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
    
    private Theme resultSetToObject(ResultSet resultSet) throws SQLException {
        Theme object = new Theme();
        object.setIdTheme(resultSet.getInt("idTheme"));
        object.setLibelle(resultSet.getString("libelle"));
        return object;
    }
    
}
