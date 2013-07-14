package main.java.browniepoints.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.browniepoints.model.CompositeQuestion;
import main.java.browniepoints.model.User;
import main.java.browniepoints.model.helper.QuestionHelper;
import main.java.browniepoints.model.helper.UserHelper;
import main.java.browniepoints.util.Util;

/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, List<CompositeQuestion>> ret =
				new HashMap<String, List<CompositeQuestion>>();
		List<CompositeQuestion> lst = QuestionHelper.getInstance()
				.getQuestionsForUser(UserHelper.getInstance().getLoggedInUid());

		ret.put("my_q", lst);
		Util.convertToJSON(ret, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer uid = UserHelper.getInstance().getLoggedInUid();
		String email = UserHelper.getInstance().getUser(uid).getEmail();
		Object name = request.getParameter("name");
		Object phone = request.getParameter("phone");

		UserHelper.getInstance().update(
				new User(uid, email, name == null ? "" : (String) name, 0,
						phone == null ? "" : (String) phone));
	}

}
