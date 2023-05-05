import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class EditDeleteStudentServlet
 */
public class EditDeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3308/notes";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        int studentId = Integer.parseInt(request.getParameter("studentId"));
	        String action = request.getParameter("action");

	        try {
	            Class.forName(JDBC_DRIVER);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	            if (action.equals("edit")) {
	                String firstName = request.getParameter("firstName");
	                String lastName = request.getParameter("lastName");
	                String gender = request.getParameter("gender");
	                String username = request.getParameter("username");

	                String query = "UPDATE students SET firstname = ?, lastname = ?, gender = ?, username = ? WHERE id = ?";
	                PreparedStatement stmt = conn.prepareStatement(query);
	                stmt.setString(1, firstName);
	                stmt.setString(2, lastName);
	                stmt.setString(3, gender);
	                stmt.setString(4, username);
	                stmt.setInt(5, studentId);
	                int rowsAffected = stmt.executeUpdate();

	                if (rowsAffected > 0) {
	                    out.println("<html><head><title>Edit Student</title></head><body>");
	                    out.println("<h1>Student updated successfully.</h1>");
	                    out.println("<a href=\"viewStudents\">Go back to View Students</a>");
	                    out.println("</body></html>");
	                } else {
	                    out.println("<html><head><title>Edit Student</title></head><body>");
	                    out.println("<h1>Failed to update student.</h1>");
	                    out.println("<a href=\"viewStudents\">Go back to View Students</a>");
	                    out.println("</body></html>");
	                }

	                stmt.close();
	            } else if (action.equals("delete")) {
	                String query = "DELETE FROM students WHERE id = ?";
	                PreparedStatement stmt = conn.prepareStatement(query);
	                stmt.setInt(1, studentId);
	                int rowsAffected = stmt.executeUpdate();

	                if (rowsAffected > 0) {
	                    out.println("<html><head><title>Delete Student</title></head><body>");
	                    out.println("<h1>Student deleted successfully.</h1>");
	                    out.println("<a href=\"viewStudents\">Go back to View Students</a>");
	                    out.println("</body></html>");
	                } else {
	                    out.println("<html><head><title>Delete Student</title></head><body>");
	                    out.println("<h1>Failed to delete student.</h1>");
	                    out.println("<a href=\"viewStudents\">Go back to View Students</a>");
	                    out.println("</body></html>");
	                }

	                stmt.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

}

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class EditDeleteStudentServlet
 */
public class EditDeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3308/notes";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        int studentId = Integer.parseInt(request.getParameter("studentId"));
	        String action = request.getParameter("action");

	        try {
	            Class.forName(JDBC_DRIVER);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	            if (action.equals("edit")) {
	                String firstName = request.getParameter("firstName");
	                String lastName = request.getParameter("lastName");
	                String gender = request.getParameter("gender");
	                String username = request.getParameter("username");

	                String query = "UPDATE students SET firstname = ?, lastname = ?, gender = ?, username = ? WHERE id = ?";
	                PreparedStatement stmt = conn.prepareStatement(query);
	                stmt.setString(1, firstName);
	                stmt.setString(2, lastName);
	                stmt.setString(3, gender);
	                stmt.setString(4, username);
	                stmt.setInt(5, studentId);
	                int rowsAffected = stmt.executeUpdate();

	                if (rowsAffected > 0) {
	                    out.println("<html><head><title>Edit Student</title></head><body>");
	                    out.println("<h1>Student updated successfully.</h1>");
	                    out.println("<a href=\"viewStudents\">Go back to View Students</a>");
	                    out.println("</body></html>");
	                } else {
	                    out.println("<html><head><title>Edit Student</title></head><body>");
	                    out.println("<h1>Failed to update student.</h1>");
	                    out.println("<a href=\"viewStudents\">Go back to View Students</a>");
	                    out.println("</body></html>");
	                }

	                stmt.close();
	            } else if (action.equals("delete")) {
	                String query = "DELETE FROM students WHERE id = ?";
	                PreparedStatement stmt = conn.prepareStatement(query);
	                stmt.setInt(1, studentId);
	                int rowsAffected = stmt.executeUpdate();

	                if (rowsAffected > 0) {
	                    out.println("<html><head><title>Delete Student</title></head><body>");
	                    out.println("<h1>Student deleted successfully.</h1>");
	                    out.println("<a href=\"viewStudents\">Go back to View Students</a>");
	                    out.println("</body></html>");
	                } else {
	                    out.println("<html><head><title>Delete Student</title></head><body>");
	                    out.println("<h1>Failed to delete student.</h1>");
	                    out.println("<a href=\"viewStudents\">Go back to View Students</a>");
	                    out.println("</body></html>");
	                }

	                stmt.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

}
