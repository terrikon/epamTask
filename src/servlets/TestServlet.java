package servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBase.DaoLibrary;
import dataBase.User;
import dataBase.UserDaoLibrary;

public class TestServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try{
			Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/Library?"
						+ "user=root");
		Statement s = c.createStatement();
		
		ResultSet r = s.executeQuery("select * from User where login =dima; ");
		while (r.next()) {
			out.println(r.getObject("login"));
		}
		resp.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");

		out.println(c);

		out.println("</head>");
		out.println("</html>");
		}catch(SQLException e){
			out.println(e);
		}catch(ClassNotFoundException e){
			
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}