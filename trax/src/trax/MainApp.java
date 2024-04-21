package trax;

import java.awt.Point;
import java.io.IOException;

import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;

/**
 * Main running application that initializes the rails, and calles the GUI methods
 * @author Sawyer Reeve + Chris Darnell
 */
public class MainApp {
	private static ST<Integer,String> ID_StationName_ST; 
	private static ST<String,Station> Name_Station_ST;
	private static ST<Integer,Station> ID_Station_ST;
	private static RailLine GreenLine;
	private static RailLine BlueLine;
	private static RailLine RedLine;
	private static RailLine FrontRunner;
	private static RailLine SLine;
	private static Station[] allStations;
	private static Integer total_time;
	
    public static void main(String[] args) throws IOException {
    	RailInitialization(); //below method, initializes all of the rail lines
    	new MainGUIframe(); //creates GUI
    }
    
    /**
     * Initalize all the rail line objects and populate the stations that they contain
     */
    public static void RailInitialization() {
		// initialization of railLines and stations with ID's

		// RailLines are initialized here(without the lists) in order to allow me to
		// assign each of them to the stations
		// when they are created
		ID_StationName_ST = new ST<Integer, String>();
		ID_Station_ST = new ST<Integer, Station>();
		Name_Station_ST = new ST<String, Station>();

		GreenLine = new RailLine(RailLines.GREEN_LINE);
		BlueLine = new RailLine(RailLines.BLUE_LINE);
		RedLine = new RailLine(RailLines.RED_LINE);
		FrontRunner = new RailLine(RailLines.FRONTRUNNER);
		SLine = new RailLine(RailLines.S_LINE);

		ID_StationName_ST.put(-1, null);
		ID_Station_ST.put(-1, null);

		// Blue Line Stations
		// iterates through and assigns an ID number for each Station and adds it to the
		// the list
		// as well as adding them to a hash map

		Queue<String> BlueStationsStrings = FileIO.getStationList(RailLines.BLUE_LINE);
		Queue<Point> BlueStationCoords = FileIO.getCoordList(RailLines.BLUE_LINE);
		Queue<Station> BlueLineStations = new Queue<>();
		int count = 0;
		for (String s : BlueStationsStrings) {
			Station station = new Station(count, s, BlueLine);
			BlueLineStations.enqueue(station);
			ID_StationName_ST.put(count, s);
			Name_Station_ST.put(s, station);
			ID_Station_ST.put(count, station);
			station.setPoint(BlueStationCoords.dequeue());
			count++;
		}

		// Red Line Stations
		Queue<String> RedStationsStrings = FileIO.getStationList(RailLines.RED_LINE);
		Queue<Point> RedStationCoords = FileIO.getCoordList(RailLines.RED_LINE);
		Queue<Station> RedLineStations = new Queue<>();
		for (String s : RedStationsStrings) {
			Station station = new Station(count, s, RedLine);
			RedLineStations.enqueue(station);
			ID_StationName_ST.put(count, s);
			Name_Station_ST.put(s, station);
			ID_Station_ST.put(count, station);
			station.setPoint(RedStationCoords.dequeue());
			count++;
		}


		// Green Line Stations
		Queue<String> GreenStationsStrings = FileIO.getStationList(RailLines.GREEN_LINE);
		Queue<Point> GreenStationCoords = FileIO.getCoordList(RailLines.GREEN_LINE);
		Queue<Station> GreenLineStations = new Queue<>();

		for (String s : GreenStationsStrings) {
			Station station = new Station(count, s, GreenLine);
			GreenLineStations.enqueue(station);
			ID_StationName_ST.put(count, s);
			Name_Station_ST.put(s, station);
			ID_Station_ST.put(count, station);
			station.setPoint(GreenStationCoords.dequeue());
			count++;
		}
    			
		// FrontRunner Stations
		Queue<String> FrontRunnerStrings = FileIO.getStationList(RailLines.FRONTRUNNER);
		Queue<Point> FrontRunnerCoords = FileIO.getCoordList(RailLines.FRONTRUNNER);
		Queue<Station> FrontRunnerStations = new Queue<>();

		for (String s : FrontRunnerStrings) {
			Station station = new Station(count, s, FrontRunner);
			FrontRunnerStations.enqueue(station);
			ID_StationName_ST.put(count, s);
			Name_Station_ST.put(s, station);
			ID_Station_ST.put(count, station);
			station.setPoint(FrontRunnerCoords.dequeue());
			count++;
		}

		// S Line stations
		Queue<String> SLineStrings = FileIO.getStationList(RailLines.S_LINE);
		Queue<Point> SLineCoords = FileIO.getCoordList(RailLines.S_LINE);
		Queue<Station> SLineStations = new Queue<>();

		for (String s : SLineStrings) {
			Station station = new Station(count, s, SLine);
			SLineStations.enqueue(station);
			ID_StationName_ST.put(count, s);
			Name_Station_ST.put(s, station);
			ID_Station_ST.put(count, station);
			station.setPoint(SLineCoords.dequeue());
			count++;
		}
    			
		// here we're just adding the list of stations to each RailLine object
		FrontRunner.add(FrontRunnerStations);
		BlueLine.add(BlueLineStations);
		GreenLine.add(GreenLineStations);
		RedLine.add(RedLineStations);
		SLine.add(SLineStations);

		//end of initialization for the Stations and rails

		//--------------------------------PRINT STATEMENTS---------------------------------------
		
		System.out.println(BlueLine);
		System.out.println(RedLine);
		System.out.println(GreenLine);
		System.out.println(FrontRunner);
		System.out.println(SLine);
		System.out.println();

	}

	/**
	 * Returns the Blue line object
	 * @return BlueLine RailLine item
	 */
	public static RailLine getBlueLine() {
		return BlueLine;
	}

	/**
	 * Returns the Red line object
	 * @return RedLine RailLine item
	 */
	public static RailLine getRedLine() {
		return RedLine;
	}

	/**
	 * Returns the Green line object
	 * @return GreenLine RailLine item
	 */
	public static RailLine getGreenLine() {
		return GreenLine;
	}

	/**
	 * Returns the FrontRunner object
	 * @return FrontRunner RailLine item
	 */
	public static RailLine getFrontRunner() {
		return FrontRunner;
	}

	/**
	 * Returns the S line object
	 * @return SLine RailLine item
	 */
	public static RailLine getSLine() {
		return SLine;
	}
	
	/**
	 * used to return an array of unique strings for the drop down menu displays
	 * @return array of unique strings
	 */
	public static String[] allStationsStrings() {
		
		//joins all Station strings into single queue
		Queue<String> strings = new Queue<String>();
		
		for (String s : BlueLine.getStationArray()) {
			strings.enqueue(s);
		}
		for (String s : RedLine.getStationArray()) {
			strings.enqueue(s);
		}
		for (String s : GreenLine.getStationArray()) {
			strings.enqueue(s);
		}
		for (String s : FrontRunner.getStationArray()) {
			strings.enqueue(s);
		}
		for (String s : SLine.getStationArray()) {
			strings.enqueue(s);
		}

		//removes duplicates
		SET<String> allStationsSet = new SET<String>();
		for (String s : strings) {
			allStationsSet.add(s);
		}

		int array_size = allStationsSet.size();
		String[] stationsArray = new String[array_size];
		
		int i=0;
		for (String s : allStationsSet) {
			stationsArray[i] = s;
			i++;
		}
		Quick.sort(stationsArray);
		return stationsArray;
		
	}
	
	/**
	 * @return the Name-Station Symbol Table
	 */
	public static ST<String,Station> getName_Station_ST(){
		return Name_Station_ST;
	} 
	
	/**
	 * @return the ID-Station Name Symbol Table
	 */
	public static ST<Integer,String> getID_StationName_ST(){
		return ID_StationName_ST;
	}
	
	/**
	 * @return the ID-Station Symbol Table
	 */
	public static ST<Integer,Station> getID_Station_ST(){
		return ID_Station_ST;
	}
	
	/**
	 * @return an array of all station objects
	 */
	public static Station[] getAllStations() {
		return allStations;
	}
	
	public static Integer getTotal_Time() {
		return total_time;
	}
	
	/**
	 * route uses Dijkstra's algorithm to find the shortest path between two points on a weighted graph,
	 * in this case the weight graph is the trax map with the weights representing the times between points
	 * @param start starting point
	 * @param destination ending point
	 * @param G provided edge weighted graph
	 * @return iterable Edge objects that represent the lowest weighted path
	 */
	public static Iterable<Edge> route(Station start, Station destination, EdgeWeightedGraph G) {
		DijkstraUndirectedSP graph = new DijkstraUndirectedSP(G, start.getID());

		total_time = (int) graph.distTo(destination.getID());

		return graph.pathTo(destination.getID());
	}
}
