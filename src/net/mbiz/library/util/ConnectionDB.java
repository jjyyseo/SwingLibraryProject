package net.mbiz.library.util;

import java.sql.*;
import java.sql.SQLException;

public class ConnectionDB {

	public static void main(String[] args) throws ClassNotFoundException {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://192.168.1.93:1433;databaseName=BOOKMGR;encrypt=true;trustServerCertificate=true";
			Connection conn = DriverManager.getConnection(connectionUrl, "BOOKMGR", "BOOKMGR");
			Statement stmt = conn.createStatement();
			System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
			
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK");
			
			while(rs.next()) {
				System.out.println(rs.getString("BK_ISBN")
						+"///"+rs.getString("BK_NM")
						+"///"+rs.getString("BK_WTR"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQLException : " + e);
			e.printStackTrace();
		}
	}
}


