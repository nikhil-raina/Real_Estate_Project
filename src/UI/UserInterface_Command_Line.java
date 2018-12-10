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

    //-1 is no access

    //	String[] actions = {};
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

        if(input.equalsIgnoreCase("y")){
//            String password = "maximum_mior";
            String password = "123";
            while(count != 0) {
                System.out.println("|----------------------------------------------------------------|");
                System.out.println("|     Please enter your staff credentials.                       |");
                System.out.println("|----------------------------------------------------------------|");
                System.out.print("      ");

//                System.out.println("Please enter your administration credentials!");
                String attemptedPassword = s.nextLine();
                if(attemptedPassword.equals(password)) {
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

                    if(input.equalsIgnoreCase("y")){
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
                System.out.println("|     Number of tries left: "+ count+"                                    |");
                System.out.println("|                                                                |");
            }
//            System.out.println(" ---------------------------------------------------------------- ");
            System.out.println("|     Number of tries exceeded...!!                              |");
            System.out.println("|     UNAUTHORIZED ENTRY, REPORTING TO FRAUD DEPARTMENT!         |");
            System.out.println("|     Staff ID ::: "+ staffID +" ::: has been reported.     |");
            System.out.println("|                     System SHUT DOWN!                          |");
            System.exit(0);

        }
        else {
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

            while(true){
                System.out.println("");
                enterCustomerTaunt();
                input = s.nextLine();
                youSure();
                input2 = s.nextLine();
                if(input.equalsIgnoreCase("1") && input2.equalsIgnoreCase("y")){
                    try {
                        con = SQLConnection.getConnection(schema);
                        custMenu(con);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                    custMenu(con);
                } else if(input.equalsIgnoreCase("2") && input2.equalsIgnoreCase("y")){
                    switchOFF();
                }
            }
        }

    }

    private static void switchOFF(){
//        System.out.println(" ________________________________________________________________");
        System.out.println("|                                                                |");
        System.out.println("|     Thanks for using Premium Real Estate UI!                   |");
        System.out.println(" ________________________________________________________________ ");
        System.exit(0);
    }

    private static void enterCustomerTaunt(){
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|     Input Selection:                                           |");
        System.out.println("|     (1) Customer Menu.                                         |");
        System.out.println("|     (2) Exit.                                                  |");
        System.out.println("|----------------------------------------------------------------|");
    }

    private static void youSure(){
        System.out.println("|     Please confirm your input.                                 |");
//        System.out.println("Please confirm your input.");
        agreement();
    }

    private static void printStaffMenu(){
//        System.out.println(" ________________________________________________________________ ");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|     (1) View data.                                             |");
        System.out.println("|     (2) Insert data.                                           |");
        System.out.println("|     (3) Update data.                                           |");
        System.out.println("|     (4) Delete data.                                           |");
        System.out.println("|     (5) Exit.                                                  |");
        System.out.println("|----------------------------------------------------------------|");
        System.out.print("      ");

//        System.out.println();
    }

    private static void agreement(){
//        System.out.println(" ________________________________________________________________ ");
//        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|     Type 'y' for yes, or 'n' for no, then press enter.         |");
        System.out.print("      ");
//        System.out.println("|----------------------------------------------------------------|");
    }

    private static void staffMenu(Connection con) throws IOException, InterruptedException {
        if(accessLevel == - 1){
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

        while(input.equalsIgnoreCase("n") || !(1 <= Integer.parseInt(desiredAction)) ||
                !(Integer.parseInt(desiredAction) <= 6)) {
            System.out.println("|     Choose again:                                              |");
            printStaffMenu();
            desiredAction = s.nextLine();
            youSure();
//            agreement();
            input = s.nextLine();
//            System.out.println();
        }

        if(desiredAction.equalsIgnoreCase("1")){
//            System.out.println(" ________________________________________________________________ ");
            System.out.println("|----------------------------------------------------------------|");
            System.out.println("|     Where would you like to View the data from:                |");
            viewData(con);

        } else if(desiredAction.equalsIgnoreCase("2")){
//            System.out.println(" ________________________________________________________________ ");
            System.out.println("|----------------------------------------------------------------|");
            System.out.println("|     Where would you like to Insert the data:                   |");
            insertData(con);

        } else if(desiredAction.equalsIgnoreCase("3")){
//            System.out.println(" ________________________________________________________________ ");
            System.out.println("|----------------------------------------------------------------|");
            System.out.println("|     Where would you like to Update the data:                   |");
            // View the different tables available in the database to UPDATE some data.
            // Have a function call updateData()

        } else if(desiredAction.equalsIgnoreCase("4")){
//            System.out.println(" ________________________________________________________________ ");
            System.out.println("|----------------------------------------------------------------|");
            System.out.println("|     Where would you like to Delete the data:                   |");
            // View the different tables available in the database to Delete data.
            // Have a function call deleteData()

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

    private static void displayTable(List<String> list){
        String result;
        System.out.println("|         --- Tables Present ---                                 |");
        for(int i = 1; i <= list.size(); i++){
            result = list.get(i - 1);
            System.out.println("\t  (" + i + ")\t" + result.toUpperCase());
        }
    }

    private static void viewData_Specify(Connection con, List<String> list)throws IOException, InterruptedException{
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
        if(Integer.parseInt(input) == count - 1){
            staffMenu(con);
        } else if(Integer.parseInt(input) == count){
            custMenu(con);
        }
        result = list.get(Integer.parseInt(input) - 1);
        while(true){
            rs = Query_Execution.executeQuery(con, "SELECT * FROM " + result);
            System.out.println();
            System.out.println("Enter your specification, either as a list of numbers separated by commas or individually");
            ResultSetMetaData rsmd;
            try {
                rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                List<String> columnNames = new ArrayList<>();
                for (int i = 1; i <= columnsNumber; i++) {
                    columnNames.add(rsmd.getColumnName(i));
                    System.out.println("\t(" + i + ")\t" + columnNames.get(i-1).toUpperCase());
                }
//                    System.out.println(" ________________________________________________________________ ");
                System.out.println("|   Selection:                                                   |");
                System.out.print("     ");
                input = s.nextLine();
                String selection[] = input.split("[, ?.@\n]+");
                count = viewDataList();
                int repeat = 0;
                String query = columnNames.get(Integer.parseInt(selection[0])-1);
                for(int i = 2; i<=selection.length; i++){
                    query = query + ", " + columnNames.get(Integer.parseInt(selection[i-1])-1);
                }
                query = "SELECT " + query + " FROM "+ result +";";
                rs = Query_Execution.executeQuery(con, query);
                String columnCount[] = new String[selection.length];
                while (rs.next()) {
                    if (repeat != count) {
                        for (int i = 1; i <= selection.length; i++) {
                            columnCount[i - 1] = rs.getString(i);
                        }
                        System.out.print("|\t\t");
                        for(String each : columnCount){
                            System.out.print(each+"\t\t|\t\t");
                        }
                        System.out.println();
                        repeat++;
                    } else
                        break;
                }
                while(true) {
                    System.out.println("Continue with this table (y/n)\n" +
                            "(1) Staff Menu\n" +
                            "(2) Customer Menu" +
                            "(3) Enter filters for this table\n");
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

    private static void completeFilter(Connection con, List<String> list){

    }

    private static int viewDataList(){
        System.out.println("How much data would you like to view? (Ex. 5, 10, 100, 782, all) and so on...");
        input = s.nextLine();
        int count = -1;
        if (!input.equalsIgnoreCase("all")) {
            count = Integer.parseInt(input);
        }
        return count;
    }

    private static void viewData(Connection con)throws IOException, InterruptedException{
        int count;
        List<String> list = new ArrayList<>();
        while(true) {
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
                if(input.equalsIgnoreCase("y")){
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
            System.out.println("Do you want to continue "+ str +" the data?");
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
            else if(count == 3){
                System.out.println("Make up your mind....");
            }
            else if(count == 5){
                System.out.println("Now this is getting annoying.......");
            }
            else if(count > 5){
                System.out.println("Ok, clearly you aren't well enough to make your own choices.");
                System.out.println("So, FORCE SHUT DOWN!!!");
                switchOFF();
            }
        }
    }

    private static void staffContinueQuestion(Connection con) throws InterruptedException, IOException {
        while(true) {
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
}
