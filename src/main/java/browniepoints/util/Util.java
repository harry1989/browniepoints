package main.java.browniepoints.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
		if (dt == null) return -1;
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
	
	public static void convertToJSON(Object obj, HttpServletResponse response) {
		if (obj == null || response == null) return;
		
		Gson gson = new Gson();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String json = gson.toJson(obj);
		System.out.println(json);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
