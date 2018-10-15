package qcm.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ResourceUtil;
import qcm.bo.SectionTest;
import qcm.bo.Theme;
import qcm.common.JdbcTools;
import qcm.dal.dao.SectionTestDAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class SectionTestDaoImpl implements SectionTestDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    private static final String SELECT_BY_TEST = "SELECT * FROM SectionTest t WHERE t.idTest=?";
    
    private static SectionTestDaoImpl instance;
    
    public SectionTestDaoImpl() {
        
    }
    
    public Connection getConnection() throws SQLException{
    	return JdbcTools.getConnection();
    }
    
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public static SectionTestDaoImpl getInstance() {
        if(instance == null) {
            instance = new SectionTestDaoImpl();
        }
        return instance;
    }
  
    @Override
    public List<SectionTest> selectByIdTest(Integer idTest) throws DaoException {
    	
    	Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SectionTest> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_BY_TEST);
            statement.setInt(1, idTest);
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
    
    private SectionTest resultSetToObject(ResultSet resultSet) throws SQLException, DaoException {
        
    	SectionTest object = new SectionTest();
        Integer idTest = resultSet.getInt("idTest");
        Integer idTheme = resultSet.getInt("idTheme");
        
        //TestDaoImpl testDao = new TestDaoImpl();
        //Test test = testDao.selectById(idTest);
        //object.setTest(test);
        //boucle :(
        object.setIdTest(idTest);
        
        ThemeDaoImpl themeDao = new ThemeDaoImpl();
        Theme theme = themeDao.selectById(idTheme);
        object.setTheme(theme);
        
        object.setNbQuestionATirer(resultSet.getInt("nbQuestionATirer"));
        
        return object;
    }

	@Override
	public SectionTest insert(SectionTest element) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SectionTest element) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SectionTest selectById(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SectionTest> selectAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
    
}
