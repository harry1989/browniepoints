package main.java.browniepoints.model.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.browniepoints.model.Attempt;
import main.java.browniepoints.util.Util;

public class AttemptHelper implements SQLConverter {
	private static final AttemptHelper instance =  new AttemptHelper();

	private static final Connection conn = ConnectionProvider.getConnection();
	
	private static final String INSERT_SQL = "insert into public.\"attempt\" "
			+ "(uid, qid, result, date, vid) "
			+ "values (?, ?, ?, ?, ?)";
	private static final String SELECT_SQL = "select "
			+ "uid, qid, result, date, vid "
			+ "from public.\"attempt\" order by date desc";
	
	Map<Integer, List<Attempt>> attemptByUID = 
			new HashMap<Integer, List<Attempt>>();
	
	private AttemptHelper() {
		init();
	}
	
	public static AttemptHelper getInstance() {
		return instance;
	}
	
	private void init() {
		List<Attempt> attempts = select();
		
		attemptByUID.clear();
		for (Integer i = 0; i < attempts.size(); ++i) {
			Util.addToMap(
					attemptByUID, attempts.get(i).getUid(), attempts.get(i));	
		}
	}
	
	public List<?> convertRSToList(ResultSet rs) {
		List<Attempt> attemptList = new ArrayList<Attempt>();
		
		if (rs == null) return attemptList;
	
		try {
			while (rs.next()) {
				Integer uid = rs.getInt("uid");
				Integer qid = rs.getInt("qid");
				String result = rs.getString("result");
				Long date = rs.getLong("date");
				Integer cid = rs.getInt("vid");

				attemptList.add(new Attempt(uid, qid, result, date, cid));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return attemptList;
	}


	public void setColumnsForUpdate(Object entity, PreparedStatement stmt) {
		if (stmt == null || entity == null) return;
		
		Attempt attempt = (Attempt) entity;
		try {
			stmt.setInt(1, attempt.getUid());
			stmt.setInt(2, attempt.getQid());
			stmt.setString(3, attempt.getResult());
			stmt.setLong(4, attempt.getDate());
			stmt.setInt(5, attempt.getVid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(Attempt attempt) {
		QueryHelper.ExecuteInsertOrUpdate(conn, INSERT_SQL, attempt, this);
		// update cache
		init();
	}
	
	public List<Attempt> select() {
		return (List<Attempt>) QueryHelper.ExecuteSelect(
				conn, SELECT_SQL, this);
	}
	
	public List<Attempt> getAttempt(Integer id) {
		return attemptByUID.get(id);
	}
}
