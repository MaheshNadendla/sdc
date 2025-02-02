import java.sql.*;

public class CRUDExample {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try {
            // Step 1: Establishing a connection
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME,
                    PASSWORD);
            // Step 2: Creating a statement
            Statement statement = connection.createStatement();
            // Step 3: Performing CRUD operations
            createRecord(statement, "John Doe", 50000);
            readRecords(statement);
            updateRecord(statement, 1, "John Updated", 55000);
            readRecords(statement);
            deleteRecord(statement, 1);
            readRecords(statement);
            // Step 4: Closing resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create a new record in the database
    private static void createRecord(Statement statement, String name, int salary) throws SQLException {
        String insertQuery = "INSERT INTO employees (name, salary) VALUES ('" + name + "', " + salary
                + ")";
        statement.executeUpdate(insertQuery);
        System.out.println("Record created successfully.");
    }

    // Read all records from the database
    private static void readRecords(Statement statement) throws SQLException {
        String selectQuery = "SELECT * FROM employees";
        ResultSet resultSet = statement.executeQuery(selectQuery);
        System.out.println("ID\tName\tSalary");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            System.out.println(id + "\t" + name + "\t" + salary);
        }
        System.out.println();
    }

    // Update a record in the database
    private static void updateRecord(Statement statement, int id, String newName, int newSalary)
            throws SQLException {
        String updateQuery = "UPDATE employees SET name = '" + newName + "', salary = " +
                newSalary + " WHERE id = " + id;
        statement.executeUpdate(updateQuery);
        System.out.println("Record updated successfully.");
    }

    // Delete a record from the database
    private static void deleteRecord(Statement statement, int id) throws SQLException {
        String deleteQuery = "DELETE FROM employees WHERE id = " + id;
        statement.executeUpdate(deleteQuery);
        System.out.println("Record deleted successfully.");
    }
}