package Data_Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class SQLDataInserter extends SQLBase {
	public static void main(String args[]) throws FileNotFoundException {
		Connection con = null;
		ResultSet rs = null;
		try {
			con = SQLBase.getConnection("real_estate");
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new File(SQLDataInserter.class.getResource("FAKE_ADDRESS.csv").getFile()));

			int rowCount = 12500; // change based on how many rows you're processing from csv to sql.
			int rowIndex = 0;

			String nextLine = "";
			String nextElem = "";

			// IF YOU ARE IMPORTING A CSV WITH N COLUMNS PER ROW, YOU NEED TO HAVE N ?s
			// inside of the parenthesis below '
			//EXAMPLE: if N=3 (?,?,?)
			PreparedStatement ps = con.prepareStatement("INSERT INTO ADDRESS VALUES (?, ?, ?, ?, ?)");

			while (scanner.hasNextLine() && rowIndex < rowCount) {
				nextLine = scanner.nextLine();

				// scanner2 is used to split up the line grabbed by the first "scanner" scanner.
				@SuppressWarnings("resource")
				Scanner scanner2 = new Scanner(nextLine);
				scanner2.useDelimiter(",");

				while (scanner2.hasNext()) { // could've done scanner2.hasNext, but data in uniform, so we're good.
					nextElem = scanner2.next();
					System.out.println(nextElem);
					
					//one ps.set statement for each question mark in the initialization of the ps object above
					//index starts at 1 for whatever reason, error will happen if you start at 0.
					//setString for strings, setInt for ints, so on...
		
					ps.setString(1, nextElem);
					ps.setString(2, scanner2.next());
					ps.setString(3, scanner2.next());
					ps.setString(4, scanner2.next());
					ps.setInt(5, Integer.parseInt(scanner2.next()));

					if (scanner2.hasNext()) {
						System.out.println("There's an issue.");
						// scanner2 should have one next() (thus one ps.set call) call for each column
						// of the sql table
						// if it still has next after the ps.set calls, that means your datas not
						// correctly formatted in the .csv
						// or you don't have enough ps.set statements relative to the csv's columns per
						// row.
						return;
					}

					ps.execute();
					ps.clearParameters();
					// clearParameters not really needed (because our data doesn't have any holes)
					// but ensures if we did have a row without data for one column
					// the previous row's column won't just be repeated.
				}

				rowIndex++;
			}
			con.close();
		} catch (SQLException e) {

			System.err.println("Something went wrong. Error: " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.err.println("Something went REALLY wrong.");
			}
		}
	}

}
