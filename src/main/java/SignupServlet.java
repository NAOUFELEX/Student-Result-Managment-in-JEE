
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3308/notes";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
		 // Get the entered username and password from the signup form
        
		String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
		String gender = request.getParameter("gender");

		String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Get the user type (student or teacher)
        String userType = request.getParameter("userType");

        // Add the user to the database
        if (addUser(firstname, lastname, gender, username, password, userType)) {
            response.sendRedirect("login.jsp?signupSuccess=true");
        } else {
            response.sendRedirect("signup.jsp?error=Failed to create account, Username already being used");

              
        }
    }

    private boolean addUser(String firstname, String lastname, String gender, String username, String password, String userType) {
        try {
            // Establish a database connection
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Determine the table based on the user type
            String tableName = (userType.equals("student")) ? "students" : "teachers";

            // Prepare the SQL query to insert a new user
            String query = "INSERT INTO " + tableName + " (firstname, lastname, gender, username, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.setString(3, gender);
            statement.setString(4, username);
            statement.setString(5, password);

            // Execute the query
            int rowsInserted = statement.executeUpdate();

            // Close the database resources
            statement.close();
            connection.close();

            return rowsInserted > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

}
