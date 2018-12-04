package UI;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class UserInterface_Command_Line {
	static int accessLevel = -1;
	static String input;
	static String input2;
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
                        staffMenu();
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
                    custMenu();
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

	static void staffMenu() throws IOException, InterruptedException {
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

		while(input.equalsIgnoreCase("n")) {
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
        }

        else if(desiredAction.equalsIgnoreCase("2")){
            System.out.println("Where would you like to Insert the data:");
            // View the different tables available in the database to INSERT data.
            // Have a function call insertData()
        }

        else if(desiredAction.equalsIgnoreCase("3")){
            System.out.println("Where would you like to Update the data:");
            // View the different tables available in the database to UPDATE some data.
            // Have a function call updateData()
        }

        else if(desiredAction.equalsIgnoreCase("4")){
            System.out.println("Where would you like to Delete the data:");
            // View the different tables available in the database to Delete data.
            // Have a function call deleteData()
        }

        else if (desiredAction.equalsIgnoreCase("5")) {
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
                    custMenu();
                } else if (input.equalsIgnoreCase("2") && input2.equalsIgnoreCase("y")) {
                    staffMenu();
                } else if (input.equalsIgnoreCase("3") && input2.equalsIgnoreCase("y")) {
                    switchOFF();
                }
            }
        }

        else if(desiredAction.equalsIgnoreCase("6")){
            switchOFF();
        }
    }
	static void custMenu() throws IOException, InterruptedException {
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
				custMenu(); //is this recursion?
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


}