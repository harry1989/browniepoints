package main.java.browniepoints.model.helper;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static final String DB_URI = "postgres://eozjtzosyxjojd:qiPf8B87dwJo2MeGuJtab83xdz@ec2-54-235-173-50.compute-1.amazonaws.com:5432/dbs1ngtb0hsdsc";
	private static final String POSTGRES_PREFIX = "jdbc:postgresql://";
	private static final String NO_SSL = "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	
	private ConnectionProvider () {
	}
	
	public static Connection getConnection() {
	    URI dbUri = null;
		try {
			dbUri = new URI(DB_URI);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = POSTGRES_PREFIX + dbUri.getHost() + ':'
	    		+ dbUri.getPort() + dbUri.getPath() + NO_SSL;
	    
	    Connection conn = null;
	    try {
			conn = DriverManager.getConnection(dbUrl, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return conn;
	}
}
