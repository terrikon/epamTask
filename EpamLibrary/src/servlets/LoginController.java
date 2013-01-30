package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBase.User;
import dataBase.UserDaoLibrary;

public class LoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String login = req.getParameter("login");
		String pass = req.getParameter("pass");
		resp.setContentType("text/html");
		PrintWriter out= resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		User user=UserDaoLibrary.getUserByLogin("dima");
		System.out.println("adfkmfdkf");
		out.println("login is "+user.getLogin() + "  " + user.getPass());
		out.println("</head>");
		out.println("</html>");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
