package trax;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;

/**
 * Main running application that initializes the rails, and calles the GUI methods
 * @author sawyerreeve + Chris Darnell
 */
public class MainApp {
	private static ST<Integer,String> railIDs; 
	private static ST<String,Station> stationSymbolTable;
	//private static SymbolTable<Integer,Station> railIDTable;
	private static RailLine GreenLine;
	private static RailLine BlueLine;
	private static RailLine RedLine;
	private static RailLine FrontRunner;
//	private static ArrayList<Station> allStationsList;
//	private static Station[] allStations;
	
    public static void main(String[] args) throws IOException {
    	
    	RailInitialization(); //below method, initializes all of the rail lines
    	new MainGUIframe();
         
    }
    
    //TODO:not sure if this needs to be its own class or if it fits here
    public static void RailInitialization() {
    	//initialization of railLines and stations with ID's
		
    			//RailLines are initialized here(without the lists) in order to allow me to assign each of them to the stations 
    			//when they are created
    			railIDs = new ST<Integer,String>();
    			
//    			GreenLine = new RailLine("Green Line");
//    			BlueLine = new RailLine("Blue Line");
//    			RedLine = new RailLine("Red Line");
//    			FrontRunner = new RailLine("FrontRunner");
    			
    			GreenLine = new RailLine(RailLines.GREEN_LINE);
    			BlueLine = new RailLine(RailLines.BLUE_LINE);
    			RedLine = new RailLine(RailLines.RED_LINE);
    			FrontRunner = new RailLine(RailLines.FRONTRUNNER);
    			
//    			allStationsList = new ArrayList<Station>();
    			stationSymbolTable = new ST<String,Station>();
    			

    			//Blue Line Stations
    			//iterates through and assigns an ID number for each Station and adds it to the the list
    			//as well as adding them to a hashmap
    			
    			Queue<String> BlueStationsStrings = FileIO.getStationList(RailLines.BLUE_LINE);
//    			ArrayList<Station> BlueLineStations = new ArrayList<Station>();
    			Queue<Station> BlueLineStations = new Queue<>();
    			int count = 0;
    			for (String s : BlueStationsStrings) {
    				Station station = new Station(count, s, BlueLine);
//    				BlueLineStations.add(station);
    				BlueLineStations.enqueue(station);
    				railIDs.put(count, s);
    				stationSymbolTable.put(s,station);
    				
    				count++;
    			}


    			// Red Line Stations
    			Queue<String> RedStationsStrings = FileIO.getStationList(RailLines.RED_LINE);
//    			ArrayList<Station> RedLineStations = new ArrayList<Station>();
    			Queue<Station> RedLineStations = new Queue<>();
    			for (String s : RedStationsStrings) {
    				Station station = new Station(count, s, RedLine);
//    				RedLineStations.add(station);
    				RedLineStations.enqueue(station);
    				railIDs.put(count, s);
    				stationSymbolTable.put(s,station);
    				count++;
    			}


    			// Green Line Stations
    			Queue<String> GreenStationsStrings = FileIO.getStationList(RailLines.GREEN_LINE);
//    			ArrayList<Station> GreenLineStations = new ArrayList<Station>();	
    			Queue<Station> GreenLineStations = new Queue<>();
    			
    			for (String s : GreenStationsStrings) {
    				Station station = new Station(count, s, GreenLine);
//    				GreenLineStations.add(station);
    				GreenLineStations.enqueue(station);
    				railIDs.put(count, s);
    				stationSymbolTable.put(s,station);
    				count++;
    			}

    			
    			//FrontRunner Stations
    			Queue<String> FrontRunnerStrings = FileIO.getStationList(RailLines.FRONTRUNNER);
//    			ArrayList<Station> FrontRunnerStations = new ArrayList<Station>();	
    			Queue<Station> FrontRunnerStations = new Queue<>();
    			
    			for (String s : FrontRunnerStrings) {
    				Station station = new Station(count, s, FrontRunner);
//    				FrontRunnerStations.add(station);
    				FrontRunnerStations.enqueue(station);
    				railIDs.put(count, s);
    				stationSymbolTable.put(s,station);
    				count++;	
    			}
    			//here we're just adding the list of stations to each RailLine object
    			FrontRunner.add(FrontRunnerStations);
    			BlueLine.add(BlueLineStations);
    			GreenLine.add(GreenLineStations);
    			RedLine.add(RedLineStations);
    			
    			//and then just adding them to a list of all stations	
    			
    			// XXX Is this even needed? Doesn't look like its used
//    			allStationsList.addAll(FrontRunnerStations);
//    			allStationsList.addAll(BlueLineStations);
//    			allStationsList.addAll(GreenLineStations);
//    			allStationsList.addAll(RedLineStations);

//    			allStations = new Station[BlueLineStations.size() + RedLineStations.size() 
//    					+ GreenLineStations.size() + FrontRunnerStations.size()];

    			//end of initialization for the Stations and rails

    			//--------------------------------------PRINT STATEMENTS--------------------------------------------------------
    			
    			System.out.println(BlueLine);
    			System.out.println(RedLine);
    			System.out.println(GreenLine);
    			System.out.println(FrontRunner);


    			System.out.println(railIDs.keys());
    			System.out.println();

    			for (String s : allStationsStrings()) {
    				System.out.println(s);
    			}
    			System.out.println();

    			//for printing to txt file
    			BlueLine.printLinearRailGraphs();
    			System.out.println();

    			//for printing to txt file
    			RedLine.printLinearRailGraphs();
    			System.out.println();

    			//for printing to txt file
    			GreenLine.printLinearRailGraphs();
    			System.out.println();

    			//for printing to txt file
    			FrontRunner.printLinearRailGraphs();
    		
    }

    
    
	
	public static RailLine getBlueLine() {
		return BlueLine;
	}
	
	public static RailLine getRedLine() {
		return RedLine;
	}
	
	public static RailLine getGreenLine() {
		return GreenLine;
	}
	
	public static RailLine getFrontRunner() {
		return FrontRunner;
	}
	
	//uses hashset to remove duplicate stations, is then fed into the drop down menus
	public static String[] allStationsStrings() {
		
		//joins all Station strings into single arraylist
		ArrayList<String> allStationsStrings = new ArrayList<String>();
		allStationsStrings.addAll(Arrays.asList(GreenLine.getStationArray()));
		allStationsStrings.addAll(Arrays.asList(BlueLine.getStationArray()));
		allStationsStrings.addAll(Arrays.asList(RedLine.getStationArray()));
		allStationsStrings.addAll(Arrays.asList(GreenLine.getStationArray()));
		
		//removes duplicates
		SET<String> allStationsSet = new SET<String>();
		for (String s : allStationsStrings) {
			allStationsSet.add(s);
		}
		//re-adds to lists and sorts by alphabetical order
		allStationsStrings.clear();
		
		for (String s :allStationsSet) {
			allStationsStrings.add(s);
		}
		
		Collections.sort(allStationsStrings, String.CASE_INSENSITIVE_ORDER);
		
		
		int array_size = allStationsSet.size();
		String[] stationsArray = new String[array_size];
		
		int i=0;
		for (String s : allStationsStrings) {
			stationsArray[i] = s;
			i++;
		}
		
		return stationsArray;
		
	}
	
	public static ST<String,Station> getStationHashMap(){
		return stationSymbolTable;
	} 
	
	public static ST<Integer,String> getRailIdHashmap(){
		return railIDs;
	}
	
	//this routing method takes in a start, destination, and graph and uses djikstras method to find the shortest route
	public static Iterable<Edge> route(Station start, Station destination, EdgeWeightedGraph G ) {
		DijkstraUndirectedSP graph = new DijkstraUndirectedSP(G, start.getID());
		return graph.pathTo(destination.getID());
	}
}
