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
	
	public static void ddl_with_parameters(Connection connection, String sqlCmd, int param1,
			String param2, String param3, String param4) throws Exception {
		PreparedStatement prepareStmt;
		prepareStmt = connection.prepareStatement(sqlCmd);
		prepareStmt.setInt(1, param1);
		prepareStmt.setString(2, param2);
		prepareStmt.setString(3, param3);
		prepareStmt.setString(4, param4);
		prepareStmt.executeUpdate();
}
	
	public static void ddl_with_parameters(Connection connection, String sqlCmd, int param1,
			int param2) throws Exception {
		PreparedStatement prepareStmt;
		prepareStmt = connection.prepareStatement(sqlCmd);
		prepareStmt.setInt(1, param1);
		prepareStmt.setInt(2, param2);
		prepareStmt.executeUpdate();
}
	
	public static void ddl_with_parameters(Connection connection, String sqlCmd, int param1,
			int param2, int param3) throws Exception {
		PreparedStatement prepareStmt;
		prepareStmt = connection.prepareStatement(sqlCmd);
		prepareStmt.setInt(1, param1);
		prepareStmt.setInt(2, param2);
		prepareStmt.setInt(3, param3);
		prepareStmt.executeUpdate();
}
		
	public static void displayCourses(Connection connection,String sqlCommand, String param1) throws Exception {
		PreparedStatement prepstmt;
		prepstmt = connection.prepareStatement(sqlCommand);
		prepstmt.setString(1, param1);
		ResultSet rset = prepstmt.executeQuery();
		while(rset.next()) {
			String field1 = rset.getString(1);
			String field2 = rset.getString(2);
			System.out.println(field1 + "\t"+field2);
			}
	}
	
	public static void displayEvaluations(Connection connection, String sqlCommand, String param) throws Exception {
		PreparedStatement preparStmt;
		preparStmt = connection.prepareStatement(sqlCommand);
		preparStmt.setString(1, param);
		ResultSet rSet = preparStmt.executeQuery();
		while(rSet.next()) {
			String field1 = rSet.getString(1);
			String field2 = rSet.getString(2);
			String field3 = rSet.getString(3);
			System.out.println(field1 + "\t"+ field2 + "\t" + field3);
		}
	}
	
	
}
