package trax;
import edu.princeton.cs.algs4.Queue;

public class RailLine {

	private String name;
	private Queue<Station> stations;

	/**
	 * Empty constructor with name
	 * 
	 * @param line name of this RailLine
	 */
	public RailLine(RailLines line) {
		this.name = line.getName();
		this.stations = new Queue<>();
	}

	/**
	 * @return list of stations
	 */
	public Queue<Station> getStations() {
		return stations;
	}

	/**
	 * for adding a stationlist to the created RailLine
	 * 
	 * @param stationlist
	 */
	public void add(Queue<Station> stationlist) {
		for (Station s : stationlist)
			stations.enqueue(s);
	}

	/**
	 * getSTationArray returns a String array of all the stations on this rail line
	 * @return
	 */
	public String[] getStationArray() {
		String[] stationArray = new String[stations.size()];
		int i = 0;
		for (Station s : stations) {
			stationArray[i++] = s.getStationName();
		}
		return stationArray;
	}

	/**
	 * prints a linear graph in txt file form with a provided station list
	 * 
	 * @param stations ArrayList of each station
	 */
	public void printLinearRailGraphs() {

		System.out.println(this.stations.size());// our vertices
		System.out.println(this.stations.size() - 1);// edges, since the lines themselves are linear its just

		Station[] arry = new Station[stations.size()]; // vertices-1;
		for (int i = 0; i < this.stations.size() - 1; i++) {
			System.out.println(arry[i].getID() + " " + arry[i + 1].getID());
		}
	}

	/**
	 * @return returns railLines name
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String s = "";
		for (Station a : stations) {
			s += a.toString() + "\n";
		}
		return s;
	}

}
