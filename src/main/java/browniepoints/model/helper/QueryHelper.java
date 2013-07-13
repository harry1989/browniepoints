package main.java.browniepoints.model.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class QueryHelper {
	public static List<?> ExecuteSelect(
			Connection conn, String query, SQLConverter converter) {
		List<?> result = null;
		
		if (conn == null || query == null || query.trim() == "") {
			return result;
		}
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			System.out.println(query);
			rs = stmt.executeQuery(query);

			result = converter.convertRSToList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static void ExecuteInsertOrUpdate(Connection conn, String sql,
			Object entity, SQLConverter converter) {
		if (conn == null || sql == null || sql.trim() == "") {
			return;
		}
		
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			converter.setColumnsForUpdate(entity, stmt);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
