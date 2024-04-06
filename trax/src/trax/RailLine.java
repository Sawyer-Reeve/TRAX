package trax;
import edu.princeton.cs.algs4.Edge;

//Im not sure if this was supposed to be a class for individual lines between two stations, or entire lines like green,red,blue
public class RailLine extends Edge {
	
	String name;
	Schedule schedule; /* is this just the time between stations? 
	we could potentially just use an int/double to represent the number of minutes*/
	
	// this is just an overridden method from Edge, I assume our weight will be time/schedule
	// our vertexes can just be a station ID, or we could have it as the Station object itself
	public RailLine(int v, int w, double weight) {
		super(v, w, weight);
		// TODO Auto-generated constructor stub
	}
	
	
	
}

