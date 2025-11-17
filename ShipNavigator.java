// Chris Welch CSCE 145-004

import java.util.Scanner;
public class ShipNavigator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create an object to accept keyboard input
		Scanner key = new Scanner(System.in);
		// Identifies the purpose of the program for the user
		System.out.println("Welcome to the Fleet Tracker! Here you will enter some details into the Fleet database for three ships.");
		System.out.println("Your current fleet has 2 cruise ships and 1 cargo ship.  Please enter the details when prompted.");
		// boolean variable declared to control the DO-WHILE loop.  DO-WHILE used because we want it to run at least once
		boolean exit = false;
		do {
			// Requests and accepts user input for ship name
			System.out.println("Enter the name of the ship:");
			String name = key.nextLine();
			// Requests and accepts user input for launch date.  required format is displayed
			System.out.println("Enter the ship's launch date (format: mm/dd/yyyy)");
			String date = key.nextLine();
			// Requests and accepts user input for ship type.  Needed to determine which type of object to build
			System.out.println("Is this a 'cruise ship' or 'cargo ship'?");
			String type = key.nextLine();
			/* Checks if the type of ship was entered correctly.  If not entered correctly, a message informs the user
			 * and the code skips the object building process and goes straight to the end of the loop
			 */
			if (!type.equalsIgnoreCase("cruise ship") && !type.equalsIgnoreCase("cargo ship")) 
			{
				System.out.println("Invalid entry, please try again or exit");
			}
			// if cruise ship was selected, goes down this path:
			if (type.equalsIgnoreCase("cruise ship"))
			{
				// Requests and accepts user input for passenger capacity
				System.out.println("Enter the passenger capacity: ");
				int capacity = key.nextInt();
				// next line clears the input following the previous integer capture
				key.nextLine();
				// Requests and accepts user input for crew members
				System.out.println("Enter the number of crew members: ");
				int crew = key.nextInt();
				// next line clears the input following the previous integer capture
				key.nextLine();
				System.out.println("Creating the cruise ship...\nDone!  Printing the details:");
				// Creates the object of type CruiseShip which inherits from Ship
				CruiseShip cs1 = new CruiseShip(name, date, capacity, crew);
				// Prints ship info using CruiseShip toString method which overrides the Ship method of the same name.
				System.out.println(cs1.toString());
			}
			// if cargo ship was selected, goes down this path:
			if (type.equalsIgnoreCase("cargo ship"))
			{
				// Requests and accepts user input for tonnage
				System.out.println("Enter the dead weight tonnage: ");
				double tonnage = key.nextDouble();
				// next line clears the input following the previous double capture
				key.nextLine();
				// Requests and accepts user input for speed
				System.out.println("Enter the maximum speed (knots): ");
				double speed = key.nextDouble();
				// next line clears the input following the previous double capture
				key.nextLine();
				System.out.println("Creating the cargo ship...\nDone!  Printing the details:");
				// Creates the object of type CargoShip which inherits from Ship
				CargoShip sc1 = new CargoShip(name, date, tonnage, speed);
				// Prints ship info using CargoShip toString method which overrides the Ship method of the same name.
				System.out.println(sc1.toString());
			}
			System.out.println("Do you want to construct another ship?  Enter 'yes' or 'no':");
			String answer = key.nextLine();
			// controls the boolean variable controlling the loop.  If invalid or no, program ends.  if yes, program loops back
			if (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"))
			{
				System.out.println("Invalid selection.");
				exit = true;
				}
				else if (answer.equalsIgnoreCase("yes"))
				{
					exit = false;
				}
				else if (answer.equalsIgnoreCase("no"))
				{
					exit = true;
				}
		} while (exit == false);
		// Program exit message and command
		System.out.println("Exiting program.");
		System.exit(0);	
	} //  close main

} // close class
