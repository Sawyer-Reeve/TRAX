package trax;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Queue;


public class RailLine {
	
	String name;
	ArrayList<Station> stations;
	
	/**
	 * Constructor with a stationlist
	 * @param stations
	 * @param name
	 */
	RailLine(ArrayList<Station> stations, String name){
		this.stations=stations;
		this.name=name;
	}
	
	/**
	 * Empty constructor with name
	 * @param name
	 */
	RailLine(String name){
		this.name=name;
		this.stations=new ArrayList<Station>();
	}
	
	/**
	 * Empty constructor with name
	 * @param line name of this RailLine
	 */
	RailLine(RailLines line) {
		this.name = line.name();
		this.stations = new ArrayList<Station>();
	}
	
	/**
	 * @return list of stations
	 */
	public ArrayList<Station> getStations(){
		return stations;
	}
	
	/**
	 * for adding a stationlist to the created RailLine
	 * @param stationlist
	 */
	public void add(ArrayList<Station> stationlist) {
		stations.addAll(stationlist);
	}
	
	public void add(Queue<Station> stations) {
		for (Station s : stations)
			this.stations.add(s);
	}
	
	public void add(Station s) {
		stations.add(s);
	}
	

	public String[] getStationArray() {
		String[] stationArray = new String[stations.size()];
		for (int i=0;i<stations.size();i++) {
			stationArray[i]=stations.get(i).getStationName();
		}
		return stationArray;
	}
	
	/**
	 * prints a linear graph in txt file form with a provided station list
	 * @param stations ArrayList of each station
	 */
	public void printLinearRailGraphs() {
		
		//TODO: add functionality to write it to a text file + Merge with other graphs, or just do it manually
		System.out.println(this.stations.size());//our vertices
		System.out.println(this.stations.size()-1);//edges, since the lines themselves are linear its just vertices-1;
		for (int i=0;i<this.stations.size()-1;i++) {
			System.out.println(stations.get(i).getID() + " " + stations.get(i+1).getID());
			
		}
	}
	
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

