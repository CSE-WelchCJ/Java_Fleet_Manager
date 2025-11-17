// Chris Welch CSCE 145-004
// This is a sub class for Ship and inherits from the Ship Class
public class CruiseShip extends Ship{

	// Defines the instance variables pertinent to cruise ships
	private int passCapacity;
	private int crewMembers;
	
	// Default constructor for cruise ship.  Calls upon its super-class Ship to inherit those instance variables as well.
	public CruiseShip() {
		// Calls on class Ship default constructor
		super();
		// zero is default capacity so it is clear that the value came from the default constructor
		this.passCapacity = 0;
		// A ship always has at least a captain, so default crew is 1
		this.crewMembers = 1;
	}
	
	// Parameterized constructor for cruise ship.  Calls upon its super-class Ship to inherit those instance variables as well
	public CruiseShip(String xShipName, String xLaunchDate, int xPassCapacity, int xCrewMembers) {
		// Calls on class Ship parameterized constructor
		super(xShipName, xLaunchDate);
		// Utilizes the set methods so that appropriate range checking can be performed
		this.setPassCapacity(xPassCapacity);
		this.setCrewMembers(xCrewMembers);
	}
	
	// accessors.  These simply return the requested instance variable
	public int getPassCapacity() {
		return this.passCapacity;
	}
	
	public int getCrewMembers() {
		return this.crewMembers;
	}
	
	// mutators
	/* setPassCapacity method range checks the input to see if it is greater than zero.  No upper bound set nor required.
	 * If the value entered is invalid, informs user why and sets to a default of 1.
	 */
	public void setPassCapacity(int xPassCapacity) {
		if (xPassCapacity > 0 ) 
		{
			this.passCapacity = xPassCapacity;
		}
		else
		{
			System.out.println("Passenger capacity cannot be zero or negative for a Cruise Ship. Set to default of 1.");
			this.passCapacity = 1;
		}
	}
	
	/* setCrewMembers method range checks the input to see if it is greater than zero.  No upper bound set nor required.
	 * If the value entered is invalid, informs user why and sets to a default of 1.
	 */
	public void setCrewMembers(int xCrewMembers) {
		if (xCrewMembers > 1 )
		{
			this.crewMembers = xCrewMembers;
		}
		else
		{
			System.out.println("The number of crew members can never be less than one, for there always must be at least a captain. Set to default of 1.");
			this.crewMembers = 1;
		}
	}
	
	// toString method returns the print out requested.  This method will override the toString method in Ship if called
	public String toString() {
		return "Ship name: "+this.getShipName()+"\nPassenger capacity: "+this.getPassCapacity()+"\nCrew members: "+this.getCrewMembers();
	}
} // Close class
