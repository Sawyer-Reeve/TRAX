package trax;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Edge;

//Im not sure if this was supposed to be a class for individual lines between two stations, or entire lines like green,red,blue
public class RailLine {
	
	String name;
	Schedule schedule;//TODO: add schedule info/getters
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
	
	/*//was going to be used to call the arrays for the combo boxes
	public String[] getStationArray() {
		String[] stationArray = new String[stations.size()];
		for (int i=0;i<stations.size();i++) {
			stationArray[i]=stations.get(i).getStationName();
		}
		return stationArray;
	}
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

