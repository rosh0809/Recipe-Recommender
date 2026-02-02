import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Call the DatabaseConnection class
        Connection connection = DatabaseConnection.getConnection();

        // Check if the connection was successful
        if (connection != null) {
            System.out.println("Connection Successful!");
        } else {
            System.out.println("Connection Failed!");
        }
    }
}
/*public class Main {
    public static void main(String[] args) {
        RecipeDAO dao = new RecipeDAO();

        // Test inserting a recipe
        Recipe r = new Recipe("Paneer Butter Masala", "Paneer, Butter, Masala", "Cook it well.");
        boolean success = dao.addRecipe(r);

        if (success) {
            System.out.println("✅ Recipe added successfully!");
        }

        // Test fetching and displaying recipes
        for (Recipe rec : dao.getAllRecipes()) {
            System.out.println("🍲 " + rec.getName());
            System.out.println("   Ingredients: " + rec.getIngredients());
            System.out.println("   Instructions: " + rec.getInstructions());
            System.out.println();
        }
    }
}*/

