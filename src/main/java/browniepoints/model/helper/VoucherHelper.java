package main.java.browniepoints.model.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import main.java.browniepoints.model.Voucher;
import main.java.browniepoints.util.Util;

public class VoucherHelper implements SQLConverter {
	private static final VoucherHelper instance = new VoucherHelper();
	private static final int VOUCHER_LEN = 10;

	private static final Connection conn = ConnectionProvider.getConnection();

	private static final String INSERT_SQL = "insert into public.\"voucher\" "
			+ "(cid, winner_uid, code, date, status) "
			+ "values (?, ?, ?, ?, ?)";
	private static final String SELECT_SQL = "select "
			+ "vid, cid, winner_uid, code, date, status "
			+ "from public.\"voucher\" order by date desc";

	private VoucherHelper() {
		init();
	}

	public static VoucherHelper getInstance() {
		return instance;
	}

	private void init() {
	}

	public List<?> convertRSToList(ResultSet rs) {
		List<Voucher> voucherList = new ArrayList<Voucher>();

		if (rs == null)
			return voucherList;

		try {
			while (rs.next()) {
				Integer vid = rs.getInt("vid");
				Integer cid = rs.getInt("cid");
				Integer winnerUid = rs.getInt("winner_uid");
				String code = rs.getString("code");
				Long date = rs.getLong("date");
				String status = rs.getString("status");

				voucherList.add(new Voucher(vid, cid, winnerUid, code, date,
						status));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return voucherList;
	}

	public void setColumnsForUpdate(Object entity, PreparedStatement stmt) {
		if (stmt == null || entity == null)
			return;

		Voucher voucher = (Voucher) entity;
		try {
			stmt.setInt(1, voucher.getCid());
			stmt.setInt(2, voucher.getWinner_uid());
			stmt.setString(3, voucher.getCode());
			stmt.setLong(4, voucher.getDate());
			stmt.setString(5, voucher.getStatus());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String insert(Integer cid, Integer uid) {
		String code = UUID.randomUUID().toString().toUpperCase();
		int length = code.length() > VOUCHER_LEN ? VOUCHER_LEN : code.length();
		code = code.substring(0, length);
		Voucher v = new Voucher(cid, uid, code, System.currentTimeMillis(),
				"NEW");
		insert(v);
		return code;
	}

	public void insert(Voucher voucher) {
		QueryHelper.ExecuteInsertOrUpdate(conn, INSERT_SQL, voucher, this);
		// update cache
		init();
	}

	public List<Voucher> select() {
		return (List<Voucher>) QueryHelper
				.ExecuteSelect(conn, SELECT_SQL, this);
	}
}