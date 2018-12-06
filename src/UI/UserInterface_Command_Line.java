package UI;

import Backend_SQL.Query_Execution;
import Backend_SQL.SQLConnection;

import javax.management.Query;
import java.io.File;
import java.io.FileNotFoundException;
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

	//-1 is no access

//	String[] actions = {};
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Welcome to the Mior Mega Real Estate Company database access user interface!");
		System.out.println("Do you have administrator credentials?");
		agreement();
		input = s.nextLine();
        System.out.println();

		// limit of tries for password entry
		int count = 3;

		// generating the Staff ID. Maximum limit is 20000
		Random num = new Random();
        int staffID = num.nextInt(20000);

        if(input.equalsIgnoreCase("y")){
//            String password = "maximum_mior";
            String password = "123";
            while(count != 0) {
                System.out.println("Please enter your administration credentials!");
                String attemptedPassword = s.nextLine();
                if(attemptedPassword.equals(password)) {
                    accessLevel = 9001;
                    System.out.println();
                    System.out.println("-------------- Access has been granted --------------");
                    System.out.println("Welcome staff member: " + staffID);
                    System.out.println("We hope you're having a profitable day!");
                    System.out.println();
                    System.out.println("Would you like to enter the staff menu?");
                    agreement();
                    input = s.nextLine();
//                    System.out.println();

                    if(input.equalsIgnoreCase("y")){
                        try {
                            con = SQLConnection.getConnection(schema);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        staffMenu(con);
                    } else {
                        System.out.println();
                        System.out.println("Thank you for using Mior Mega Real Estate Company database!!!");
                        System.out.println("HAVE A WONDERFUL DAY!\n");
                        System.exit(0);
                    }

                }
                count--;
                System.out.println("Incorrect Password.");
                System.out.println("Number of tries left: "+ count);
                System.out.println();
            }

            System.out.println("Number of tries exceeded...!!");
            System.out.println("UNAUTHORIZED ENTRY, REPORTING TO FRAUD DEPARTMENT!");
            System.out.println("Staff ID ::: "+ staffID +" ::: has been reported.");
            System.out.println("System SHUT DOWN!");
            System.exit(0);

		}
		else {
			System.out.println("Hello Customer! Welcome!");
			System.out.println("We hope you're having a splendid day!");
			System.out.println();
//			System.out.println();

			while(true){
                System.out.println();
                enterCustomerTaunt();
                input = s.nextLine();
                youSure();
                input2 = s.nextLine();
                if(input.equalsIgnoreCase("1") && input2.equalsIgnoreCase("y")){
                    try {
                        con = SQLConnection.getConnection(schema);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    custMenu(con);
                } else if(input.equalsIgnoreCase("2") && input2.equalsIgnoreCase("y")){
                    switchOFF();
                }
		    }
		}

	}
	//accessLevel
	//staff/mgr/admin = 9001
	//customer (read only) = -1
	public int getAccessLevel(String taxID){
		return accessLevel;

	}

	static void switchOFF(){
        System.out.println("Thank you for using Mior Mega Real Estate Company database!!!");
        System.exit(0);
    }

	static void enterCustomerTaunt(){
        System.out.println("Would you like to enter the Customer Menu (1) to explore our " +
                "bountiful selection, or EXIT (2) ?");
    }

	static void youSure(){
        System.out.println("Are you certain with your choice?");
    }

	static void printStaffMenu(){
        System.out.println("(1) View data");
        System.out.println("(2) Insert data");
        System.out.println("(3) Update data");
        System.out.println("(4) Delete data");
        System.out.println("(5) Just looking around...");
        System.out.println("(6) Exit");
        System.out.println();

    }

    static void agreement(){
        System.out.println("Please type 'y' for yes, or 'n' for no!");
    }

	static void staffMenu(Connection con) throws IOException, InterruptedException {
		String desiredAction;
        System.out.println();
		System.out.println("Welcome to the staff menu!");
		System.out.println("What would you like to do today:");
        printStaffMenu();
		desiredAction = s.nextLine();
		youSure();
		agreement();
		input = s.nextLine();
        System.out.println();

//        if(1 <= Integer.parseInt(desiredAction) && Integer.parseInt(desiredAction) <= 6){
        while(input.equalsIgnoreCase("n") ||
                !(1 <= Integer.parseInt(desiredAction)) ||
                !(Integer.parseInt(desiredAction) <= 6)) {
            System.out.println("Choose again:");
            printStaffMenu();
            desiredAction = s.nextLine();
            youSure();
            agreement();
            input = s.nextLine();
            System.out.println();
        }

        if(desiredAction.equalsIgnoreCase("1")){
            System.out.println("Where would you like to View the data from:");
            // View the different tables available in the database.
            // Have a function call viewData()
            viewData(con);
        } else if(desiredAction.equalsIgnoreCase("2")){
            System.out.println("Where would you like to Insert the data:");
            // View the different tables available in the database to INSERT data.
            // Have a function call insertData()
        } else if(desiredAction.equalsIgnoreCase("3")){
            System.out.println("Where would you like to Update the data:");
            // View the different tables available in the database to UPDATE some data.
            // Have a function call updateData()
        } else if(desiredAction.equalsIgnoreCase("4")){
            System.out.println("Where would you like to Delete the data:");
            // View the different tables available in the database to Delete data.
            // Have a function call deleteData()
        } else if (desiredAction.equalsIgnoreCase("5")) {
            System.out.println("We're happy to have been here to help you do your job, " +
                    "if you need anything else, be sure to use our tool again later!");
            while (true) {
                System.out.println();
                enterCustomerTaunt();
                System.out.println("If you want, you can go back to the Staff Menu (3).");
                input = s.nextLine();
                youSure();
                agreement();
                input2 = s.nextLine();
                if (input.equalsIgnoreCase("1") && input2.equalsIgnoreCase("y")) {
                    custMenu(con);
                } else if (input.equalsIgnoreCase("2") && input2.equalsIgnoreCase("y")) {
                    switchOFF();
                } else if (input.equalsIgnoreCase("3") && input2.equalsIgnoreCase("y")) {
                    staffMenu(con);
                }
            }
        } else if(desiredAction.equalsIgnoreCase("6")){
            switchOFF();
        }
	}

    static void viewData(Connection con){
        int count = 0;
        List<String> list = new ArrayList<>();
	    try {
            rs = Query_Execution.executeQuery(con, "SELECT * FROM pg_tables WHERE schemaname='"+schema+"'");
            System.out.println("--- Tables Present ---");
            while(rs.next()){
                count++;
                String val = rs.getString("tablename");
                list.add(val);
                System.out.println("\t("+ count +")\t"+val.toUpperCase());
            }
            System.out.println();
            System.out.println("Enter the Table number that you wish to view:");
            String table;
            while(true){
                input = s.nextLine();
                int result = Integer.parseInt(input);
                if (1 <= result && result <= count) {
                    table = list.get(result-1);
                    rs = Query_Execution.executeQuery(con, "SELECT * FROM " + table);
                    break;
                }
                else
                    System.out.println("Wrong Input.\nTry again\n");
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            String columnCount[] = new String[columnsNumber+1];
            String align = "";
            for(int i = 1; i <=columnsNumber; i++){
                columnCount[i-1] = rsmd.getColumnName(i).toUpperCase();
                align = align + "%20s|";
            }
            System.out.format(align + "\n",columnCount);
            System.out.println();
            while(rs.next()){
                for (int i = 1; i<=columnsNumber; i++){
                    columnCount[i-1] = rs.getString(i);
                }
                System.out.format(align + "\n",columnCount);
            }
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	static void custMenu(Connection con) throws IOException, InterruptedException {
		String desiredAction = "";

		System.out.println("Welcome to the customer menu!");
		System.out.println("What would you like to do today? \nJust enter a command: ");
		custCommandList(); //will list out all available commands for customers.

		desiredAction = s.nextLine();
		System.out.println();

		System.out.println("If I understand correctly, you're trying to " + desiredAction + ".");
		System.out.println("Do I understand your intentions correctly? \n");

		System.out.println("Please type 'y' for yes, or 'n' for no!");
		if(s.nextLine().equals("y")) {
			System.out.println("Awesome! I will get that ready for you as soon as possible!");

		}


		else
		{
			System.out.println("Well, would you like to try again?");
			System.out.println("Please type 'y' for yes, or 'n' for no!");
			if(s.nextLine().equals("y")) {
				custMenu(con); //is this recursion?
			}
			else {
				System.out.println("\n\n We're happy to have been here to help you, if you need anything else, be " +
                        "sure to use our tool again later!");
			}



		}
	}

	static void custCommandList() {
		//todo: prints customer commands.
	}
	public String getSale(int PID ){
		return "TODO: SALE INFO";
	}
	public String getCustomerInfo(int taxID) {
		return new String();
		//if taxID is seller/buyer
		//	return info
		//else return null/error
	}
	public String getOfficeManager(int officeID) {
		//return as formatted taxID
		return new String();
	}
	public String getOfficeInfo(int officeID) {
		return null;
	}
	public int setOfferStatus(int PID) {
//		return new Integer(7);
        return 0;
		//input: property ID
		//output: 0 for success, -1 for error.
	}

	static int chooseInsertList() {
	    boolean valid = false;
	    int choice = 0;

        System.out.println("(1) New Property");
        System.out.println("(2) New Client");
        System.out.println("(3) New Agent");
        // should be added at the same time as office to keep 1:1 rule
        // System.out.println("(4) New Manager");
        System.out.println("(4) New Offer");
        System.out.println("(5) New Office");
        while(valid == false) {
            choice = s.nextInt();
            if(choice < 6 && choice > 0)
                valid = true;
            else {
                System.out.println("Invalid value entered. Please enter again.");
            }
        }
        return choice;
    }

    static void insertData(Connection con) {
        String sql = "";
        System.out.println("What would you like to insert?");
        int insertType = chooseInsertList();
        switch(insertType) {
            case 1:
                //Insert new property
            case 2:
                //Insert new client
            case 3:
                //Insert new agent
            case 4:
                //Insert new offer
            case 5:
                //Insert new office
            default:
                //something has gone wrong...
        }
    }

    static void insertNewProp(Connection con) {
        //get taxid of agent
        //get taxid of seller
        //get list price
        //get description?
        //get area (sq ft)
        //get address details
        //blank sell date for now
        //can now insert property
    }

    static void insertNewClient(Connection con) {
        //get first
        //get last
        //get taxid
        //get phone num
        //get address details
        //get agent taxid
        //can now insert client
    }

    static void insertNewAgent(Connection con) {
        //get first
        //get last
        //get taxid
        //get phone num
        //get address details
        //get salary
        //get primary office id
        //get commission percentage
        //get taxid of manager
        //can now insert agent
    }

    static void insertNewManager(Connection con) {
        //get first
        //get last
        //get taxid
        //get phone num
        //get address details
        //get salary
        //should be called from office creation to get office id
        //can now insert manager
    }

    static void insertNewOffer(Connection con) {
        //property made on
        //get current date value from the sql
        //tax ID of client
        //offer amount
        //no need to put in status, default to waiting
        //can now insert offer
    }

    static void insertNewOffice(Connection con) {
	    //new manager?
        //add their taxID
        //get address details
        //new office can be created
    }
}



//package Data_Files;
//
//        import Backend_SQL.SQLConnection;
//
//        import java.io.File;
//        import java.io.FileNotFoundException;
//        import java.sql.Connection;
//        import java.sql.PreparedStatement;
//        import java.sql.ResultSet;
//        import java.sql.SQLException;
//        import java.util.Scanner;
//
//class SQLDataInserter{
//    public static void main(String args[]) throws FileNotFoundException {
//        Connection con = null;
//        ResultSet rs = null;
//        try {
//            con = SQLConnection.getConnection("real_estate");
//            // Check statement
//            @SuppressWarnings("resource")
//            Scanner scanner = new Scanner(new File(Data_Files.SQLDataInserter.class.getResource("FAKE_ADDRESS.csv").getFile()));
//
//            int rowCount = 12500; // change based on how many rows you're processing from csv to sql.
//            int rowIndex = 0;
//
//            String nextLine = "";
//            String nextElem = "";
//
//            // IF YOU ARE IMPORTING A CSV WITH N COLUMNS PER ROW, YOU NEED TO HAVE N ?s
//            // inside of the parenthesis below '
//            //EXAMPLE: if N=3 (?,?,?)
//            PreparedStatement ps = con.prepareStatement("INSERT INTO ADDRESS VALUES (?, ?, ?, ?, ?)");
//
//            while (scanner.hasNextLine() && rowIndex < rowCount) {
//                nextLine = scanner.nextLine();
//
//                // scanner2 is used to split up the line grabbed by the first "scanner" scanner.
//                @SuppressWarnings("resource")
//                Scanner scanner2 = new Scanner(nextLine);
//                scanner2.useDelimiter(",");
//
//                while (scanner2.hasNext()) { // could've done scanner2.hasNext, but data in uniform, so we're good.
//                    nextElem = scanner2.next();
//                    System.out.println(nextElem);
//
//                    //one ps.set statement for each question mark in the initialization of the ps object above
//                    //index starts at 1 for whatever reason, error will happen if you start at 0.
//                    //setString for strings, setInt for ints, so on...
//
//                    ps.setString(1, nextElem);
//                    ps.setString(2, scanner2.next());
//                    ps.setString(3, scanner2.next());
//                    ps.setString(4, scanner2.next());
//                    ps.setInt(5, Integer.parseInt(scanner2.next()));
//
//                    if (scanner2.hasNext()) {
//                        System.out.println("There's an issue.");
//                        // scanner2 should have one next() (thus one ps.set call) call for each column
//                        // of the sql table
//                        // if it still has next after the ps.set calls, that means your datas not
//                        // correctly formatted in the .csv
//                        // or you don't have enough ps.set statements relative to the csv's columns per
//                        // row.
//                        return;
//                    }
//
//                    ps.execute();
//                    ps.clearParameters();
//                    // clearParameters not really needed (because our data doesn't have any holes)
//                    // but ensures if we did have a row without data for one column
//                    // the previous row's column won't just be repeated.
//                }
//
//                rowIndex++;
//            }
//            con.close();
//        } catch (SQLException e) {
//
//            System.err.println("Something went wrong. Error: " + e.getMessage());
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                System.err.println("Something went REALLY wrong.");
//            }
//        }
//    }
//
//}
