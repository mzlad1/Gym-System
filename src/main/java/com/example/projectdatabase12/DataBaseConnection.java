package com.example.projectdatabase12;

public class DataBaseConnection {

	private String dbUsername = "root"; // mysql user name
	private String dbPassword = "root"; // mysql password
	private String URL = "127.0.0.1"; // location of db server
	private String port = "3306"; // constant
	private String dbName = "Gym"; // most likely will not change
	private DBConn connection;

	public DataBaseConnection(String dbUsername, String dbPassword, String URL, String port, String dbName) {
		this.connection = new DBConn(URL, port, dbName, dbUsername, dbPassword);
	}

	public DataBaseConnection() {
		dbUsername = "root";
		dbPassword = "root";
		URL = "127.0.0.1";
		port = "3306";
		dbName = "Gym";
		this.connection = new DBConn(URL, port, dbName, dbUsername, dbPassword);
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public DBConn getCon() {
		return connection;
	}

	public DBConn getConnection() {
		return connection;
	}
}
