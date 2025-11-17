// Chris Welch CSCE 145-004
// This will be the super class from which the other two (CruiseShip and CargoShip) will inherit
public class Ship {

	// Create private instance variables for encapsulation
	private String shipName;
	private String launchDate;
	// Create a constant string to serve as a default launch date if one of the rules for setting a launch date are violated
	public static final String DEFDATE = "01/01/1990";
	
	/* Default constructor.  Unknown makes it clear if the value is set by the default constructor.  Doesn't use constant DEFDATE so
	 * it can be determined whether the value comes from default constructor or the setLaunchDate method due to an invalid entry.
	 */
	public Ship() {
		this.shipName = "Unknown";
		this.launchDate = "Unknown";
	}
	
	// Parameterized constructor.  Uses the set methods so that range validations can be applied without having to re-type the code
	public Ship(String xShipName, String xLaunchDate) {
		this.setShipName(xShipName);
		this.setLaunchDate(xLaunchDate);
	}
	
	// Accessors.  These simply return the value of the instance variable if requested.
	public String getShipName() {
		return this.shipName;
	}
	
	public String getLaunchDate() {
		return this.launchDate;
	}
	
	// Mutators
	// Ship name has no range checking, any string is allowed to be the name of the ship.
	public void setShipName(String xShipName) {
		this.shipName = xShipName;
	}
	
	/* The setLaunchDate method has several checks for the date entry to confirm its valid.  The checks are, in order:
	 * 1) Check to see if the date string does not have a '/' at the third index OR does not have a '/' at the sixth index OR
	 * the string length is not 10.  If any are true, invalid entry and set default date
	 * 2) Convert the string indexes 0 & 1 to an int and check if <= zero or > 12.  if true, invalid month, set default date
	 * 3) Convert the string indexes 4 & 5 to an int and check if <= zero or >31.  if true, invalid day, set default date
	 * 4) Convert the string indexes 6, 7, 8, & 9 to an int and check if < 1990 or > 2019.  if true, invalid year, set defualt date
	 * 5) Convert the string indexes 0 & 1 to an int and check if month is Apr, June, Sept, or Nov.  if true, then convert the string
	 * indexes 4 & 5 to an int and check if <= zero or >30.  If true, invalid date since these months only have 30 days, set default date.
	 * 6) Convert the string indexes 0 & 1 to an int and check if month is Feb.  if true, then convert the string
	 * indexes 4 & 5 to an int and check if <= zero or >28.  If true, invalid date since this month only has 28 days, set default date.  Note,
	 * leap year days are not allowed and the user is informed to enter the date as 02/28/YYYY instead.
	 * If all of the above checks are false, then the user entered date is saved as the Launch Date.
	 */
	public void setLaunchDate(String xLaunchDate) {
		if (!xLaunchDate.substring(2,3).equals("/") || !xLaunchDate.substring(5,6).equals("/") || xLaunchDate.length() != 10 ) 
			{
				System.out.println("Invalid entry for date.  Use MM/DD/YYYY format.  Date set to default date 01/01/1990.");
				this.launchDate = DEFDATE;
			} 
			else if (this.convertMonth(xLaunchDate) <= 0 || this.convertMonth(xLaunchDate) > 12 ) 
			{
				System.out.println("Invalid entry for date, the month cannot be zero, negative, or greater than 12. Date set to default date 01/01/1990.");
				this.launchDate = DEFDATE;
			} 
			else if (this.convertDay(xLaunchDate) <= 0 || this.convertDay(xLaunchDate) > 31 ) 
			{
				System.out.println("Invalid entry for date, the day cannot be zero, negative, or greater than 31. Date set to default date 01/01/1990.");
				this.launchDate = DEFDATE;
			} 
			else if (this.convertYear(xLaunchDate) > 2019 || this.convertYear(xLaunchDate) < 1990)
			{
				System.out.println("Invalid entry for year.  Only launch dates between 01/01/1990 and 12/31/2019 are accepted.");
				System.out.println("Date set to default date 01/01/1990.");
				this.launchDate = DEFDATE;
			}
			else if (this.convertMonth(xLaunchDate) == 4 || this.convertMonth(xLaunchDate) == 6 || 
					this.convertMonth(xLaunchDate) == 9 || this.convertMonth(xLaunchDate) == 11) 
			{
				if (this.convertDay(xLaunchDate) <= 0 || this.convertDay(xLaunchDate) > 30) 
				{
					System.out.println("Invalid entry for date, the day cannot be zero, negative, or greater than 30 for Apr, June, Sept, or Nov.");
					System.out.println("Date set to default date 01/01/1990.");
					this.launchDate = DEFDATE;	
				} 
				else
				{
					this.launchDate = xLaunchDate;
				}
			} 
			else if (this.convertMonth(xLaunchDate) == 2) 
			{
				if (this.convertDay(xLaunchDate) <= 0 || this.convertDay(xLaunchDate) > 28) 
					{
						System.out.println("Invalid entry for date, the day cannot be zero, negative, or greater than 28 for Feb.\n");
						System.out.println("Note: If the launch date was Feb 29th, then enter Feb 28th instead. Leap year dates are not valid.");
						System.out.println("Date set to default date 01/01/1990.");
						this.launchDate = DEFDATE;
					}
					else
					{
						this.launchDate = xLaunchDate;
					}
			} 
			else
			{
			this.launchDate = xLaunchDate;
			}
	}
	
	// toString method returns the print out requested.
	public String toString() {
		return "Ship name: "+this.getShipName()+"\nLaunch date: "+this.getLaunchDate();
	}
	
	/* I built my own parsers for converting the year from the entered string value into an int value.  
	 * We learned about the parse command after I had already built all of this, so I left my versions here.  It was a good exercise.
	 * The sequence is:
	 * 1) Declare an integer variable year to contain the final year value
	 * 2) Declare an integer value to store the value of the digit being analyzed
	 * 3) Declare a char variable and assign the value using charAt.  Since the String for date is fixed at MM/DD/YYYY, the indexex os conern
	 * for year are 6, 7, 8, & 9.  6 is called first.
	 * 4) SWITCH is used to check which char is at index 6 and when it finds its match, assigns the previously declared int variable the int value
	 * 5) Repeats steps 3 and 4 for string indexes 7, 8, and 9 so each digit of the year is now saved in int form.
	 * 6) Assigns the int variable year its value based on multiplying each digit by its place (1st digit * 1000, 2nd digit * 100, etc)
	 * 7) returns the int value year.
	 * This allowed me to range check inside the setLaunchDate using mathematical operators.
	 */
	public int convertYear(String xDate) {
		int year = 0;
		int digit1i = 0;
		char digit1c = xDate.charAt(6);
		switch (digit1c) {
			case '1':
				digit1i = 1;
				break;
			case '2':
				digit1i = 2;
				break;
			case '3':
				digit1i = 3;
				break;
			case '4':
				digit1i = 4;
				break;
			case '5':
				digit1i = 5;
				break;
			case '6':
				digit1i = 6;
				break;
			case '7':
				digit1i = 7;
				break;
			case '8':
				digit1i = 8;
				break;
			case '9':
				digit1i = 9;
				break;
			case '0':
				digit1i = 0;
				break;
			default:
				System.out.println("There was an error converting the 1st digit of year.");
		}
		int digit2i = 0;
		char digit2c = xDate.charAt(7);
		switch (digit2c) {
			case '1':
				digit2i = 1;
				break;
			case '2':
				digit2i = 2;
				break;
			case '3':
				digit2i = 3;
				break;
			case '4':
				digit2i = 4;
				break;
			case '5':
				digit2i = 5;
				break;
			case '6':
				digit2i = 6;
				break;
			case '7':
				digit2i = 7;
				break;
			case '8':
				digit2i = 8;
				break;
			case '9':
				digit2i = 9;
				break;
			case '0':
				digit2i = 0;
				break;
			default:
				System.out.println("There was an error converting the 2nd digit of year.");
		}
		int digit3i = 0;
		char digit3c = xDate.charAt(8);
		switch (digit3c) {
			case '1':
				digit3i = 1;
				break;
			case '2':
				digit3i = 2;
				break;
			case '3':
				digit3i = 3;
				break;
			case '4':
				digit3i = 4;
				break;
			case '5':
				digit3i = 5;
				break;
			case '6':
				digit2i = 6;
				break;
			case '7':
				digit3i = 7;
				break;
			case '8':
				digit3i = 8;
				break;
			case '9':
				digit3i = 9;
				break;
			case '0':
				digit3i = 0;
				break;
			default:
				System.out.println("There was an error converting the 3rd digit of year.");
		}
		int digit4i = 0;
		char digit4c = xDate.charAt(9);
		switch (digit4c) {
			case '1':
				digit4i = 1;
				break;
			case '2':
				digit4i = 2;
				break;
			case '3':
				digit4i = 3;
				break;
			case '4':
				digit4i = 4;
				break;
			case '5':
				digit4i = 5;
				break;
			case '6':
				digit4i = 6;
				break;
			case '7':
				digit4i = 7;
				break;
			case '8':
				digit4i = 8;
				break;
			case '9':
				digit4i = 9;
				break;
			case '0':
				digit4i = 0;
				break;
			default:
				System.out.println("There was an error converting the 4th digit of year.");
		}
		year = (digit1i*1000) + (digit2i*100) + (digit3i*10) + (digit4i*1);
		return year;
	}
	
	/* This method is functionally the same as the convertYear method above.  It looks at the indexes for the day inside MM/DD/YYYY.  Since the 
	 * day only has two digits, only two SWITCHs are needed and the final calc for day is just the tens place plus the ones place.
	 * This allowed me to range check inside the setLaunchDate using mathematical operators.
	 */
	public int convertDay(String xDate) {
		int day = 0;
		int digit1i = 0;
		char digit1c = xDate.charAt(3);
		switch (digit1c) {
			case '1':
				digit1i = 1;
				break;
			case '2':
				digit1i = 2;
				break;
			case '3':
				digit1i = 3;
				break;
			case '4':
				digit1i = 4;
				break;
			case '5':
				digit1i = 5;
				break;
			case '6':
				digit1i = 6;
				break;
			case '7':
				digit1i = 7;
				break;
			case '8':
				digit1i = 8;
				break;
			case '9':
				digit1i = 9;
				break;
			case '0':
				digit1i = 0;
				break;
			default:
				System.out.println("There was an error converting the 1st digit of day.");
		}
		int digit2i = 0;
		char digit2c = xDate.charAt(4);
		switch (digit2c) {
			case '1':
				digit2i = 1;
				break;
			case '2':
				digit2i = 2;
				break;
			case '3':
				digit2i = 3;
				break;
			case '4':
				digit2i = 4;
				break;
			case '5':
				digit2i = 5;
				break;
			case '6':
				digit2i = 6;
				break;
			case '7':
				digit2i = 7;
				break;
			case '8':
				digit2i = 8;
				break;
			case '9':
				digit2i = 9;
				break;
			case '0':
				digit2i = 0;
				break;
			default:
				System.out.println("There was an error converting the 2nd digit of day.");
		}
		day = (digit1i*10) + (digit2i*1);
		return day;
	}
	
	/* This method is functionally the same as the convertDay method above.  It looks at the indexes for the month inside MM/DD/YYYY.  Since the 
	 * month only has two digits, only two SWITCHs are needed and the final calc for month is just the tens place plus the ones place.
	 * This allowed me to range check inside the setLaunchDate using mathematical operators.
	 */
	public int convertMonth(String xDate) {
		int month = 0;
		int digit1i = 0;
		char digit1c = xDate.charAt(0);
		switch (digit1c) {
			case '1':
				digit1i = 1;
				break;
			case '2':
				digit1i = 2;
				break;
			case '3':
				digit1i = 3;
				break;
			case '4':
				digit1i = 4;
				break;
			case '5':
				digit1i = 5;
				break;
			case '6':
				digit1i = 6;
				break;
			case '7':
				digit1i = 7;
				break;
			case '8':
				digit1i = 8;
				break;
			case '9':
				digit1i = 9;
				break;
			case '0':
				digit1i = 0;
				break;
			default:
				System.out.println("There was an error converting the 1st digit of month.");
		}
		int digit2i = 0;
		char digit2c = xDate.charAt(1);
		switch (digit2c) {
			case '1':
				digit2i = 1;
				break;
			case '2':
				digit2i = 2;
				break;
			case '3':
				digit2i = 3;
				break;
			case '4':
				digit2i = 4;
				break;
			case '5':
				digit2i = 5;
				break;
			case '6':
				digit2i = 6;
				break;
			case '7':
				digit2i = 7;
				break;
			case '8':
				digit2i = 8;
				break;
			case '9':
				digit2i = 9;
				break;
			case '0':
				digit2i = 0;
				break;
			default:
				System.out.println("There was an error converting the 2nd digit of month.");
		}
		month = (digit1i*10) + (digit2i*1);
		return month;
	}
} // Close class
