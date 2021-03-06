package main.java.browniepoints.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import main.java.browniepoints.model.CompositeQuestion;
import main.java.browniepoints.model.User;
import main.java.browniepoints.model.helper.AttemptHelper;
import main.java.browniepoints.model.helper.CouponHelper;
import main.java.browniepoints.model.helper.QuestionHelper;
import main.java.browniepoints.model.helper.UserHelper;
import main.java.browniepoints.model.helper.VoucherHelper;
import main.java.browniepoints.util.Util;

import org.expressme.openid.Association;
import org.expressme.openid.Authentication;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdException;
import org.expressme.openid.OpenIdManager;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String APP_URL = "http://gentle-badlands-2040.herokuapp.com/"; 

	private OpenIdManager manager = null;

	static final long ONE_HOUR = 3600000L;
	static final long TWO_HOUR = ONE_HOUR * 2L;
	static final String ATTR_MAC = "openid_mac";
	static final String ATTR_ALIAS = "openid_alias";
	

	private Integer uid = null;

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
		manager = new OpenIdManager();
		manager.setRealm(APP_URL);
		manager.setReturnTo(APP_URL + "/login");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if (!Util.isNullOrEmpty(op)) {
			Endpoint endpoint = manager.lookupEndpoint(op);
			Association association = manager.lookupAssociation(endpoint);
			request.getSession().setAttribute(ATTR_MAC,
					association.getRawMacKey());
			request.getSession().setAttribute(ATTR_ALIAS, endpoint.getAlias());
			String url = manager.getAuthenticationUrl(endpoint, association);
			System.out.println(url);
			response.sendRedirect(url);
			return;
		}

		Endpoint endpoint = manager.lookupEndpoint("Yahoo");
		Association association = manager.lookupAssociation(endpoint);
		Authentication authentication = manager.getAuthentication(request,
				association.getRawMacKey());
		// System.out.println(authentication);
		String email = request.getParameter("openid.ax.value.email");
		String identity = request.getParameter("openid.identity");

		System.out.println(email);
		System.out.println(identity);

		boolean userLoggedIn = !Util.isNullOrEmpty(email)
				&& !Util.isNullOrEmpty(identity);

		if (userLoggedIn && UserHelper.getInstance().getUser(email) == null) {
			// new user sign up. Take him to profile page after inserting a row
			// in the user table.
			UserHelper.getInstance().insert(
					new User(email, email.substring(0, email.indexOf('@')), 0,
							""));

			Integer uid = UserHelper.getInstance().getUser(email).getUid();
			request.setAttribute("uid", uid);
			if (userLoggedIn) {
				UserHelper.getInstance().setLoggedInUid(uid);
			}

			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("name",
					email.substring(0, email.indexOf('@')));
			getServletContext().getRequestDispatcher("/profile.jsp").forward(
					request, response);
			System.out.println("new user");
			return;
		}

		Integer uid = UserHelper.getInstance().getUser(email).getUid();
		if (userLoggedIn) {
			UserHelper.getInstance().setLoggedInUid(uid);
		}
		request.getSession().setAttribute("email", email);
		request.getSession().setAttribute("name",
				UserHelper.getInstance().getUser(uid).getName());

		List<CompositeQuestion> questions = userLoggedIn ? QuestionHelper
				.getInstance().getQuestionsForUser(this.uid) : QuestionHelper
				.getInstance().getRandomQuestions();
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

	void checkNonce(String nonce) {
		// check response_nonce to prevent replay-attack:
		if (nonce == null || nonce.length() < 20)
			throw new OpenIdException("Verify failed.");
		long nonceTime = getNonceTime(nonce);
		long diff = System.currentTimeMillis() - nonceTime;
		if (diff < 0)
			diff = (-diff);
		if (diff > ONE_HOUR)
			throw new OpenIdException("Bad nonce time.");
		if (isNonceExist(nonce))
			throw new OpenIdException("Verify nonce failed.");
		storeNonce(nonce, nonceTime + TWO_HOUR);
	}

	boolean isNonceExist(String nonce) {
		// TODO: check if nonce is exist in database:
		return false;
	}

	void storeNonce(String nonce, long expires) {
		// TODO: store nonce in database:
	}

	long getNonceTime(String nonce) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(
					nonce.substring(0, 19) + "+0000").getTime();
		} catch (ParseException e) {
			throw new OpenIdException("Bad nonce time.");
		}
	}

}
