package vn.iotstar.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private final String serverName = "DESKTOP-EBUN5JD";
	private final String dbName = "ltwebst4";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "1234567890";
	
	public Connection getConnection() {
		Connection conn = null;
		try {
		String url = "jdbc:sqlserver://"+serverName+":"+portNumber+"\\" + instance + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
		{
			url = "jdbc:sqlserver://" + serverName + ":"+ portNumber + "; databaseName = "+dbName;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url,userID, password);
			if (conn != null)
			{
				DatabaseMetaData dm= (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name:" + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name:" +dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
				return conn;
			}
		}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return conn;
	}
	public static void main(String[] args) {
		try {
			System.out.println( new DBConnection().getConnection());
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		}
	}