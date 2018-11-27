
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

class SQLTest extends SQLBase {
    public static void main(String args[]) {
        Connection con = null;
        ResultSet rs = null;
        try {
            con = SQLBase.getConnection("real_estate");
            rs = SQLBase.executeQuery(con, "SELECT * FROM address;");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Something went wrong.");
        } finally {
            try {
                if (rs != null) { rs.close(); }
            } catch (SQLException e) {
                System.err.println("Something went REALLY wrong.");
            }
        }
    }
}
