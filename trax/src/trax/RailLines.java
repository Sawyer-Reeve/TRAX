package trax;

/**
 * Represents the names of the common TRAX rail lines
 * @author sawyerreeve + chrisdarnell
 */
public enum RailLines {
	BLUE_LINE ("Blue Line"), 
	RED_LINE ("Red Line"), 
	GREEN_LINE ("Green Line"), 
	FRONTRUNNER ("Front Runner");

	private final String name;
	
	RailLines(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the String representation of the name of this enum
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	// test client for unit testing
	public static void main(String[] args) {
		System.out.println(RailLines.BLUE_LINE.getName());
	}
}
