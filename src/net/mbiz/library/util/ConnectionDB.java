package net.mbiz.library.util;

import java.sql.*;
import java.sql.SQLException;

public class ConnectionDB {

	public static void main(String[] args) throws ClassNotFoundException {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=BOOKMGR;"
					+ "integratedSecurity=true";
			Connection conn = DriverManager.getConnection(connectionUrl, "jang", "1234");
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
		}
	}
}
