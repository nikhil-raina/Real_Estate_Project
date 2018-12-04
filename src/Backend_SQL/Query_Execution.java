package Backend_SQL;

import java.sql.*;


public class Query_Execution {
    /**
     * SELECT QUERY
     *
     * Creates a query that gets executed and returns a ResultSet holding the entire table that has been requested for.
     *
     * @param con DriverManager Connection passed on after getting the connection from the respected database.
     *            In this case, the connection is to the database 'real_estate'
     * @param sql The SELECT Command being executed.
     * @return ResultSet holding the entire table data.
     */
    public static ResultSet executeQuery(Connection con, String sql) {
        Statement stmt;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            stmt.closeOnCompletion();
        } catch (SQLException e) {
            System.err.println("Something went wrong.");
        }
        return rs;
    }

}
