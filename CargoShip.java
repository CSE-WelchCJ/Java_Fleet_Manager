// Chris Welch CSCE 145-004
// This is a sub class for Ship and inherits from the Ship Class
public class CargoShip extends Ship{

	// Defines the instance variables pertinent to cargo ships
	private double tonnage;
	private double speed;
	
	// Default constructor for cargo ship.  Calls upon its super-class Ship to inherit those instance variables as well.
	public CargoShip() {
		// Calls on class Ship default constructor
		super();
		// zero is default tonnage so it is clear that the value came from the default constructor
		this.tonnage = 0;
		// zero is default speed so it is clear that the value came from the default constructor
		this.speed = 0;
	}
	
	// Parameterized constructor for cargo ship.  Calls upon its super-class Ship to inherit those instance variables as well
	public CargoShip(String xShipName, String xLaunchDate, double xTonnage, double xSpeed) {
		// Calls on class Ship parameterized constructor
		super(xShipName, xLaunchDate);
		// Utilizes the set methods so that appropriate range checking can be performed
		this.setTonnage(xTonnage);
		this.setSpeed(xSpeed);
	}
	
	// Accessors.  These simply return the requested instance variable
	public double getTonnage() {
		return this.tonnage;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	// mutators
	/* setTonnage method range checks the input to see if it is greater than zero.  No upper bound set nor required.
	 * If the value entered is invalid, informs user why and sets to a default of 1.
	 */
	public void setTonnage(double xTonnage) {
		if (xTonnage > 0 )
		{
			this.tonnage = xTonnage;
		}
		else
		{
			System.out.println("DWT cannot be zero or negative.  Set to default 1.0");
			this.tonnage = 1.0;
		}
	}
	
	/* setSpeed method range checks the input to see if it is greater than zero.  No upper bound set nor required.
	 * If the value entered is invalid, informs user why and sets to a default of 1.
	 */
	public void setSpeed(double xSpeed) {
		if (xSpeed > 0 )
		{
			this.speed = xSpeed;
		}
		else
		{
			System.out.println("Speed cannot be zero or negative.  Set to default 1.0");
			this.speed = 1.0;
		}
	}
	
	// toString method returns the print out requested.  This method will override the toString method in Ship if called
	public String toString() {
		return "Ship name: "+this.getShipName()+"\nDead weight tonnage: "+this.getTonnage()+" DWT\nMaximum speed: "+this.getSpeed()+" kts";
	}
} // close class
