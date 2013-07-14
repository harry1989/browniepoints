package main.java.browniepoints.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.browniepoints.model.User;
import main.java.browniepoints.model.helper.UserHelper;

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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer uid = UserHelper.getInstance().getLoggedInUid();
		String email = UserHelper.getInstance().getUser(uid).getEmail();
		Object name = request.getAttribute("name");
		Object phone = request.getAttribute("phone");

		UserHelper.getInstance().update(
				new User(uid, email, name == null ? "" : (String) name,
						 0, phone == null ? "" : (String) phone));
	}

}
