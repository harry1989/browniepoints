package main.java.browniepoints.model.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface SQLConverter {
	List<?> convertRSToList(ResultSet rs);
	void setColumnsForUpdate(Object entity, PreparedStatement stmt);
}
