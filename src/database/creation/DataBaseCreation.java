package database.creation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreation {
	public static void createTables(Connection connection, String sqlCommand) throws Exception {
		Statement statement;
		statement = connection.createStatement();
		statement.executeUpdate(sqlCommand);
	}
	
	public static void insertData_ddl(Connection connection, String sqlCommand) throws Exception {
		Statement statement;
		statement = connection.createStatement();
		statement.executeUpdate(sqlCommand);
	}
	
	
	
	
}
