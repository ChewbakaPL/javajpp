package qcm.common;
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
		String host = "jdbc:sqlserver://127.0.0.1;databasename=javaQCM";
		//String host = "jdbc:sqlserver://10.102.200.28;databasename=javaQCM"; //no pass firewall :(
		Connection connection = null;
		connection = DriverManager.getConnection(host, "sa", "Pa$$w0rd");
		return connection;
	}
	
	public static Boolean IntToBoolean(Integer input){
		Boolean output = false;
		if(input == 1){
			output = true;
		}
		return output;
	}
	public static Integer BooleanToInt(Boolean input){
		Integer output = 0;
		if(input == true){
			output = 1;
		}
		return output;
	}
	

}
