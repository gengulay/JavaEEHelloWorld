package com.gengulay;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessInfo")
public class ProcessInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProcessInfo() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/DisplayInfo.jsp";
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String phone = request.getParameter("phone");

		updateDB(fname, lname, phone);

		Customer cust = new Customer(fname, lname, phone);

		request.setAttribute("cust", cust);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	protected void updateDB(String fName, String lName, String phone) {

		Connection con;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/test1";
			String username = "dbadmin";
			String pw = "turtledove";
			con = DriverManager.getConnection(url, username, pw);
			Statement s = con.createStatement();
			String query = "INSERT INTO customer " + "(first_name, last_name, phone, cust_id) " + "VALUES ('" + fName
					+ "', '" + lName + "', '" + phone + "', NULL)";
			s.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
