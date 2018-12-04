package UI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class SQLTest extends SQLBase {
    public static void main(String args[]) {
        Connection con = null;
        ResultSet rs = null;
        List<String> list;
//        list.add("name");
//        list.add("heelooo");
        try {
            con = getConnection("real_estate");
            rs = executeQuery(con, "SELECT * FROM address;");
            while (rs.next()) {
                // creating a list to add all the column values at that column.
                list = new ArrayList<>();
                list.add(rs.getString(1));
                list.add(rs.getString(2));
                list.add(rs.getString(3));
                list.add(rs.getString(4));
                list.add(rs.getString(5));
                System.out.println(list);
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
//        System.out.println(list);
    }
}
