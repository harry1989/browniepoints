package main.java.browniepoints.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import main.java.browniepoints.model.CompositeQuestion;
import main.java.browniepoints.model.helper.QuestionHelper;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CompositeQuestion> questions =
				QuestionHelper.getInstance().getRandomQuestions();
		if (null == questions || questions.size() == 0) return;
		
		Util.convertToJSON(questions, response);
	}

}
