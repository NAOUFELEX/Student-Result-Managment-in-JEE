import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/login")

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3308/notes";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		// Get the entered username and password from the login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authenticateStudent(username, password)) {
            // If the user is a student, redirect to the student dashboard
        	response.sendRedirect("student_dashboard.jsp");
        } else if (authenticateTeacher(username, password)) {
            // If the user is a teacher, redirect to the teacher dashboard
            response.sendRedirect("teacher_dashboard.jsp");
        } else {
            // If the entered credentials are invalid, redirect back to the login page with an error message
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }

    private boolean authenticateStudent(String username, String password) {
        try {
            // Establish a database connection
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare the SQL query to check if the student credentials are valid
            String query = "SELECT * FROM students WHERE username=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if a matching student record was found
            boolean isValid = resultSet.next();

            // Close the database resources
            resultSet.close();
            statement.close();
            connection.close();

            return isValid;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean authenticateTeacher(String username, String password) {
        try {
            // Establish a database connection
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare the SQL query to check if the teacher credentials are valid
            String query = "SELECT * FROM teachers WHERE username=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if a matching teacher record was found
            boolean isValid = resultSet.next();

            // Close the database resources
            resultSet.close();
            statement.close();
            connection.close();

            return isValid;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }

	}
}
