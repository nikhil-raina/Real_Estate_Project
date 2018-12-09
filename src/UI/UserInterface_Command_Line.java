package UI;

import Backend_SQL.Query_Execution;
import Backend_SQL.SQLConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UserInterface_Command_Line {
	static int accessLevel = -1;
	static String input;
	static String input2;
	static Connection con = null;
	static ResultSet rs = null;
	final static String schema = "real_estate";

	// -1 is no access

	// String[] actions = {};
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println(" ________________________________________________________________");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("| Welcome to the Premium Real Estate Company database UI!        |");
		System.out.println("|----------------------------------------------------------------|");

		System.out.println("|    Do you have staff (admin/manager/agent) credentials?        |");
		agreement();
		input = s.nextLine();
		System.out.println();

		// limit of tries for password entry
		int count = 3;

		// generating the Staff ID. Maximum limit is 20000
		Random num = new Random();
		int staffID = num.nextInt(20000);

		if (input.equalsIgnoreCase("y")) {
			// String password = "maximum_mior";
			String password = "123";
			while (count != 0) {
				System.out.println("|----------------------------------------------------------------|");
				System.out.println("|     Please enter your staff credentials.                       |");
				System.out.println("|----------------------------------------------------------------|");

				String attemptedPassword = s.nextLine();
				if (attemptedPassword.equals(password)) {
					accessLevel = 9001;
					System.out.println();
					System.out.println("|-------------- Access has been granted --------------------|");
					System.out.println("|      Welcome staff member: 5795.                          |");
					System.out.println("| 	   Access Level: Agent                              |");
					System.out.println(" ___________________________________________________________");
					System.out.println("|      We hope you're having a profitable day!              |");
					System.out.println("|      Would you like to enter the staff menu?              |");

					agreement();
					input = s.nextLine();
					// System.out.println();

					if (input.equalsIgnoreCase("y")) {
						try {
							con = SQLConnection.getConnection(schema);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						staffMenu(con);
					} else {
						System.out.println();
						System.out.println(" ________________________________________________________________");
						System.out.println("|Thanks for using the Premium Real Estate Company database UI.  |");
						System.out.println("|Have a great day.                                              |");
						System.out.println(" ________________________________________________________________");

						System.exit(0);
					}

				}
				count--;
				System.out.println("Incorrect Password.");
				System.out.println("Number of tries left: " + count);
				System.out.println();
			}

			System.out.println("Number of tries exceeded...!!");
			System.out.println("UNAUTHORIZED ENTRY, REPORTING TO FRAUD DEPARTMENT!");
			System.out.println("Staff ID ::: " + staffID + " ::: has been reported.");
			System.out.println("System SHUT DOWN!");
			System.exit(0);

		} else {
			System.out.println(" ________________________________________________________________");
			System.out.println("| Access Level:      Customer                                    |");
			System.out.println("| Welcome!                                                       |");
			System.out.println("| Thanks for choosing Premium Real Estate!                       |");
			// System.out.println();

			while (true) {
				enterCustomerTaunt();
				input = s.nextLine();
				youSure();

				input2 = s.nextLine();
				if (input.equalsIgnoreCase("1") && input2.equalsIgnoreCase("y")) {
					try {
						con = SQLConnection.getConnection(schema);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					custMenu(con);
				} else if (input.equalsIgnoreCase("2") && input2.equalsIgnoreCase("y")) {
					switchOFF();
				}
			}
		}

	}

	// accessLevel
	// staff/mgr/admin = 9001
	// customer (read only) = -1
	public int getAccessLevel(String taxID) {
		return accessLevel;

	}

	static void switchOFF() {
		System.out.println(" ________________________________________________________________");
		System.out.println("|    Thanks for using Premium Real Estate UI!                    |");
		System.out.println("|________________________________________________________________|");
		System.exit(0);
	}

	static void enterCustomerTaunt() {
		System.out.println(" ________________________________________________________________ ");
		System.out.println("| Input Selection:                                               |");
		System.out.println("| (1) Customer Menu.                                             |");
		System.out.println("| (2) Exit.                                                      |");
		System.out.println(" ________________________________________________________________ ");

	}

	static boolean youSure() {
		System.out.println("Please confirm your input.");
		agreement();
		return false; //why Nikhil.. moved agreement into youSure, and even than, that's kinda..
	}

	static void printStaffMenu() {
		System.out.println(" ________________________________________________________________ ");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("| (1) View data.                                                 |");
		System.out.println("| (2) Insert data.                                               |");
		System.out.println("| (3) Update data.                                               |");
		System.out.println("| (4) Delete data.                                               |");
		System.out.println("| (5) Exit.                                                      |");
		System.out.println("|----------------------------------------------------------------|");


	}

	static void agreement() {
		System.out.println(" ________________________________________________________________ ");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("| Type 'y' for yes, or 'n' for no, then press enter.             |");
		System.out.println("|----------------------------------------------------------------|");
	}

	static void coolTextPrinter(String s) {
		//todo, make add ---- lines based on spacing.
		//no in use yet.
		System.out.println(" ________________________________________________________________ ");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("|"+ s + ""         );
		System.out.println("|----------------------------------------------------------------|");
	}

	static void staffMenu(Connection con) throws IOException, InterruptedException {
		String desiredAction;
		System.out.println(" ________________________________________________________________ ");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("| Welcome to the staff menu!                                     |");
		System.out.println("| What would you like to do today?:                              |");
		System.out.println("|________________________________________________________________|");


		printStaffMenu();

		desiredAction = s.nextLine();

		youSure();

		input = s.nextLine();
		System.out.println();

		// if(1 <= Integer.parseInt(desiredAction) && Integer.parseInt(desiredAction) <=
		// 6){
		while (input.equalsIgnoreCase("n") || !(1 <= Integer.parseInt(desiredAction))
				|| !(Integer.parseInt(desiredAction) <= 6)) {
			System.out.println("Choose again:");
			printStaffMenu();
			desiredAction = s.nextLine();
			youSure();
			input = s.nextLine();
			System.out.println();
		}

		if (desiredAction.equalsIgnoreCase("1")) {
			System.out.println(" ________________________________________________________________ ");
			System.out.println("|----------------------------------------------------------------|");
			System.out.println("|Where would you like to (V)iew the data from:                   |");
			// View the different tables available in the database.
			// Have a function call viewData()
			viewData(con);
		} else if (desiredAction.equalsIgnoreCase("2")) {
			System.out.println("|Where would you like to (I)nsert the data:                      |");
			// View the different tables available in the database to INSERT data.
			// Have a function call insertData()
			insertData(con);
		} else if (desiredAction.equalsIgnoreCase("3")) {
			System.out.println("|Where would you like to (U)pdate the data:                      |");
			// View the different tables available in the database to UPDATE some data.
			// Have a function call updateData()
		} else if (desiredAction.equalsIgnoreCase("4")) {
			System.out.println("|Where would you like to (D)elete the data:                      |");
			// View the different tables available in the database to Delete data.
			// Have a function call deleteData()
		} else if (desiredAction.equalsIgnoreCase("5")) {
			System.out.println("|We're happy to have been here to help you do your job!          |");
			System.out.println("|If you need anything else, be sure to use our tool again later! |");
			while (true) {
				System.out.println();
					enterCustomerTaunt();
				System.out.println("|If you want, you can return to the Staff Menu (3).              |");
				input = s.nextLine();
				youSure();

				input2 = s.nextLine();
				if (input.equalsIgnoreCase("1") && input2.equalsIgnoreCase("y")) {
					custMenu(con);
				} else if (input.equalsIgnoreCase("2") && input2.equalsIgnoreCase("y")) {
					switchOFF();
				} else if (input.equalsIgnoreCase("3") && input2.equalsIgnoreCase("y")) {
					staffMenu(con);
				}
			}
		} else if (desiredAction.equalsIgnoreCase("6")) {
			switchOFF();
		}
	}

	static void viewData(Connection con) {
		int count = 0;
		List<String> list = new ArrayList<>();
		try {
			rs = Query_Execution.executeQuery(con, "SELECT * FROM pg_tables WHERE schemaname='" + schema + "'");
			System.out.println("|         --- Tables Present ---                             |");

			while (rs.next()) {
				count++;
				String val = rs.getString("tablename");
				list.add(val);
				System.out.println("|\t(" + count + ")\t" + val.toUpperCase());
			}
			System.out.println("|    Enter the table number to view:                         |");

			String table;
			while (true) {
				input = s.nextLine();
				int result = Integer.parseInt(input);
				if (1 <= result && result <= count) {
					table = list.get(result - 1);
					rs = Query_Execution.executeQuery(con, "SELECT * FROM " + table);
					break;
				} else
					System.out.println("Wrong Input.\nTry again\n");
			}
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			String columnCount[] = new String[columnsNumber + 1];
			String align = "";
			for (int i = 1; i <= columnsNumber; i++) {
				columnCount[i - 1] = rsmd.getColumnName(i).toUpperCase();
				align = align + "%20s|";
			}
			System.out.format(align + "\n", columnCount);
			System.out.println();
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					columnCount[i - 1] = rs.getString(i);
				}
				System.out.format(align + "\n", columnCount);
			}
			System.exit(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void custMenu(Connection con) throws IOException, InterruptedException {
		String desiredAction = "";

		System.out.println(" ________________________________________________________________ ");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("| Welcome to the customer menu!                                  |");
		System.out.println(" ________________________________________________________________ ");
		System.out.println("| Just enter a command, or input some data to search:            |");
		System.out.println("|________________________________________________________________|");
		custCommandList(); // will list out all available commands for customers.

		desiredAction = s.nextLine();
		System.out.println();

		System.out.println("You're trying to " + desiredAction + ".");
		System.out.println("Is this correct?");
		agreement();
		if (s.nextLine().equals("y")) {
			System.out.println("\nAwesome! \nI will get that ready for you as soon as possible!");

		}

		else {
			System.out.println("Well, would you like to try again?");
			agreement();
			if (s.nextLine().equals("y")) {
				custMenu(con); // is this recursion?
			} else {
				System.out.println("\n\n We're always happy to help you!\nIf you need anything else, be "
						+ "sure to use our tool again later!");
			}

		}
	}

	static void custCommandList() {
		boolean this_will_look_awful_on_the_powerpoint = true;
		//TODO, make this_will_look_awful_on_the_powerpoint = false.

		while(!this_will_look_awful_on_the_powerpoint) {
		System.out.println(" ________________________________________________________________ ");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("| (1) View data.                                                 |");
		System.out.println("|                                                                |");
		System.out.println("|                                                                |");
		System.out.println("|----------------------------------------------------------------|");
		}

	}

	static int chooseInsertList() {
		boolean valid = false;
		int choice = 0;
		System.out.println(" ________________________________________________________________ ");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("| (1) New Property.                                              |");
		System.out.println("| (2) New Client.                                                |");
		System.out.println("| (3) New Agent.                                                 |");
		System.out.println("| (4) New Offer.                                                 |");
		System.out.println("| (5) New Office.                                                |");
		System.out.println("|----------------------------------------------------------------|");
		// should be added at the same time as office to keep 1:1 rule
		// System.out.println("(4) New Manager");

		while (valid == false) {
			choice = s.nextInt();
			if (choice < 6 && choice > 0)
				valid = true;
			else {
		System.out.println("| Invalid value entered. Please enter again.                      |");
			}
		}
		return choice;
	}

	static void insertData(Connection con) {
		String sql = "";
		System.out.println("What would you like to insert?");
		int insertType = chooseInsertList();
		switch (insertType) {
		case 1:
			// Insert new property
			insertNewProp(con);
		case 2:
			// Insert new client
		case 3:
			// Insert new agent
		case 4:
			// Insert new offer
		case 5:
			// Insert new office
		default:
			// something has gone wrong...
		}
	}

	static void insertNewProp(Connection con) {
		s.nextLine();

		System.out.print("Enter the tax id of the new property's listed agent: ");
		String agentID = s.nextLine();

		System.out.print("Enter the tax id of the property's seller: ");
		String sellerID = s.nextLine();

		System.out.print("Enter the listing price: ");
		int listPrice = s.nextInt();

		s.nextLine();
		System.out.print("Enter the property's description: ");
		String desc = s.nextLine();

		System.out.print("Enter the property's area (in square feet): ");
		int area = s.nextInt();

		s.nextLine();
		System.out.print("Enter the property's address: ");
		String address = s.nextLine();

		System.out.print("Enter the property's city: ");
		String city = s.nextLine();

		System.out.print("Enter the property's state abbreviation: ");
		String state = s.nextLine();

		System.out.print("Enter the property's ZIP code: ");
		String zip = s.nextLine();

		try {
			int addressid = 0;

			rs = Query_Execution.executeQuery(con, "SELECT MAX(addressid)+1 AS newID FROM address;");
			if (rs.next())
				addressid = rs.getInt("newID");

			PreparedStatement addressPS = con.prepareStatement("INSERT INTO address VALUES(?, ?, ?, ?, ?);");
			addressPS.setString(1, address);
			addressPS.setString(2, city);
			addressPS.setString(3, state);
			addressPS.setString(4, zip);
			addressPS.setInt(5, addressid);
			addressPS.execute();

			int propertyid = 0;

			rs = Query_Execution.executeQuery(con, "SELECT MAX(propertyid)+1 FROM property;");
			if (rs.next()) {
				propertyid = rs.getInt(1);
			}

			PreparedStatement propPS = con.prepareStatement("INSERT INTO property VALUES(?, ?, ?, ?, ?, ?, ?, ?);");

			propPS.setInt(1, propertyid);
			propPS.setString(2, agentID);
			propPS.setString(3, sellerID);
			propPS.setInt(4, listPrice);
			propPS.setString(5, desc);
			propPS.setInt(6, area);
			propPS.setInt(7, addressid);
			propPS.setDate(8, null);
			propPS.execute();

			System.out.println("Property " + propertyid + " inserted!");
		} catch (SQLException e) {
			System.err.println("Could not insert new property.");
			System.out.println("Error: " + e.getMessage());
		}
	}
}
