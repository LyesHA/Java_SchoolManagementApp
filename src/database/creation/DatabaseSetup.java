package database.creation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseSetup {
	
	public static Connection getConnection(String fileName) throws Exception {
		Properties props = new Properties();
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		props.load(br);
		String driver = props.getProperty("driver");
		String url = props.getProperty("url");
		String database = props.getProperty("database");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		br.close();
		fr.close();
		Class.forName(driver);
		return DriverManager.getConnection(url+database, username, password);		
	}
}
