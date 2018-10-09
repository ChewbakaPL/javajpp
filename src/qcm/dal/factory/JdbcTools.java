package qcm.dal.factory;
/**
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * classe en clarge de .
 * @author rleugasigue2018
 * @date 15 mai 2018
 * @version TP_Papeterie_03 v1.0
 */
public class JdbcTools {
	
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection connection = null;		
		connection = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1;databasename=javaQCM"
				, "sa", "Pa$$w0rd");
		return connection;
	}
	
	
	

}
