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
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(" ________________________________________________________________");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|    Welcome to the Premium Real Estate Company database UI!     |");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|     Do you have staff (admin/manager/agent) credentials?       |");
        agreement();
        try {
            con = SQLConnection.getConnection(schema);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        input = s.nextLine();
        // limit of tries for password entry
        int count = 3;

        // generating the Staff ID. Maximum limit is 20000
        Random num = new Random();
        int staffID = num.nextInt(20000);

        if(input.equalsIgnoreCase("y")){
            String password = "123";
            while(count != 0) {
                System.out.println("|----------------------------------------------------------------|");
                System.out.println("|     Please enter your staff credentials.                       |");
                System.out.println("|----------------------------------------------------------------|");
                System.out.print("      ");
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

                    if(input.equalsIgnoreCase("y")){
                        staffMenu(con);
                    } else {
                        System.out.println("|  Thanks for using the Premium Real Estate Company database UI. |");
                        System.out.println("|  Have a great day.                                             |");
                        System.out.println("|----------------------------------------------------------------|");
                        System.out.println(" ________________________________________________________________ ");
                        System.exit(0);
                    }
                }
                count--;
                System.out.println("|     Incorrect Password.                                        |");
                System.out.println("|     Number of tries left: "+ count+"                                    |");
                System.out.println("|                                                                |");
            }
            System.out.println("|     Number of tries exceeded...!!                              |");
            System.out.println("|     UNAUTHORIZED ENTRY, REPORTING TO FRAUD DEPARTMENT!         |");
            System.out.println("|     Staff ID ::: "+ staffID +" ::: has been reported.     |");
            System.out.println("|                     System SHUT DOWN!                          |");
            System.exit(0);

        }
        else
            custMenu(con);
    }

    private static void switchOFF(){
//        System.out.println(" ________________________________________________________________");
        System.out.println("|                                                                |");
        System.out.println("|     Thanks for using Premium Real Estate UI!                   |");
        System.out.println(" ________________________________________________________________ ");
        System.exit(0);
    }

    private static void enterCustomerTaunt(){
        System.out.println("|                                                                |");
        System.out.println("|     Input Selection:                                           |");
        System.out.println("|     (1) View Data                                              |");
        System.out.println("|     (2) Exit.                                                  |");
        System.out.println("|----------------------------------------------------------------|");
        System.out.print("      ");
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
            System.out.println("                 Unauthorised Entry.");
            System.out.println("                 Access Denied.");
            switchOFF();
        }
        String desiredAction;
        System.out.println();
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|                   Welcome to the staff menu!                   |");
        System.out.println("|                What would you like to do today:                |");

        printStaffMenu();
        desiredAction = s.nextLine();
        youSure();
        input = s.nextLine();
        System.out.println();

        while(input.equalsIgnoreCase("n") || !(1 <= Integer.parseInt(desiredAction)) ||
                !(Integer.parseInt(desiredAction) <= 6)) {
            System.out.println("|     Choose again:                                              |");
            printStaffMenu();
            desiredAction = s.nextLine();
            youSure();
            input = s.nextLine();
        }

        if(desiredAction.equalsIgnoreCase("1")){
            System.out.println("|----------------------------------------------------------------|");
            System.out.println("|     Where would you like to View the data from:                |");
            viewData(con);

        } else if(desiredAction.equalsIgnoreCase("2")){
            System.out.println("|----------------------------------------------------------------|");
            System.out.println("|     Where would you like to Insert the data:                   |");
            insertData(con);

        } else if(desiredAction.equalsIgnoreCase("3")){
            System.out.println("|----------------------------------------------------------------|");
            System.out.println("|     Where would you like to Update the data:                   |");
            // View the different tables available in the database to UPDATE some data.
            // Have a function call updateData()
            updateData(con);

        } else if(desiredAction.equalsIgnoreCase("4")){
            System.out.println("|----------------------------------------------------------------|");
            System.out.println("|     Where would you like to Delete the data:                   |");
            // View the different tables available in the database to Delete data.
            // Have a function call deleteData()

        } else if (desiredAction.equalsIgnoreCase("5")) {
            System.out.println("|     Are you absolutely certain that you would like to leave?   |");
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
        System.out.println("\t  (" + count + ")\t" + "staff menu".toUpperCase());
        count++;
        System.out.println("\t  (" + count + ")\t" + "customer menu".toUpperCase());
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
            System.out.println("|                                                                |");
//            System.out.println(" ________________________________________________________________ ");

            System.out.println("|     Enter your specification, either as a list of numbers      |\n" +
                               "|     separated by commas or individually                        |");
            ResultSetMetaData rsmd;
            try {
                rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                List<String> columnNames = new ArrayList<>();
                for (int i = 1; i <= columnsNumber; i++) {
                    columnNames.add(rsmd.getColumnName(i));
                    System.out.println("\t  (" + i + ")\t" + columnNames.get(i-1).toUpperCase());
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
//                    System.out.println("|                                                                |");
                    System.out.println("|     Continue with this table (y/n)                             |\n" +
                                       "|     (1) Staff Menu                                             |\n" +
                                       "|     (2) Customer Menu                                          |\n" +
                                       "|     (3) Enter filters for this table                           |\n");
                    System.out.print("     ");
                    agreement();
                    input = s.nextLine();
                    youSure();
//                    agreement();
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
        System.out.println("|                                                                |");
        System.out.println("|     How much data would you like to view? (Ex. 5, 10,          |\n" +
                           "|     100, 782, all) and so on...                                |");
        System.out.print("     ");
        input = s.nextLine();
        int count = -1;
        if (!input.equalsIgnoreCase("all")) {
            count = Integer.parseInt(input);
        }
        return count;
    }

    private static List<String> viewAvailableTable(Connection con){
        List<String> list = new ArrayList<>();
        int count = 0;
        try {
            rs = Query_Execution.executeQuery(con, "SELECT * FROM pg_tables WHERE schemaname='" + schema + "'");
            System.out.println("|         --- Tables Present ---                                 |");
            while (rs.next()) {
                count++;
                String val = rs.getString("tablename");
                list.add(val);
                System.out.println("      (" + count + ")\t" + val.toUpperCase());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("|                                                                |");
        System.out.println("|    Enter the table number to view:                             |");
        return list;
    }

    private static void viewData(Connection con)throws IOException, InterruptedException{
        int count;
        List<String> list = viewAvailableTable(con);
        while(true) {
            count = 0;
            try {
                String table;
                while (true) {
                    input = s.nextLine();
                    int result = Integer.parseInt(input);
                    if (1 <= result && result <= count) {
                        table = list.get(result - 1);
                        rs = Query_Execution.executeQuery(con, "SELECT * FROM " + table);
                        break;
                    } else {
//                        System.out.println("|                                                                |");
                        System.out.println("|     Wrong Input.                                               |\n" +
                                           "|     Try again                                                  |\n");
                        System.out.print("     ");
                    }
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
                System.out.println("|                                                                |");
                System.out.println("|     Do you wish to apply and filters to the results?           |");
                agreement();
                System.out.print("     ");
                input = s.nextLine();
//                System.out.print("     ");
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
//            System.out.println();
            System.out.println("|                                                                |");
            System.out.println("|     Do you want to continue "+ str +" the data?                   |");
            agreement();
            System.out.print("     ");
            input = s.nextLine();
            System.out.println();
            youSure();
            System.out.print("     ");
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
            System.out.print("     ");
            input = s.nextLine();
            System.out.println();
            youSure();
            System.out.print("     ");
//            agreement();
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
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("|      Access Level:      Customer                               |");
        System.out.println("|      Hello and Welcome!                                        |");
        System.out.println("|      Thanks for choosing Premium Real Estate!                  |");
        while(true){
            System.out.println("|                                                                |");
            enterCustomerTaunt();
            input = s.nextLine();
            youSure();
            input2 = s.nextLine();
            if(input.equalsIgnoreCase("1") && input2.equalsIgnoreCase("y")){
                viewData(con);
            } else if(input.equalsIgnoreCase("2") && input2.equalsIgnoreCase("y")){
                switchOFF();
            }
            System.out.println("|----------------------------------------------------------------|");
            System.out.println("|     Series of inputs did not make sense.                       |");
            System.out.println("|     Please Enter Again                                         |");
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

    private static int chooseUpdateList() {
        boolean valid = false;
        int choice = 0;

        System.out.println(" ________________________________________________________________ ");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("| (1) Update Property.                                           |");
        System.out.println("| (2) Update Client.                                             |");
        System.out.println("| (3) Update Agent.                                              |");
        System.out.println("| (4) Update Offer.                                              |");
        System.out.println("| (5) Update Office.                                             |");
        System.out.println("|----------------------------------------------------------------|");

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

    private static int chooseUpdatePropList() {
        boolean valid = false;
        int choice = 0;

        System.out.println(" ________________________________________________________________ ");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("| (1) Update Agent.                                              |");
        System.out.println("| (2) Update List Price.                                         |");
        System.out.println("| (3) Update Description.                                        |");
        System.out.println("| (4) Update Area.                                               |");
        System.out.println("| (5) Update Address.                                            |");
        System.out.println("| (6) Update City.                                               |");
        System.out.println("| (7) Update State.                                              |");
        System.out.println("| (8) Update ZIP.                                                |");
        System.out.println("|----------------------------------------------------------------|");

        while (valid == false) {
            choice = s.nextInt();
            if (choice < 9 && choice > 0)
                valid = true;
            else {
                System.out.println("| Invalid value entered. Please enter again.                      |");
            }
        }

        return choice;
    }

    private static int chooseUpdateClientList() {
        boolean valid = false;
        int choice = 0;

        System.out.println(" ________________________________________________________________ ");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("| (1) Update First Name.                                         |");
        System.out.println("| (2) Update Last Name.                                          |");
        System.out.println("| (3) Update Phone Number.                                       |");
        System.out.println("| (4) Update Agent ID.                                           |");
        System.out.println("| (5) Update Address.                                            |");
        System.out.println("| (6) Update City.                                               |");
        System.out.println("| (7) Update State.                                              |");
        System.out.println("| (8) Update ZIP.                                                |");
        System.out.println("|----------------------------------------------------------------|");

        while (valid == false) {
            choice = s.nextInt();
            if (choice < 9 && choice > 0)
                valid = true;
            else {
                System.out.println("| Invalid value entered. Please enter again.                      |");
            }
        }

        return choice;
    }

    private static int chooseUpdateAgentList() {
        boolean valid = false;
        int choice = 0;

        System.out.println(" ________________________________________________________________ ");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("| (1) Update First Name.                                         |");
        System.out.println("| (2) Update Last Name.                                          |");
        System.out.println("| (3) Update Phone Number.                                       |");
        System.out.println("| (4) Update Manager ID.                                         |");
        System.out.println("| (5) Update Primary Office ID.                                  |");
        //should I really give agents the ability to change these? probably not.
        System.out.println("| (6) Update Commission.                                         |");
        System.out.println("| (7) Update Salary.                                             |");
        //
        System.out.println("| (8) Update Address.                                            |");
        System.out.println("| (9) Update City.                                               |");
        System.out.println("| (10) Update State.                                             |");
        System.out.println("| (11) Update ZIP.                                               |");
        System.out.println("|----------------------------------------------------------------|");

        while (valid == false) {
            choice = s.nextInt();
            if (choice < 12 && choice > 0)
                valid = true;
            else {
                System.out.println("| Invalid value entered. Please enter again.                      |");
            }
        }

        return choice;
    }

    private static int chooseUpdateOfferList() {
        boolean valid = false;
        int choice = 0;

        System.out.println(" ________________________________________________________________ ");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("| (1) Update the Property ID.                                    |");
        System.out.println("| (2) Update Amount Offered.                                     |");
        System.out.println("| (3) Accept an offer.                                           |");
        System.out.println("| (4) Decline an offer.                                          |");
        System.out.println("|----------------------------------------------------------------|");

        while (valid == false) {
            choice = s.nextInt();
            if (choice < 12 && choice > 0)
                valid = true;
            else {
                System.out.println("| Invalid value entered. Please enter again.                      |");
            }
        }

        return choice;
    }

    private static int chooseUpdateOfficeList() {
        boolean valid = false;
        int choice = 0;

        System.out.println(" ________________________________________________________________ ");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("| (1) Update the Manager ID.                                     |");
        System.out.println("| (2) Update Address.                                            |");
        System.out.println("| (3) Update City.                                               |");
        System.out.println("| (4) Update State.                                              |");
        System.out.println("| (5) Update ZIP.                                                |");
        System.out.println("|----------------------------------------------------------------|");

        while (valid == false) {
            choice = s.nextInt();
            if (choice < 12 && choice > 0)
                valid = true;
            else {
                System.out.println("| Invalid value entered. Please enter again.                      |");
            }
        }

        return choice;
    }

    private static void updateData(Connection con) {
        //System.out.println("|----------------------------------------------------------------|");
        System.out.println("|     What would you like to update?                             |");
        int updateType = chooseUpdateList();
        int updateSubType = 0;
        switch(updateType) {
            case 1:
                updateSubType = chooseUpdatePropList();
                System.out.print("Enter the Property ID to be updated: ");
                int propID = s.nextInt();
                switch(updateSubType) {
                    case 1: {
                        //update Agent
                        s.nextLine();
                        System.out.print("Enter the new Agent ID: ");
                        String agent = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE property SET agenttaxid = '?' WHERE propertyid = ?;");
                            update.setString(1, agent);
                            update.setInt(2, propID);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update agent ID.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 2: {
                        //update List Price
                        System.out.print("Enter the new List Price: ");
                        int price = s.nextInt();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE property SET listprice = ? WHERE propertyid = ?;");
                            update.setInt(1, price);
                            update.setInt(2, propID);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update list price.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 3: {
                        //update Description
                        s.nextLine();
                        System.out.print("Enter the new description: ");
                        String desc = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE property SET descripition = '?' WHERE propertyid = ?;");
                            update.setString(1, desc);
                            update.setInt(2, propID);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update description.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 4: {
                        //update Area
                        System.out.print("Enter the new area in sq. ft.: ");
                        int area = s.nextInt();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE property SET area = ? WHERE propertyid = ?;");
                            update.setInt(1, area);
                            update.setInt(2, propID);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update area.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 5: {
                        //update Address
                        s.nextLine();
                        System.out.print("Enter the new address: ");
                        String address = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET streetaddress = '?' WHERE addressid = (SELECT addressid FROM property WHERE propertyid = ?);");
                            update.setString(1, address);
                            update.setInt(2, propID);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update address.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 6: {
                        //update City
                        s.nextLine();
                        System.out.print("Enter the new city: ");
                        String city = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET city = '?' WHERE addressid = (SELECT addressid FROM property WHERE propertyid = ?);");
                            update.setString(1, city);
                            update.setInt(2, propID);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update city.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 7: {
                        //update State
                        s.nextLine();
                        System.out.print("Enter the new state: ");
                        String state = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET state = '?' WHERE addressid = (SELECT addressid FROM property WHERE propertyid = ?);");
                            update.setString(1, state);
                            update.setInt(2, propID);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update state.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 8: {
                        //update ZIP
                        s.nextLine();
                        System.out.print("Enter the new ZIP: ");
                        String zip = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET zipcode = '?' WHERE addressid = (SELECT addressid FROM property WHERE propertyid = ?);");
                            update.setString(1, zip);
                            update.setInt(2, propID);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update ZIP.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    default:
                        //something's gone wrong...
                }
            case 2:
                updateSubType = chooseUpdateClientList();
                System.out.print("Enter the tax id of the client to be updated: ");
                String clientTaxid = s.nextLine();
                switch(updateSubType) {
                    case 1: {
                        //update first
                        System.out.print("Enter the new first name: ");
                        String first = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE person SET firstname = '?' WHERE taxid = '?';");
                            update.setString(1, first);
                            update.setString(2, clientTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update first name.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 2: {
                        //update last
                        System.out.print("Enter the new last name: ");
                        String last = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE person SET lastname = '?' WHERE taxid = '?';");
                            update.setString(1, last);
                            update.setString(2, clientTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update last name.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 3: {
                        //update phone
                        System.out.print("Enter the new phone number: ");
                        String phone = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE person SET phonenumber = '?' WHERE taxid = '?';");
                            update.setString(1, phone);
                            update.setString(2, clientTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update phone number.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 4: {
                        //update agent
                        System.out.print("Enter the tax ID of the customer's new agent: ");
                        String agent = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE client SET agenttaxid = '?' WHERE taxid = '?';");
                            update.setString(1, agent);
                            update.setString(2, clientTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update the client's agent.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 5: {
                        //update address
                        System.out.print("Enter the client's new address: ");
                        String address = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET streetaddress = '?' WHERE addressid = (SELECT addressid FROM person WHERE taxid = '?');");
                            update.setString(1, address);
                            update.setString(2, clientTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update address.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 6: {
                        //update city
                        System.out.print("Enter the client's new city: ");
                        String city = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET city = '?' WHERE addressid = (SELECT addressid FROM person WHERE taxid = '?');");
                            update.setString(1, city);
                            update.setString(2, clientTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update city.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 7: {
                        //update state
                        System.out.print("Enter the client's new state: ");
                        String state = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET state = '?' WHERE addressid = (SELECT addressid FROM person WHERE taxid = '?');");
                            update.setString(1, state);
                            update.setString(2, clientTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update state.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 8: {
                        //update ZIP
                        System.out.print("Enter the client's new ZIP: ");
                        String zip = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET zipcode = '?' WHERE addressid = (SELECT addressid FROM person WHERE taxid = '?');");
                            update.setString(1, zip);
                            update.setString(2, clientTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update zip code.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    default:
                        //something's gone wrong...
                }
            case 3:
                updateSubType = chooseUpdateAgentList();
                System.out.print("Enter the tax id of the agent to be updated: ");
                String agentTaxid = s.nextLine();
                switch(updateSubType) {
                    case 1: {
                        //first
                        System.out.print("Enter the new first name: ");
                        String first = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE person SET firstname = '?' WHERE taxid = '?';");
                            update.setString(1, first);
                            update.setString(2, agentTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update first name.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 2: {
                        //last
                        System.out.print("Enter the new last name: ");
                        String last = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE person SET lastname = '?' WHERE taxid = '?';");
                            update.setString(1, last);
                            update.setString(2, agentTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update last name.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 3: {
                        //phone
                        System.out.print("Enter the new phone number: ");
                        String phone = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE person SET phonenumber = '?' WHERE taxid = '?';");
                            update.setString(1, phone);
                            update.setString(2, agentTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update phone number.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 4: {
                        //manager
                        System.out.print("Enter the tax ID of the agent's new manager: ");
                        String manager = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE agent SET managertaxid = '?' WHERE taxid = '?';");
                            update.setString(1, manager);
                            update.setString(2, agentTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update the agent's manager.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 5: {
                        //office
                        System.out.print("Enter the new primary office ID: ");
                        int office = s.nextInt();
                        s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE agent SET primaryofficeid = ? WHERE taxid = '?';");
                            update.setInt(1, office);
                            update.setString(2, agentTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update the primary office.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 6: {
                        //commission
                        System.out.print("Enter the agent's new commission value (e.g 0.14): ");
                        float comm = s.nextFloat();
                        s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE agent SET commissionpercentage = ? WHERE taxid = '?';");
                            update.setFloat(1, comm);
                            update.setString(2, agentTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update the commission value.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 7: {
                        //salary
                        System.out.print("Enter the agent's new salary: ");
                        int salary = s.nextInt();
                        s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE agent SET salary = ? WHERE taxid = '?';");
                            update.setInt(1, salary);
                            update.setString(2, agentTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update the agent's salary.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 8: {
                        //address
                        System.out.print("Enter the agent's new address: ");
                        String address = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET streetaddress = '?' WHERE addressid = (SELECT addressid FROM person WHERE taxid = '?');");
                            update.setString(1, address);
                            update.setString(2, agentTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update address.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 9: {
                        //city
                        System.out.print("Enter the agent's new city: ");
                        String city = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET city = '?' WHERE addressid = (SELECT addressid FROM person WHERE taxid = '?');");
                            update.setString(1, city);
                            update.setString(2, agentTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update city.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 10: {
                        //state
                        System.out.print("Enter the agent's new state: ");
                        String state = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET state = '?' WHERE addressid = (SELECT addressid FROM person WHERE taxid = '?');");
                            update.setString(1, state);
                            update.setString(2, agentTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update state.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 11: {
                        //ZIP
                        System.out.print("Enter the agent's new ZIP: ");
                        String zip = s.nextLine();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET zipcode = '?' WHERE addressid = (SELECT addressid FROM person WHERE taxid = '?');");
                            update.setString(1, zip);
                            update.setString(2, agentTaxid);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update zip code.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    default:
                        //something's gone wrong...
                }
            case 4:
                updateSubType = chooseUpdateOfferList();
                System.out.print("Enter the offer id of the offer to be updated: ");
                int offerID = s.nextInt();
                switch(updateSubType) {
                    case 1: {
                        //propertyID
                        System.out.print("Enter the new property ID: ");
                        int offerPropID = s.nextInt();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE offer SET propertyid = ? WHERE offerid = ?;");
                            update.setInt(1, offerPropID);
                            update.setInt(2, offerID);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update property ID.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 2: {
                        //Amount
                        System.out.print("Enter the new offered amount: ");
                        int amount = s.nextInt();
                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE offer SET offeramount = ? WHERE offerid = ?;");
                            update.setInt(1, amount);
                            update.setInt(2, offerID);
                            update.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not update amount.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 3: {
                        //accept
                        try {
                            PreparedStatement accept = con.prepareStatement("UPDATE offer SET offerstatus = 'a' WHERE offerid = ?;");
                            accept.setInt(1, offerID);
                            accept.execute();

                            PreparedStatement dateUpdate = con.prepareStatement("UPDATE property SET selldate = GETDATE() WHERE propertyid = (SELECT propertyid FROM offer WHERE offerid = ?);");
                            dateUpdate.setInt(1, offerID);
                            dateUpdate.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not accept offer.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                        System.out.println("Offer " + offerID + " accepted!");
                    }
                    case 4: {
                        //decline
                        try {
                            PreparedStatement decline = con.prepareStatement("UPDATE offer SET offerstatus = 'd' WHERE offerid = ?;");
                            decline.setInt(1, offerID);
                            decline.execute();
                        } catch (SQLException e) {
                            System.out.println("Could not decline offer.");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                        System.out.println("Offer " + offerID + " declined.");
                    }
                    default:
                        //something's gone wrong...
                }
            case 5:
                updateSubType = chooseUpdateOfficeList();
                System.out.print("Enter the office ID to be updated: ");
                int officeID = s.nextInt();
                switch(updateSubType) {
                    case 1: {
                        //managerID
                        s.nextLine();
                        System.out.print("Enter the new manager's ID: ");
                        String manager = s.nextLine();

                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE office SET managertaxid = '?' WHERE officeid = ?;");
                            update.setString(1, manager);
                            update.setInt(2, officeID);
                        } catch (SQLException e) {
                            System.out.println("Could not update manager. ");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 2: {
                        //address
                        s.nextLine();
                        System.out.print("Enter the new address: ");
                        String address = s.nextLine();

                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET streetaddress = '?' WHERE addressid = (SELECT addressid FROM office WHERE officeid = ?);");
                            update.setString(1, address);
                            update.setInt(2, officeID);
                        } catch (SQLException e) {
                            System.out.println("Could not update address. ");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 3: {
                        //city
                        s.nextLine();
                        System.out.print("Enter the new city: ");
                        String city = s.nextLine();

                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET city = '?' WHERE addressid = (SELECT addressid FROM office WHERE officeid = ?);");
                            update.setString(1, city);
                            update.setInt(2, officeID);
                        } catch (SQLException e) {
                            System.out.println("Could not update city. ");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 4: {
                        //state
                        s.nextLine();
                        System.out.print("Enter the new state: ");
                        String state = s.nextLine();

                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET state = '?' WHERE addressid = (SELECT addressid FROM office WHERE officeid = ?);");
                            update.setString(1, state);
                            update.setInt(2, officeID);
                        } catch (SQLException e) {
                            System.out.println("Could not update state. ");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case 5: {
                        //ZIP
                        s.nextLine();
                        System.out.print("Enter the new ZIP: ");
                        String zip = s.nextLine();

                        try {
                            PreparedStatement update = con.prepareStatement("UPDATE address SET zipcode = '?' WHERE addressid = (SELECT addressid FROM office WHERE officeid = ?);");
                            update.setString(1, zip);
                            update.setInt(2, officeID);
                        } catch (SQLException e) {
                            System.out.println("Could not update zip code. ");
                            System.out.println("Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    default:
                        //something's gone wrong
                }
            default:
                //something's gone wrong...
        }
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
            if(rs.next())
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
        }
        catch (SQLException e)
        {
            System.err.println("Could not insert new client.");
            System.out.println("Error: " + e.getMessage());
        }
        //get first
        //get last
        //get taxid
        //get phone num
        //get address details
        //get agent taxid
        //can now insert client
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
            if(rs.next())
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
        }
        catch (SQLException e)
        {
            System.err.println("Could not insert new agent.");
            System.out.println("Error: " + e.getMessage());
        }
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
            if(rs.next())
                offerID = rs.getInt("newID");

            PreparedStatement offerPS = con.prepareStatement("INSERT INTO offer VALUES (?, ?, GETDATE(), ?, ?, 'p');");
            offerPS.setInt(1, offerID);
            offerPS.setInt(2, propID);
            offerPS.setString(3, buyerID);
            offerPS.setInt(4, offer);

            System.out.println("Offer #" + offerID + " added!");
        }
        catch (SQLException e)
        {
            System.err.println("Could not make the offer.");
            System.out.println("Error: " + e.getMessage());
        }
        //property made on
        //get current date value from the sql
        //tax ID of client
        //offer amount
        //no need to put in status, default to waiting
        //can now insert offer
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
            if(rs.next())
                officeID = rs.getInt("newID");

            int addressID = 0;

            rs = Query_Execution.executeQuery(con, "SELECT MAX(addressid)+1 AS newID FROM address;");
            if(rs.next())
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
        }
        catch (SQLException e)
        {
            System.err.println("Could not make the offer.");
            System.out.println("Error: " + e.getMessage());
        }
        //new manager?
        //add their taxID
        //get address details
        //new office can be created
    }
}
