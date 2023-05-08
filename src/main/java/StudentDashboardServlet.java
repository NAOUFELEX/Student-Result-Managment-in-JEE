
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class StudentDashboardServlet
 */
@WebServlet("/studentDashboard")

public class StudentDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3308/notes";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String studentId = (String) session.getAttribute("studentId");

        if (studentId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT * FROM results WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            out.println("<html><head><title>Student Dashboard</title></head><body>");
            out.println("<h1>Welcome, Student " + studentId + "</h1>");
            out.println("<h2>View Your Results</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Subject</th><th>Score</th></tr>");

            while (rs.next()) {
                String subject = rs.getString("subject");
                int score = rs.getInt("score");

                out.println("<tr><td>" + subject + "</td><td>" + score + "</td></tr>");
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
