package org.botCreators.SherpaBot.Sherpa.Utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
	
	private Properties props;
	private Connection conn;
	
	private String username;
	private String password;
	private String url = "jdbc:mysql://localhost:3306/sherpa_bot";
	
	private Properties populateProperties(){
		if (props == null){
			props = new Properties();
	
	    	try (FileReader in = new FileReader("Properties/db.propConfs")) {
	    		props.load(in);
	    	} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}				
	    	
	    	username = props.getProperty("username");
			password = props.getProperty("password");
			url = props.getProperty("url");
		}
		
		return props;
	}
	
	public Connection connect() {
		populateProperties();
		if (conn == null) {
			try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
		return conn;
	}
	
	public void disconnect() {
		if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}


}
