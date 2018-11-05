import java.io.IOException;
import java.util.Scanner;

public class demoUI {
	static int accessLevel = -1;
	//-1 is no access

	String[] actions = {};
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Welcome to the Mior Mega Real Estate Company database access user interface!");
		System.out.println("Do you have administrator credentials?");

		System.out.println("Please type 'y' for yes, or 'n' for no!");

		if(s.nextLine().equals("y")){

			System.out.println("Please enter your administration credentials!");
			String password = "maximum_mior_";

			String attemptedPassword = s.nextLine();
			String staffID = "";

			if(attemptedPassword.contains(password)) {
				accessLevel = 9001;
				staffID = attemptedPassword.substring(13);
				System.out.println("--------- access granted staff member: " + staffID + "---------");
				System.out.println("We hope you're having a profitable day!");


				System.out.println("Would you like to enter the staff menu?");
				System.out.println("Please type 'y' for yes, or 'n' for no!");
				if(s.nextLine().equals("y")){
					staffMenu();
				}
				else {
					System.out.println("Well, than, goodbye!");
					System.exit(0);
				}

			}
			else {
				System.out.println("UNAUTHORIZED ENTRY, REPORTING TO FRAUD DEPARTMENT!\n");
			}
			System.out.println("HAVE A WONDERFUL DAY!\n");
			System.exit(0);

		}
		else {
			System.out.println("Hello Customer! Welcome!");
			System.out.println("We hope you're having a splendid day!");
			System.out.println();
			System.out.println();

			System.out.println("Would you like to enter the customer menu in order to explore our bountiful selection, and get the information you might need?");
			System.out.println("Please type 'y' for yes, or 'n' for no!");

			custMenu();
		}

	}
	//accessLevel
	//staff/mgr/admin = 9001
	//customer (read only) = -1
	public int getAccessLevel(String taxID){
		return accessLevel;

	}

	static void staffMenu() throws IOException, InterruptedException {
		String desiredAction = "";

		System.out.println("Welcome to the staff menu!");
		System.out.println("What would you like to do today? \nJust enter a command: ");

		desiredAction = s.nextLine();

		System.out.println();
		System.out.println("If I understand correctly, you're trying to " + desiredAction + ".");
		System.out.println("Do I understand your intentions correctly?");

		System.out.println("Please type 'y' for yes, or 'n' for no!");
		if(s.nextLine().equals("y")) {
			System.out.println("Awesome! I will get that ready for you as soon as possible!");




		}
		else
		{
			System.out.println("Well, would you like to try again?");
			System.out.println("Please type 'y' for yes, or 'n' for no!");
			if(s.nextLine().equals("y")) {
				staffMenu(); //is this recursion?
			}
			else {
				System.out.println("\n\n We're happy to have been here to help you do your job, if you need anything else, be sure to use our tool again later!");


			}


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
				System.out.println("\n\n We're happy to have been here to help you, if you need anything else, be sure to use our tool again later!");
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
		return new Integer(7);
		//input: property ID
		//output: 0 for success, -1 for error.
	}


}