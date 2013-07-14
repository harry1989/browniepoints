package main.java.browniepoints.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.browniepoints.model.Attempt;
import main.java.browniepoints.model.CompositeQuestion;
import main.java.browniepoints.model.User;
import main.java.browniepoints.model.helper.AttemptHelper;
import main.java.browniepoints.model.helper.QuestionHelper;
import main.java.browniepoints.model.helper.UserHelper;
import main.java.browniepoints.util.Util;

class Profile {
	private User user;
	Map<String, List<CompositeQuestion>> ret = new HashMap<String, List<CompositeQuestion>>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, List<CompositeQuestion>> getRet() {
		return ret;
	}

	public void setRet(Map<String, List<CompositeQuestion>> ret) {
		this.ret = ret;
	}
}

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

		Integer uid = UserHelper.getInstance().getLoggedInUid();

		Map<String, List<CompositeQuestion>> m = new HashMap<String, List<CompositeQuestion>>();
		List<CompositeQuestion> lst = QuestionHelper.getInstance()
				.getQuestionsForUser(uid);
		List<CompositeQuestion> fullLst = QuestionHelper.getInstance()
				.getAllQuestions();
		List<Attempt> attempts = AttemptHelper.getInstance().getAttempt(uid);
		Set<Integer> qids = new HashSet<Integer>();
		for (Attempt a : attempts) {
			qids.add(a.getQid());
		}

		m.put("my_q", lst);

		for (CompositeQuestion q : fullLst) {
			if (qids.contains(q.getQ().getQid())) {
				Util.addToMap(m, "my_a", q);
			}
		}

		Profile ret = new Profile();
		ret.setRet(m);
		ret.setUser(UserHelper.getInstance().getUser(uid));

		Util.convertToJSON(ret, response);
	}

//	public static void test() throws IOException {
//		Integer uid = 1;
//
//		Map<String, List<CompositeQuestion>> m = new HashMap<String, List<CompositeQuestion>>();
//		List<CompositeQuestion> lst = QuestionHelper.getInstance()
//				.getQuestionsForUser(uid);
//		List<CompositeQuestion> fullLst = QuestionHelper.getInstance()
//				.getAllQuestions();
//		List<Attempt> attempts = AttemptHelper.getInstance().getAttempt(uid);
//		Set<Integer> qids = new HashSet<Integer>();
//		for (Attempt a : attempts) {
//			qids.add(a.getQid());
//		}
//
//		m.put("my_q", lst);
//
//		for (CompositeQuestion q : fullLst) {
//			if (qids.contains(q.getQ().getQid())) {
//				Util.addToMap(m, "my_a", q);
//			}
//		}
//
//		Profile ret = new Profile();
//		ret.setRet(m);
//		ret.setUser(UserHelper.getInstance().getUser(uid));
//
//		System.out.println(Util.convertToJSON(ret));
//	}

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

	public static void main(String[] args) throws IOException {
		ProfileServlet obj = new ProfileServlet();
//		 obj.test();
	}

}
