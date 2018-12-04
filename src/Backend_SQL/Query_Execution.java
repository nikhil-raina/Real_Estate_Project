package Backend_SQL;

import java.sql.*;


public class Query_Execution {
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
