package main.java.browniepoints.servlet;

import java.io.IOException;

import static main.java.browniepoints.util.Util.getInt;
import static main.java.browniepoints.util.Util.getLong;
import static main.java.browniepoints.util.Util.getString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.browniepoints.model.Question;
import main.java.browniepoints.model.helper.QuestionHelper;
import main.java.browniepoints.model.helper.RestaurantHelper;
import main.java.browniepoints.model.helper.UserHelper;

/**
 * Servlet implementation class AddQuestionServlet
 */
public class AddQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddQuestionServlet() {
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			QuestionHelper.getInstance().insert(
					new Question(UserHelper.getInstance().getLoggedInUid(),
							getString(request, "title"), getString(request,
									"desc"), getString(request, "trivia"),
							"img", getString(request, "url"), getString(
									request, "option1"), getString(request,
									"option2"), getString(request, "option3"),
							getString(request, "option4"), getString(request,
									"answer"), RestaurantHelper
									.getInstance()
									.getRestaurantByName(
											getString(request, "name"))
									.getRid(), getString(request, "cuisine"),
							0, // likes
							0, // played
							System.currentTimeMillis(), -1));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
