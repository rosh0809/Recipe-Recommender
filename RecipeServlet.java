import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/searchRecipe")
public class RecipeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dish = request.getParameter("dish");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // HTML start
        out.println("<html><head><title>Recipe Results</title>");
        out.println("<link rel='stylesheet' type='text/css' href='style.css'>");
        out.println("</head><body>");

        out.println("<nav>");
        out.println("<a href='index.jsp'>Home</a>");
        out.println("<a href='recipe.jsp'>Search Recipes</a>");
        out.println("<a href='addRecipe.jsp'>Add Recipe</a>");
        out.println("<a href='allRecipes.jsp'>All Recipes</a>");
        out.println("</nav><hr>");

        out.println("<h2>Search Results for: <i>" + dish + "</i></h2>");

        if (dish == null || dish.trim().isEmpty()) {
            out.println("<p>Please enter a dish name.</p>");
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/recipes_db", "root", "roshni08092005");

                String sql = "SELECT * FROM recipes WHERE name LIKE ? OR ingredients LIKE ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + dish + "%");
                ps.setString(2, "%" + dish + "%");
                ResultSet rs = ps.executeQuery();

                boolean found = false;
                while (rs.next()) {
                    found = true;
                    out.println("<div class='recipe-box'>");
                    out.println("<h3>" + rs.getString("name") + "</h3>");
                    out.println("<p><strong>Ingredients:</strong> " + rs.getString("ingredients") + "</p>");
                    out.println("<p><strong>Instructions:</strong> " + rs.getString("instructions") + "</p>");
                    out.println("</div><br>");
                }

                if (!found) {
                    out.println("<p>No matching recipes found.</p>");
                }

                con.close();
            } catch (Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            }
        }

        out.println("</body></html>");
    }
}
