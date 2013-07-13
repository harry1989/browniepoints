package main.java.browniepoints.model.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.browniepoints.model.Coupon;

public class CouponHelper implements SQLConverter {

	private static Connection conn = null;
	private static final CouponHelper instance = new CouponHelper();
	
	private static final String INSERT_SQL = "insert into public.\"coupon\" "
			+ "(qid, title, desc, quota, discount, points, start_date, end_date) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_SQL = "select "
			+ "cid, qid, title, \"desc\", quota, discount, points, start_date, end_date "
			+ "from public.\"coupon\"";
	private static final String UPDATE_SQL = "update public.\"coupon\" "
			+ "set qid = ?, title = ?, desc = ?, quota = ?, discount = ?, "
			+ "points = ?, start_date = ?, end_date = ? where cid = ?";
	
	Map<Integer, Coupon> couponByCID = new HashMap<Integer, Coupon>();
	Map<Integer, Coupon> couponByQID = new HashMap<Integer, Coupon>();
	
	private CouponHelper() {
		init();
	}
	
	public static CouponHelper getInstance() {
		return instance;
	}
	
	private void init() {
		if (null == conn) {
			conn = ConnectionProvider.getConnection();
		}		
		List<Coupon> coupons = select();
		
		couponByCID.clear();
		
		for (Integer i = 0; i < coupons.size(); ++i) {
			couponByCID.put(coupons.get(i).getCid(), coupons.get(i));
			couponByQID.put(coupons.get(i).getQid(), coupons.get(i));
		}
	}
	
	public List<?> convertRSToList(ResultSet rs) {
		List<Coupon> couponList = new ArrayList<Coupon>();
		
		if (rs == null) return couponList;
	
		try {
			while (rs.next()) {
				Integer cid = rs.getInt("cid");
				Integer qid = rs.getInt("qid");
				String title = rs.getString("title");
				String desc = rs.getString("desc");
				Integer quota = rs.getInt("quota");
				Integer discount = rs.getInt("discount");
				Integer points = rs.getInt("points");
				Long startDate = rs.getLong("start_date");
				Long endDate = rs.getLong("end_date");

				couponList.add(new Coupon(cid, qid, title, desc, quota, 
						discount, points, startDate, endDate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return couponList;
	}


	public void setColumnsForUpdate(Object entity, PreparedStatement stmt) {
		if (stmt == null || entity == null) return;
		
		Coupon coupon = (Coupon) entity;
		try {
			stmt.setInt(1, coupon.getQid());
			stmt.setString(2, coupon.getTitle());
			stmt.setString(3, coupon.getDesc());
			stmt.setInt(4, coupon.getQuota());
			stmt.setInt(5, coupon.getDiscount());
			stmt.setInt(6, coupon.getPoints());
			stmt.setLong(7, coupon.getStart_date());
			stmt.setLong(8, coupon.getEnd_date());
			if (coupon.hasCid()) stmt.setInt(9, coupon.getCid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(Coupon coupon) {
		QueryHelper.ExecuteInsertOrUpdate(conn, INSERT_SQL, coupon, this);
		// update cache
		init();
	}
	
	public void update(Coupon coupon) {
		QueryHelper.ExecuteInsertOrUpdate(conn, UPDATE_SQL, coupon, this);
		// update cache
		init();
	}
	
	public List<Coupon> select() {
		return (List<Coupon>) QueryHelper.ExecuteSelect(
				conn, SELECT_SQL, this);
	}
	
	public Coupon getCoupon(Integer id) {
		return couponByCID.get(id);
	}
	
	public Coupon getCouponByQid(Integer qid) {
		return couponByQID.get(qid);
	}	
}
