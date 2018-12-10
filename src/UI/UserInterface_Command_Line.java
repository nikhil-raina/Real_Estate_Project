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
	private static int accessLevel = -1;
	private static String input;
	private static String input2;
	private static Connection con = null;
	private static ResultSet rs = null;
	private final static String schema = "real_estate";

	// -1 is no access

	// String[] actions = {};
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException {
//        try {
//            con = SQLConnection.getConnection(schema);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        viewData(con);

		System.out.println(" ________________________________________________________________");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("|    Welcome to the Premium Real Estate Company database UI!     |");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("|     Do you have staff (admin/manager/agent) credentials?       |");
//        youSure();
		agreement();
		input = s.nextLine();
//        System.out.println();

		// limit of tries for password entry
		int count = 3;

		// generating the Staff ID. Maximum limit is 20000
		Random num = new Random();
		int staffID = num.nextInt(20000);

		if (input.equalsIgnoreCase("y")) {
//            String password = "maximum_mior";
			String password = "123";
			while (count != 0) {
				System.out.println("|----------------------------------------------------------------|");
				System.out.println("|     Please enter your staff credentials.                       |");
				System.out.println("|----------------------------------------------------------------|");
				System.out.print("      ");

//                System.out.println("Please enter your administration credentials!");
				String attemptedPassword = s.nextLine();
				if (attemptedPassword.equals(password)) {
					accessLevel = 9001;
					System.out.println("|                                                                |");
					System.out.println("|-------------- Access has been granted -------------------------|");
					System.out.println("|     Welcome staff member: " + staffID + "                                |");
					System.out.println("|     Access Level: Agent                                        |");
					System.out.println("|                                                                |");
					System.out.println("|                                                                |");
					System.out.println("|----------------------------------------------------------------|");
					System.out.println("|      We hope you're having a profitable day!                   |");
					System.out.println("|      Would you like to enter the staff menu?                   |");
					agreement();
					input = s.nextLine();
//                    System.out.println();

					if (input.equalsIgnoreCase("y")) {
						try {
							con = SQLConnection.getConnection(schema);
							staffMenu(con);
						} catch (SQLException e) {
							e.printStackTrace();
						}
//                        staffMenu(con);
					} else {
//                        System.out.println();
//                        System.out.println(" ________________________________________________________________");
						System.out.println("|  Thanks for using the Premium Real Estate Company database UI. |");
						System.out.println("|  Have a great day.                                             |");
						System.out.println("|----------------------------------------------------------------|");
						System.out.println(" ________________________________________________________________ ");
						System.exit(0);
					}

				}
				count--;
//                System.out.println(" ---------------------------------------------------------------- ");
				System.out.println("|     Incorrect Password.                                        |");
				System.out.println("|     Number of tries left: " + count + "                                    |");
				System.out.println("|                                                                |");
			}
//            System.out.println(" ---------------------------------------------------------------- ");
			System.out.println("|     Number of tries exceeded...!!                              |");
			System.out.println("|     UNAUTHORIZED ENTRY, REPORTING TO FRAUD DEPARTMENT!         |");
			System.out.println("|     Staff ID ::: " + staffID + " ::: has been reported.     |");
			System.out.println("|                     System SHUT DOWN!                          |");
			System.exit(0);

		} else {
			try {
				con = SQLConnection.getConnection(schema);
				custMenu(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("|----------------------------------------------------------------|");
			System.out.println("|      Access Level:      Customer                               |");
			System.out.println("|      Hello and Welcome!                                        |");
			System.out.println("|      Thanks for choosing Premium Real Estate!                  |");
//            System.out.println();

			while (true) {
				System.out.println("");
				enterCustomerTaunt();
				input = s.nextLine();
				youSure();
				input2 = s.nextLine();
				if (input.equalsIgnoreCase("1") && input2.equalsIgnoreCase("y")) {
					try {
						con = SQLConnection.getConnection(schema);
						custMenu(con);
					} catch (SQLException e) {
						e.printStackTrace();
					}
//                    custMenu(con);
				} else if (input.equalsIgnoreCase("2") && input2.equalsIgnoreCase("y")) {
					switchOFF();
				}
			}
		}

	}

	private static void switchOFF() {
//        System.out.println(" ________________________________________________________________");
		System.out.println("|                                                                |");
		System.out.println("|     Thanks for using Premium Real Estate UI!                   |");
		System.out.println(" ________________________________________________________________ ");
		System.exit(0);
	}

	private static void enterCustomerTaunt() {
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("|     Input Selection:                                           |");
		System.out.println("|     (1) Customer Menu.                                         |");
		System.out.println("|     (2) Exit.                                                  |");
		System.out.println("|----------------------------------------------------------------|");
	}

	private static void youSure() {
		System.out.println("|     Please confirm your input.                                 |");
//        System.out.println("Please confirm your input.");
		agreement();
	}

	private static void agreement() {
//        System.out.println(" ________________________________________________________________ ");
//        System.out.println("|----------------------------------------------------------------|");
		System.out.println("|     Type 'y' for yes, or 'n' for no, then press enter.         |");
		System.out.print("      ");
//        System.out.println("|----------------------------------------------------------------|");
	}

	private static void printStaffMenu() {
//      System.out.println(" ________________________________________________________________ ");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("|     (1) View data.                                             |");
		System.out.println("|     (2) Insert data.                                           |");
		System.out.println("|     (3) Update data.                                           |");
		System.out.println("|     (4) Delete data.                                           |");
		System.out.println("|     (5) Exit.                                                  |");
		System.out.println("|----------------------------------------------------------------|");
		System.out.print("      ");

//      System.out.println();
	}

	private static void staffMenu(Connection con) throws IOException, InterruptedException {
		if (accessLevel == -1) {
//            System.out.println(" ________________________________________________________________ ");
			System.out.println("                 Unauthorised Entry.");
			System.out.println("                 Access Denied.");
			switchOFF();
//            System.out.println(" ________________________________________________________________ ");
//            System.out.println();
		}
		String desiredAction;
		System.out.println();
//        System.out.println(" ________________________________________________________________ ");
		System.out.println("|----------------------------------------------------------------|");
		System.out.println("|                   Welcome to the staff menu!                   |");
		System.out.println("|                What would you like to do today:                |");
//        System.out.println(" ---------------------------------------------------------------- ");
//        System.out.println("|________________________________________________________________|");

		printStaffMenu();
		desiredAction = s.nextLine();
		youSure();
//		agreement();
		input = s.nextLine();
		System.out.println();

		while (input.equalsIgnoreCase("n") || !(1 <= Integer.parseInt(desiredAction))
				|| !(Integer.parseInt(desiredAction) <= 6)) {
			System.out.println("|     Choose again:                                              |");
			printStaffMenu();
			desiredAction = s.nextLine();
			youSure();
//            agreement();
			input = s.nextLine();
//            System.out.println();
		}

		if (desiredAction.equalsIgnoreCase("1")) {
//            System.out.println(" ________________________________________________________________ ");
			System.out.println("|----------------------------------------------------------------|");
			System.out.println("|     Where would you like to View the data from:                |");
			viewData(con);

		} else if (desiredAction.equalsIgnoreCase("2")) {
//            System.out.println(" ________________________________________________________________ ");
			System.out.println("|----------------------------------------------------------------|");
			System.out.println("|     Where would you like to Insert the data:                   |");
			insertData(con);

		} else if (desiredAction.equalsIgnoreCase("3")) {
//            System.out.println(" ________________________________________________________________ ");
			System.out.println("|----------------------------------------------------------------|");
			System.out.println("|     Where would you like to Update the data:                   |");
			// View the different tables available in the database to UPDATE some data.
			// Have a function call updateData()

		} else if (desiredAction.equalsIgnoreCase("4")) {
//            System.out.println(" ________________________________________________________________ ");
			System.out.println("|----------------------------------------------------------------|");
			System.out.println("|     Row Deletion Procedure Initiated:                          |");
			// View the different tables available in the database to Delete data.
			// Have a function call delete_data()
			delete_data(con);

		} else if (desiredAction.equalsIgnoreCase("5")) {
//            youSure();
			System.out.println("|     Are you absolutely certain that you would like to leave?   |");
//            System.out.println("|     We're happy to have been here to help you do your job!     |");
//            System.out.println("| If you need anything else, be sure to use our tool again later!|");
//            System.out.println("|     Are you absolutely certain that you would like to leave?   |");
			input = s.nextLine();
			if (input.equalsIgnoreCase("n")) {
				System.out.println("|     Redirecting to Staff Menu                                  |");
				staffMenu(con);
			} else
				System.out.println("|     We're happy to have been here to help you do your job!     |");
			System.out.println("| If you need anything else, be sure to use our tool again later!|");
			switchOFF();
		}
	}

	private static void delete_data(Connection con) {
		int temp_input = -1;
		
		int table_select_prompt = -1;
		String table_selected_as_string = "";
		int count = 0;
		
		ResultSetMetaData rsmd;

		try {
			rs = Query_Execution.executeQuery(con, "SELECT * FROM pg_tables WHERE schemaname='" + schema + "'");
			System.out.println("|         --- Tables Present ---                             |");

			System.out.println();
			
			rsmd = rs.getMetaData();
			int col_count = rsmd.getColumnCount();
			List<String> col_names = new ArrayList<>();
			
			while (rs.next()) {
				count++;
				String val = rs.getString("tablename");
				col_names.add(val);
				System.out.println("\t(" + count + ")\t" + val.toUpperCase());
			}

			System.out.println("|                                                                           |");

			while (table_select_prompt == -1) {
				System.out.println(
						"Which type of table would you like to delete a row from? Make sure you input a valid integer.");
				temp_input = Integer.parseInt(s.nextLine());

				if (temp_input > 0 && temp_input <= col_count) {
					table_select_prompt = temp_input;
					table_selected_as_string = col_names.get(temp_input-1);
				}
			}
			String primary_key_for_deletion = "";
			
			if(table_selected_as_string.equalsIgnoreCase("manager")) {
			    System.out.println("| Warning, before removing this manager, you should ensure the manager's    |\n" +
                                   "| office is delegated to a different manager.                               |");
			    System.out.println("| Using the update function will help you complete this.                    |");
                System.out.println("|                                                                           |");
			    System.out.println("| What is the tax id of the manager you'd like to remove?                   |");
			    System.out.println("| NOTE: If the tax id has a leading zero, do not include it in your input.  |");
			
			    primary_key_for_deletion = s.nextLine();
			
			    while(primary_key_for_deletion.length()>9 || primary_key_for_deletion.length()<8) {
				    System.out.println("Invalid tax ID format, try again.");
				    primary_key_for_deletion = s.nextLine();

			    }
			    System.out.println("Are you absolutely sure you want to delete the " + table_selected_as_string + " with the primary key " + primary_key_for_deletion + "?");
			    System.out.println("Please be aware, failing to indicate a new manager to control the office will result in the office having no manager.");
			
                String temp_choice = s.nextLine();
                while(!temp_choice.equalsIgnoreCase("y")){

                    if(temp_choice.equalsIgnoreCase("n")){
                        staffMenu(con); //behavior only needs to be defined for staff menu as per the fact customers can never get here.
                        return;
                    }

				    System.out.println("Invalid input; type 'y' for yes, or 'n' for no (to go back to the action-menu.");
				
			    }
			
			    rs = Query_Execution.executeQuery(con, "DELETE from manager where taxid =' " + primary_key_for_deletion + "'");
			    rs = Query_Execution.executeQuery(con, "DELETE from person where taxid =' " + primary_key_for_deletion + "'");

			}
			if(table_selected_as_string.equalsIgnoreCase("agent")) {
				System.out.println("|Warning, for reference purposes, all property listing attached to this agent will still retail this agent's taxid.|");
				
				
				System.out.println("| What is the tax id of the agent you'd like to remove?                   |");
				System.out.println("| NOTE: If the tax id has a leading zero, do not include it in your input.  |");
				
				primary_key_for_deletion = s.nextLine();
				
				while(primary_key_for_deletion.length()>9 || primary_key_for_deletion.length()<8) {
					System.out.println("Invalid tax ID format, try again.");
					primary_key_for_deletion = s.nextLine().trim();
				}
				System.out.println("Are you absolutely sure you want to delete the " + table_selected_as_string + " with the primary key " + primary_key_for_deletion + "?");
				System.out.println("Please be aware, failing to indicate a new manager to control the office will result in the office having no manager.");
				
				String temp_choice = s.nextLine();
				
				while(!temp_choice.equalsIgnoreCase("y")){
					
					if(temp_choice.equalsIgnoreCase("n")){
					staffMenu(con); //behavior only needs to be defined for staff menu as per the fact customers can never get here.
					return;
					}
					
					System.out.println("Invalid input; type 'y' for yes, or 'n' for no (to go back to the action-menu.");
					
				}
				PreparedStatement delPS = con.prepareStatement("DELETE FROM client WHERE taxid = (?)");
				System.out.println(primary_key_for_deletion);
				delPS.setString(1, primary_key_for_deletion);

				rs = delPS.executeQuery();
				System.out.println(rs.rowDeleted());
				rs = Query_Execution.executeQuery(con, "DELETE FROM person WHERE taxid ='" + primary_key_for_deletion + "'");


			}
			
			if(table_selected_as_string.equalsIgnoreCase("client")) {
				System.out.println("Are you absolutely sure you want to delete the " + table_selected_as_string + " with the primary key " + primary_key_for_deletion + "?");

				
				rs = Query_Execution.executeQuery(con, "");
			}

			if(table_selected_as_string.equalsIgnoreCase("person")) {
				System.out.println("Please rerun the delete function selecting the type of person you'd like to delete (agent/manager/client)!");
				System.exit(0);
			}
			if(table_selected_as_string.equalsIgnoreCase("property")) {
				System.out.println("Are you absolutely sure you want to delete the " + table_selected_as_string + " with the primary key " + primary_key_for_deletion + "?");

				rs = Query_Execution.executeQuery(con, "");
			}
			if(table_selected_as_string.equalsIgnoreCase("office")) {
				System.out.println("Deleting an office must be done by the system administrator.");
				staffMenu(con);
				return;
			}

			/*
			PROPERTY
			AGENT
			OFFER
			PERSON //taxid
			CLIENT
			MANAGER //taxid 
			-delete from manager, delete from person
			OFFICE officeid
			ADDRESS addressid
			*/

		} catch (SQLException | IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static void displayTable(List<String> list) {
		String result;
		System.out.println("|         --- Tables Present ---                                 |");
		for (int i = 1; i <= list.size(); i++) {
			result = list.get(i - 1);
			System.out.println("\t  (" + i + ")\t" + result.toUpperCase());
		}
	}

	private static void viewData_Specify(Connection con, List<String> list) throws IOException, InterruptedException {
		String result;
		int count = list.size();
		displayTable(list);
		count++;
		System.out.println("\t(" + count + ")\t" + "staff menu".toUpperCase());
		count++;
		System.out.println("\t(" + count + ")\t" + "customer menu".toUpperCase());
		System.out.println();
//        System.out.println(" ________________________________________________________________ ");
		System.out.println("|   Choose the Table:                                            |");
		input = s.nextLine();
		if (Integer.parseInt(input) == count - 1) {
			staffMenu(con);
		} else if (Integer.parseInt(input) == count) {
			custMenu(con);
		}
		result = list.get(Integer.parseInt(input) - 1);
		while (true) {
			rs = Query_Execution.executeQuery(con, "SELECT * FROM " + result);
			System.out.println();
			System.out.println(
					"Enter your specification, either as a list of numbers separated by commas or individually");
			ResultSetMetaData rsmd;
			try {
				rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				List<String> columnNames = new ArrayList<>();
				for (int i = 1; i <= columnsNumber; i++) {
					columnNames.add(rsmd.getColumnName(i));
					System.out.println("\t(" + i + ")\t" + columnNames.get(i - 1).toUpperCase());
				}
//                    System.out.println(" ________________________________________________________________ ");
				System.out.println("|   Selection:                                                   |");
				System.out.print("     ");
				input = s.nextLine();
				String selection[] = input.split("[, ?.@\n]+");
				count = viewDataList();
				int repeat = 0;
				String query = columnNames.get(Integer.parseInt(selection[0]) - 1);
				for (int i = 2; i <= selection.length; i++) {
					query = query + ", " + columnNames.get(Integer.parseInt(selection[i - 1]) - 1);
				}
				query = "SELECT " + query + " FROM " + result + ";";
				rs = Query_Execution.executeQuery(con, query);
				String columnCount[] = new String[selection.length];
				while (rs.next()) {
					if (repeat != count) {
						for (int i = 1; i <= selection.length; i++) {
							columnCount[i - 1] = rs.getString(i);
						}
						System.out.print("|\t\t");
						for (String each : columnCount) {
							System.out.print(each + "\t\t|\t\t");
						}
						System.out.println();
						repeat++;
					} else
						break;
				}
				while (true) {
					System.out.println("Continue with this table (y/n)\n" + "(1) Staff Menu\n" + "(2) Customer Menu"
							+ "(3) Enter filters for this table\n");
					agreement();
					input = s.nextLine();
					youSure();
					agreement();
					input2 = s.nextLine();
					if (input.equalsIgnoreCase("y") && input2.equalsIgnoreCase("y")) {
						break;
					} else if (input.equalsIgnoreCase("n") && input2.equalsIgnoreCase("y")) {
						viewData_Specify(con, list);
					} else if (input.equalsIgnoreCase("1") && input2.equalsIgnoreCase("y")) {
						staffMenu(con);
					} else if (input.equalsIgnoreCase("2") && input2.equalsIgnoreCase("y")) {
						custMenu(con);
					} else if (input.equalsIgnoreCase("3") && input2.equalsIgnoreCase("y")) {
						completeFilter(con, list);
					}
					System.out.println("Wrong Formulation");
					System.out.println();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void completeFilter(Connection con, List<String> list) {

	}

	private static int viewDataList() {
		System.out.println("How much data would you like to view? (Ex. 5, 10, 100, 782, all) and so on...");
		input = s.nextLine();
		int count = -1;
		if (!input.equalsIgnoreCase("all")) {
			count = Integer.parseInt(input);
		}
		return count;
	}

	private static void viewData(Connection con) throws IOException, InterruptedException {
		int count;
		List<String> list = new ArrayList<>();
		while (true) {
			count = 0;
			try {
				rs = Query_Execution.executeQuery(con, "SELECT * FROM pg_tables WHERE schemaname='" + schema + "'");
				System.out.println("|         --- Tables Present ---                             |");
				while (rs.next()) {
					count++;
					String val = rs.getString("tablename");
					list.add(val);
					System.out.println("\t(" + count + ")\t" + val.toUpperCase());
				}
				System.out.println();
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
				count = viewDataList();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();

				String columnCount[] = new String[columnsNumber + 1];
				String align = "|";
				for (int i = 1; i <= columnsNumber; i++) {
					columnCount[i - 1] = rsmd.getColumnName(i).toUpperCase();
					align = align + "\t%10s\t\t|\t\t";
				}
				System.out.format(align + "\n", (Object[]) columnCount);
//                String columnHeaders[] = columnCount;
				System.out.println();
				int repeat = 0;
				while (rs.next()) {
					if (repeat != count) {
						for (int i = 1; i <= columnsNumber; i++) {
							columnCount[i - 1] = rs.getString(i);
						}
						repeat++;
					} else
						break;
					// relays all the columns with all the data
					System.out.format(align + "\n", (Object[]) columnCount);
				}

				System.out.println("Do you wish to apply and filters to the results?");
				agreement();
				input = s.nextLine();
				if (input.equalsIgnoreCase("y")) {
					System.out.println();
					viewData_Specify(con, list);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			dataAction("VIEWING", con);
		}
	}

	private static void dataAction(String str, Connection con) throws IOException, InterruptedException {
		int count = 0;
		while (true) {
			count++;
			System.out.println();
			System.out.println("Do you want to continue " + str + " the data?");
			agreement();
			input = s.nextLine();
			System.out.println();
			youSure();
//            agreement();
			input2 = s.nextLine();
			if (input.equalsIgnoreCase("n") && input2.equalsIgnoreCase("y"))
				staffContinueQuestion(con);
			else if (input.equalsIgnoreCase("y") && input2.equalsIgnoreCase("y"))
				break;
			else if (count == 3) {
				System.out.println("Make up your mind....");
			} else if (count == 5) {
				System.out.println("Now this is getting annoying.......");
			} else if (count > 5) {
				System.out.println("Ok, clearly you aren't well enough to make your own choices.");
				System.out.println("So, FORCE SHUT DOWN!!!");
				switchOFF();
			}
		}
	}

	private static void staffContinueQuestion(Connection con) throws InterruptedException, IOException {
		while (true) {
//            System.out.println("|         --- Tables Present ---                             |");
			System.out.println("|  Do you want to return to the Staff Menu (y) or EXIT (n)?  |");
			agreement();
			input = s.nextLine();
			System.out.println();
			youSure();
			agreement();
			input2 = s.nextLine();
			if (input.equalsIgnoreCase("y") && input2.equalsIgnoreCase("y")) {
				staffMenu(con);
			} else if (input.equalsIgnoreCase("n") && input2.equalsIgnoreCase("n"))
				System.exit(0);
			else
				return;
		}
	}

	private static void custMenu(Connection con) throws IOException, InterruptedException {
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

	private static void custCommandList() {
		boolean this_will_look_awful_on_the_powerpoint = true;
		// TODO, make this_will_look_awful_on_the_powerpoint = false.

		while (!this_will_look_awful_on_the_powerpoint) {
			System.out.println(" ________________________________________________________________ ");
			System.out.println("|----------------------------------------------------------------|");
			System.out.println("| (1) View data.                                                 |");
			System.out.println("|                                                                |");
			System.out.println("|                                                                |");
			System.out.println("|----------------------------------------------------------------|");
		}

	}

	private static int chooseInsertList() {
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

	private static void insertData(Connection con) {
		String sql = "";
		System.out.println("What would you like to insert?");
		int insertType = chooseInsertList();
		switch (insertType) {
		case 1:
			// Insert new property
			insertNewProp(con);
		case 2:
			// Insert new client
			insertNewClient(con);
		case 3:
			// Insert new agent
			insertNewAgent(con);
		case 4:
			// Insert new offer
			insertNewOffer(con);
		case 5:
			// Insert new office
			insertNewOffice(con);
		default:
			// something has gone wrong...
		}
	}

	private static void insertNewProp(Connection con) {
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

	static void insertNewClient(Connection con) {
		s.nextLine();

		System.out.print("Enter the client's first name: ");
		String first = s.nextLine();

		System.out.print("Enter the client's last name: ");
		String last = s.nextLine();

		System.out.print("Enter the tax id of the new client: ");
		String clientID = s.nextLine();

		System.out.print("Enter the tax id of the client's agent: ");
		String agentID = s.nextLine();

		System.out.print("Enter the client's phone number: ");
		String phone = s.nextLine();

		System.out.print("Enter the client's address: ");
		String address = s.nextLine();

		System.out.print("Enter the client's city: ");
		String city = s.nextLine();

		System.out.print("Enter the client's state abbreviation: ");
		String state = s.nextLine();

		System.out.print("Enter the client's ZIP code: ");
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

			PreparedStatement personPS = con.prepareStatement("INSERT INTO person VALUES(?, ?, ?, ?, ?);");
			personPS.setString(1, first);
			personPS.setString(2, last);
			personPS.setString(3, clientID);
			personPS.setString(4, phone);
			personPS.setInt(5, addressid);
			personPS.execute();

			PreparedStatement clientPS = con.prepareStatement("INSERT INTO client VALUES(?, ?)");
			clientPS.setString(1, clientID);
			clientPS.setString(2, agentID);
			clientPS.execute();

			System.out.println("Client " + first + " " + last + " inserted!");
		} catch (SQLException e) {
			System.err.println("Could not insert new client.");
			System.out.println("Error: " + e.getMessage());
		}
		// get first
		// get last
		// get taxid
		// get phone num
		// get address details
		// get agent taxid
		// can now insert client
	}

	static void insertNewAgent(Connection con) {
		s.nextLine();

		System.out.print("Enter the agent's first name: ");
		String first = s.nextLine();

		System.out.print("Enter the agent's last name: ");
		String last = s.nextLine();

		System.out.print("Enter the tax id of the new agent: ");
		String agentID = s.nextLine();

		System.out.print("Enter the tax id of the agent's manager: ");
		String managerID = s.nextLine();

		System.out.print("Enter the salary of the agent: ");
		int salary = s.nextInt();

		System.out.print("Enter the id of the agent's primary office: ");
		int officeID = s.nextInt();

		s.nextLine();
		System.out.print("Enter the commission percentage for this agent. (e.g. 14.20): ");
		float comm = s.nextFloat();

		s.nextLine();
		System.out.print("Enter the agent's phone number: ");
		String phone = s.nextLine();

		System.out.print("Enter the agent's address: ");
		String address = s.nextLine();

		System.out.print("Enter the agent's city: ");
		String city = s.nextLine();

		System.out.print("Enter the agent's state abbreviation: ");
		String state = s.nextLine();

		System.out.print("Enter the agent's ZIP code: ");
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

			PreparedStatement personPS = con.prepareStatement("INSERT INTO person VALUES(?, ?, ?, ?, ?);");
			personPS.setString(1, first);
			personPS.setString(2, last);
			personPS.setString(3, agentID);
			personPS.setString(4, phone);
			personPS.setInt(5, addressid);
			personPS.execute();

			PreparedStatement clientPS = con.prepareStatement("INSERT INTO agent VALUES(?, ?, ?, ?, ?)");
			clientPS.setString(1, agentID);
			clientPS.setInt(2, salary);
			clientPS.setInt(3, officeID);
			clientPS.setFloat(4, comm);
			clientPS.setString(5, managerID);
			clientPS.execute();

			System.out.println("Agent " + first + " " + last + " inserted!");
		} catch (SQLException e) {
			System.err.println("Could not insert new agent.");
			System.out.println("Error: " + e.getMessage());
		}
		// get first
		// get last
		// get taxid
		// get phone num
		// get address details
		// get salary
		// get primary office id
		// get commission percentage
		// get taxid of manager
		// can now insert agent
	}

//    static void insertNewManager(Connection con) {
//        s.nextLine();
//
//        System.out.print("Enter the manager's first name: ");
//        String first = s.nextLine();
//
//        System.out.print("Enter the manager's last name: ");
//        String last = s.nextLine();
//
//        System.out.print("Enter the tax id of the new manager: ");
//        String managerID = s.nextLine();
//
//        System.out.print("Enter the salary of the new manager: ");
//        int salary = s.nextInt();
//
//        System.out.print("Enter the id of the manager's office: ");
//        int officeID = s.nextInt();
//
//        s.nextLine();
//        System.out.print("Enter the manager's phone number: ");
//        String phone = s.nextLine();
//
//        System.out.print("Enter the manager's address: ");
//        String address = s.nextLine();
//
//        System.out.print("Enter the manager's city: ");
//        String city = s.nextLine();
//
//        System.out.print("Enter the manager's state abbreviation: ");
//        String state = s.nextLine();
//
//        System.out.print("Enter the manager's ZIP code: ");
//        String zip = s.nextLine();
//
//        try {
//            int addressid = 0;
//
//            rs = Query_Execution.executeQuery(con, "SELECT MAX(addressid)+1 AS newID FROM address;");
//            if(rs.next())
//                addressid = rs.getInt("newID");
//
//            PreparedStatement addressPS = con.prepareStatement("INSERT INTO address VALUES(?, ?, ?, ?, ?);");
//            addressPS.setString(1, address);
//            addressPS.setString(2, city);
//            addressPS.setString(3, state);
//            addressPS.setString(4, zip);
//            addressPS.setInt(5, addressid);
//            addressPS.execute();
//
//            PreparedStatement personPS = con.prepareStatement("INSERT INTO person VALUES(?, ?, ?, ?, ?);");
//            personPS.setString(1, first);
//            personPS.setString(2, last);
//            personPS.setString(3, managerID);
//            personPS.setString(4, phone);
//            personPS.setInt(5, addressid);
//            personPS.execute();
//
//            PreparedStatement managerPS = con.prepareStatement("INSERT INTO manager VALUES(?, ?, ?);");
//            managerPS.setString(1, managerID);
//            managerPS.setInt(2, salary);
//            managerPS.setInt(3, officeID);
//
//            System.out.println("Manager " + first + " " + last + " inserted!");
//        }
//        catch (SQLException e)
//        {
//            System.err.println("Could not insert new manager.");
//            System.out.println("Error: " + e.getMessage());
//        }
//        //get first
//        //get last
//        //get taxid
//        //get phone num
//        //get address details
//        //get salary
//        //should be called from office creation to get office id
//        //can now insert manager
//    }

	static void insertNewOffer(Connection con) {
		s.nextLine();

		System.out.print("Enter the id of the property this offer is being made on: ");
		int propID = s.nextInt();

		s.nextLine();
		System.out.print("Enter the tax id of the buyer: ");
		String buyerID = s.nextLine();

		System.out.print("Enter the offer amount in dollars: $");
		int offer = s.nextInt();

		try {
			int offerID = 0;

			rs = Query_Execution.executeQuery(con, "SELECT MAX(offerid)+1 AS newID FROM offer;");
			if (rs.next())
				offerID = rs.getInt("newID");

			PreparedStatement offerPS = con.prepareStatement("INSERT INTO offer VALUES (?, ?, GETDATE(), ?, ?, 'p');");
			offerPS.setInt(1, offerID);
			offerPS.setInt(2, propID);
			offerPS.setString(3, buyerID);
			offerPS.setInt(4, offer);

			System.out.println("Offer #" + offerID + " added!");
		} catch (SQLException e) {
			System.err.println("Could not make the offer.");
			System.out.println("Error: " + e.getMessage());
		}
		// property made on
		// get current date value from the sql
		// tax ID of client
		// offer amount
		// no need to put in status, default to waiting
		// can now insert offer
	}

	static void insertNewOffice(Connection con) {
		s.nextLine();

		System.out.print("Enter the tax id of the office's manager: ");
		String managerID = s.nextLine();

		System.out.print("Enter the office's address: ");
		String address = s.nextLine();

		System.out.print("Enter the office's city: ");
		String city = s.nextLine();

		System.out.print("Enter the office's state abbreviation: ");
		String state = s.nextLine();

		System.out.print("Enter the office's ZIP code: ");
		String zip = s.nextLine();

		try {
			int officeID = 0;

			rs = Query_Execution.executeQuery(con, "SELECT MAX(officeid)+1 AS newID FROM office;");
			if (rs.next())
				officeID = rs.getInt("newID");

			int addressID = 0;

			rs = Query_Execution.executeQuery(con, "SELECT MAX(addressid)+1 AS newID FROM address;");
			if (rs.next())
				addressID = rs.getInt("newID");

			PreparedStatement addressPS = con.prepareStatement("INSERT INTO address VALUES (?, ?, ?, ?, ?);");
			addressPS.setString(1, address);
			addressPS.setString(2, city);
			addressPS.setString(3, state);
			addressPS.setString(4, zip);
			addressPS.setInt(5, addressID);
			addressPS.execute();

			PreparedStatement officePS = con.prepareStatement("INSERT INTO office VALUES (?, ?, ?);");
			officePS.setInt(1, officeID);
			officePS.setString(2, managerID);
			officePS.setInt(3, addressID);
			officePS.execute();

			System.out.println("Office #" + officeID + " added!");
		} catch (SQLException e) {
			System.err.println("Could not make the offer.");
			System.out.println("Error: " + e.getMessage());
		}
		// new manager?
		// add their taxID
		// get address details
		// new office can be created
	}
}
