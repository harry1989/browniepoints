package main.java.browniepoints.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import main.java.browniepoints.model.CompositeQuestion;
import main.java.browniepoints.model.helper.AttemptHelper;
import main.java.browniepoints.model.helper.CouponHelper;
import main.java.browniepoints.model.helper.QuestionHelper;
import main.java.browniepoints.model.helper.UserHelper;
import main.java.browniepoints.model.helper.VoucherHelper;
import main.java.browniepoints.util.Util;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		QuestionHelper.getInstance();
		CouponHelper.getInstance();
		VoucherHelper.getInstance();
		AttemptHelper.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<CompositeQuestion> questions = QuestionHelper.getInstance()
				.getRandomQuestions();
		if (null == questions || questions.size() == 0)
			return;

		List<CompositeQuestion> ret = new ArrayList<CompositeQuestion>();
		try {
			for (CompositeQuestion q : questions) {
				ret.add(Util.toNonRevealMode(q));
			}

			request.setAttribute("questions", Util.convertToJSON(ret));
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().println(e.getMessage());
		}

		getServletContext().getRequestDispatcher("/index.jsp").forward(request,
				response);
	}
}
