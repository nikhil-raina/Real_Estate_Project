package Backend_SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class SQLTest{
    public static void main(String args[]) {
        Connection con;
        ResultSet rs = null;
        List<String> list;
        try {
            con = SQLConnection.getConnection("real_estate");
            rs = Query_Execution.executeQuery(con, "SELECT * FROM address");
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnsNumber = rsmd.getColumnCount();
            System.out.println(rsmd.getColumnName(1));
            System.out.println(columnsNumber);
            while (rs.next()) {

                // creating a list to add all the column values at that column.
                // string, column header names or column position number, starting from 1, can be used in order to
                // get the required columns.
                list = new ArrayList<>();
                list.add(rs.getString(1));
                list.add(rs.getString(2));
                list.add(rs.getString(3));
                list.add(rs.getString(4));
                list.add(rs.getString(5));
                System.out.println(list);
//                System.out.println(rs.getString("tablename").toUpperCase());
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
