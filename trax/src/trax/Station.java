package trax;

public class Station {
	int ID;
	String stationName;
	RailLine line;
	
	public Station(int id, String name, RailLine line) {
		this.ID=id;
		this.stationName=name;
		this.line = line;
	}
	
	public int getID() {
		return ID;
	}
	public String getStationName() {
		return stationName;
	}
	public RailLine getRailLine() {
		return line;
	}
}
