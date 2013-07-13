package main.java.browniepoints.model.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.browniepoints.model.User;

public class UserHelper implements SQLConverter {
	private static final Connection conn = ConnectionProvider.getConnection();
	
	private static final String INSERT_SQL = "insert into public.\"user\" "
			+ "(email, name, points, phone) values (?, ?, ?, ?)";
	private static final String SELECT_SQL = "select uid, email, name, points, phone "
			+ "from public.\"user\"";
	private static final String UPDATE_SQL = "update public.\"user\" "
			+ "set email = ?, name = ?, points = ?, phone = ? where uid = ?";
	
	Map<Integer, User> userByUID = new HashMap<Integer, User>();
	Map<String, User> userByEmail = new HashMap<String, User>();
	
	public UserHelper() {
		init();
	}
	
	private void init() {
		List<User> users = select();
		
		userByEmail.clear();
		userByUID.clear();
		
		for (Integer i = 0; i < users.size(); ++i) {
			userByEmail.put(users.get(i).getEmail(), users.get(i));
			userByUID.put(users.get(i).getUid(), users.get(i));			
		}
	}
	
	public List<?> convertRSToList(ResultSet rs) {
		List<User> userList = new ArrayList<User>();
		
		if (rs == null) return userList;
	
		try {
			while (rs.next()) {
				Integer uid = rs.getInt("uid");				
				String email = rs.getString("email");
				String name = rs.getString("name");
				Integer points = rs.getInt("points");
				String phone = rs.getString("phone");

				userList.add(new User(uid, email, name, points, phone));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userList;
	}


	public void setColumnsForUpdate(Object entity, PreparedStatement stmt) {
		if (stmt == null || entity == null) return;
		
		User user = (User) entity;
		try {
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getName());
			stmt.setInt(3, user.getPoints());
			stmt.setString(4, user.getPhone());
			if (user.hasUid()) stmt.setInt(5, user.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(User user) {
		QueryHelper.ExecuteInsertOrUpdate(conn, INSERT_SQL, user, this);
		// update cache
		init();
	}
	
	public void update(User user) {
		QueryHelper.ExecuteInsertOrUpdate(conn, UPDATE_SQL, user, this);
		// update cache
		init();
	}
	
	public List<User> select() {
		return (List<User>) QueryHelper.ExecuteSelect(conn, SELECT_SQL, this);
	}
	
	public User getUser(Integer id) {
		return userByUID.get(id);
	}
	
	public User getUser(String email) {
		return userByEmail.get(email);
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		conn.close();
	}

	public static void main(String[] args) {
		User user = new User("paul.rahul@gmail.com", "Rahul Paul", 0, "12345");
		
		UserHelper helper = new UserHelper();
		helper.insert(user);
		List<User> users = helper.select();
		String email = "";
		for (Integer i = 0; i < users.size(); ++i) {
			email = users.get(i).getEmail();
			System.out.println(email);
		}

		User nu = helper.getUser(email);
		System.out.println(nu.getUid());
		nu.setEmail("raj@gmail.com");
		helper.update(nu);
	}
}