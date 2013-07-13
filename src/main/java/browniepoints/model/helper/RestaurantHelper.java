package main.java.browniepoints.model.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.browniepoints.model.Restaurant;

public class RestaurantHelper implements SQLConverter {

	private static final Connection conn = ConnectionProvider.getConnection();
	
	private static final String INSERT_SQL = "insert into public.\"restaurant\" "
			+ "(name, latitude, longitude, place, phone, cuisine, owner_uid) "
			+ "values (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_SQL = "select "
			+ "rid, name, latitude, longitude, place, phone, cuisine, owner_uid "
			+ "from public.\"restaurant\"";
	private static final String UPDATE_SQL = "update public.\"restaurant\" "
			+ "set name = ?, latitude = ?, longitude = ?, place = ?, "
			+ "phone = ?, cuisine = ?, owner_uid = ? where rid = ?";
	
	Map<Integer, Restaurant> bistroByRID = new HashMap<Integer, Restaurant>();
	
	public RestaurantHelper() {
		init();
	}
	
	private void init() {
		List<Restaurant> bistros = select();
		
		bistroByRID.clear();
		
		for (Integer i = 0; i < bistros.size(); ++i) {
			bistroByRID.put(bistros.get(i).getRid(), bistros.get(i));			
		}
	}
	
	public List<?> convertRSToList(ResultSet rs) {
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();
		
		if (rs == null) return restaurantList;
	
		try {
			while (rs.next()) {
				Integer rid = rs.getInt("uid");				
				String name = rs.getString("email");
				Double latitude = rs.getDouble("latitude");
				Double longitude = rs.getDouble("longitude");
				String place = rs.getString("place");
				String phone = rs.getString("phone");
				String cuisine = rs.getString("cuisine");
				Integer owner_uid = rs.getInt("owner_uid");

				restaurantList.add(new Restaurant(rid, name, latitude,
						longitude, place, phone, cuisine, owner_uid));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return restaurantList;
	}


	public void setColumnsForUpdate(Object entity, PreparedStatement stmt) {
		if (stmt == null || entity == null) return;
		
		Restaurant restaurant = (Restaurant) entity;
		try {
			stmt.setString(1, restaurant.getName());
			stmt.setDouble(2, restaurant.getLatitude());
			stmt.setDouble(3, restaurant.getLongitude());
			stmt.setString(4, restaurant.getPlace());
			stmt.setString(5, restaurant.getPhone());
			stmt.setString(6, restaurant.getCuisine());
			stmt.setInt(7, restaurant.getOwner_uid());
			if (restaurant.hasRid()) stmt.setInt(8, restaurant.getRid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(Restaurant restaurant) {
		QueryHelper.ExecuteInsertOrUpdate(conn, INSERT_SQL, restaurant, this);
		// update cache
		init();
	}
	
	public void update(Restaurant restaurant) {
		QueryHelper.ExecuteInsertOrUpdate(conn, UPDATE_SQL, restaurant, this);
		// update cache
		init();
	}
	
	public List<Restaurant> select() {
		return (List<Restaurant>) QueryHelper.ExecuteSelect(
				conn, SELECT_SQL, this);
	}
	
	public Restaurant getRestaurant(Integer id) {
		return bistroByRID.get(id);
	}

}