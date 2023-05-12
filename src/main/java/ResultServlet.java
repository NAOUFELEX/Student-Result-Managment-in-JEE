


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/result")
 
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3308/notes";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentId = request.getParameter("studentId");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String query = "SELECT course_name, result FROM results WHERE student_id = '1'";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html><head><title>View Result</title>");
            out.println("<link rel='stylesheet' type='text/css' href='css/ViewStudentsServlet.css'>");
            out.println("</head><body>");

            if (rs.next()) {
            	out.println("<h2>Welcome, Student!</h2>");
            	out.println("<p>This is your dashboard.</p>");

                out.println("<h1>Student Result</h1>");
                out.println("<table>");
                out.println("<tr><th>Course Name</th><th>Result</th></tr>");

                do {
                    String courseName = rs.getString("course_name");
                    double result = rs.getDouble("result");

                    out.println("<tr><td>" + courseName + "</td><td>" + result + "</td></tr>");
                } while (rs.next());

                out.println("</table>");
            } 

            out.println("</body>");
            out.println("</html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
