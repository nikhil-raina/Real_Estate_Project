package Backend_SQL;

import java.sql.*;

public class SQLBase {
    public static Connection getConnection() throws SQLException {
        return getConnection(null);
    }

    public static Connection getConnection(String schema) throws SQLException {
        // Read the password file
        // should be ~/.pgpass and look like
        // reddwarf.cs.rit.edu:5432:USERNAME:USERNAME:PASSWORD
        String content = "reddwarf.cs.rit.edu:5432:jbk2042:DunkinDonuts";

        String parts[] = content.split(":");
        String host = parts[0];
        String port = parts[1];
        String username = parts[2];
        String password = parts[3];

        String url = "jdbc:postgresql://" + host + ":" + port;
        if (schema != null) {
            url += "/?currentSchema=" + schema;
        }
        return DriverManager.getConnection(url, username, password);
    }

}
