


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class ViewStudentsServlet
 */
public class ViewStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3308/notes";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewStudentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	        PrintWriter pw = response.getWriter();

	        PrintWriter out = response.getWriter();
	        try {
	            Class.forName(JDBC_DRIVER);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	            String query = "SELECT * FROM students";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            ResultSet rs = stmt.executeQuery();

	            out.println("<html><head><title>View Students</title>");
	           
	            out.println("<link rel='stylesheet' type='text/css' href='css/ViewStudentsServlet.css'>");

	            out.println("</head><body>");
	            out.println("<h1>View Students</h1>");
	            out.println("<table>");
	            out.println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Gender</th><th>Username</th></tr>");
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String firstName = rs.getString("firstname");
	                String lastName = rs.getString("lastname");
	                String gender = rs.getString("gender");
	                String username = rs.getString("username");

	                out.println("<tr><td>" + id + "</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" + gender + "</td><td>" + username + "</td></tr>");
	            }

	            out.println("</table>");
	            out.println("</body></html>");
	            rs.close();
	            stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
