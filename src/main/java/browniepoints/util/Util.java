package main.java.browniepoints.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import main.java.browniepoints.model.CompositeQuestion;

import com.google.gson.Gson;

public class Util {

	public static <K, V> void addToMap(Map<K, List<V>> m, K k, V v) {
		List<V> toPut = null;
		if (!m.containsKey(k)) {
			toPut = new ArrayList<V>();
		} else {
			toPut = m.get(k);
		}

		toPut.add(v);
		m.put(k, toPut);
	}

	public static Date toDate(long ts) {
		return new Date(ts);
	}

	public static long dateToMillis(Date dt) {
		if (dt == null)
			return -1;
		return dt.getTime();
	}

	public static int dateDiff(long d1, long d2) {
		if (d1 < d2) {
			long t = d1 + d2;
			d2 = t - d2;
			d1 = t - d2;
		}

		return (int) (d1 - d2) / (1000 * 24 * 3600);
	}

	public static String convertToJSON(Object obj) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}	
	
	public static void convertToJSON(Object obj, HttpServletResponse response) throws IOException {
		if (obj == null || response == null)
			return;

		Gson gson = new Gson();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String json = gson.toJson(obj);
		System.out.println(json);

		response.getWriter().write(json);
	}
	
	public static CompositeQuestion toNonRevealMode(CompositeQuestion q) throws CloneNotSupportedException {
		CompositeQuestion nq = q.copyOf();
		
		nq.getQ().setTrivia("");
		nq.getQ().setAnswer("");
		nq.getQ().setRid(null);
		nq.getQ().setApproved_by(null);
		nq.getQ().setCreation_date(null);
		
		return nq;
	}
}
