package main.java.browniepoints.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.browniepoints.model.CompositeQuestion;
import main.java.browniepoints.model.helper.QuestionHelper;
import main.java.browniepoints.model.helper.UserHelper;
import main.java.browniepoints.util.Util;

/**
 * Servlet implementation class AnswerServlet
 */
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnswerServlet() {
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
		String qid = request.getParameter("qid");
		String answer = request.getParameter("a");

		System.out.println(qid + ", " + answer);

		if (Util.isNullOrEmpty(qid) || Util.isNullOrEmpty(answer)) {
			request.setAttribute("response", null);
			return;
		}

		CompositeQuestion ret = null;
		try {
			ret = QuestionHelper.getInstance().evaluateAnswer(
					UserHelper.getInstance().getLoggedInUid(),
					Integer.parseInt(qid), answer);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Util.convertToJSON(ret, response);
	}

}
