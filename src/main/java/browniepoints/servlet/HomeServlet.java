package main.java.browniepoints.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.browniepoints.model.CompositeQuestion;
import main.java.browniepoints.model.helper.AttemptHelper;
import main.java.browniepoints.model.helper.CouponHelper;
import main.java.browniepoints.model.helper.QuestionHelper;
import main.java.browniepoints.model.helper.RestaurantHelper;
import main.java.browniepoints.model.helper.VoucherHelper;
import main.java.browniepoints.util.Util;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		AttemptHelper.getInstance();
		QuestionHelper.getInstance();
		CouponHelper.getInstance();
		VoucherHelper.getInstance();
		RestaurantHelper.getInstance();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
