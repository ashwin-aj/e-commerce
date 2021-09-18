package org.simplilearn.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.util.DbConnection;

/**
 * Servlet implementation class ProductDetails
 */
@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetails() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DbConnection conn = new DbConnection();
			
			String pId = request.getParameter("pid");
			
			Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rSet = stmt.executeQuery("select * from eproduct where pid="+pId);
			
			response.getWriter().print("<html><body>");
			
			if(rSet.next()) {
				response.getWriter().println("Product available."+ " <br> " );				
				response.getWriter().println("<TABLE BORDER><TR><TH>PRODUCT ID<TH>NAME<TH>PRICE<TH>DATE</TR>");
				response.getWriter().println("<TR ALIGN=RIGHT><TD>" + rSet.getInt(1) + "<TD>"+ rSet.getString(2) + "<TD>"+ rSet.getString(3)+ "<TD>" +rSet.getString(4));
			}else {
				response.getWriter().print("Product not available."); 
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
