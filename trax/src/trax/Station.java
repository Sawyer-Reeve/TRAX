package trax;

public class Station {
	int ID;
	String stationName;
	//FIXME: I figure we could have each station have a value for the next and previous stations, that represent the time needed
	//between them, but there  might be a better way of doing it
	double next;
	double previous;
	
	public Station(int id, String name) {
		this.ID=id;
		this.stationName=name;
	}
	
	public int getID() {
		return ID;
	}
	public String getStationName() {
		return stationName;
	}

	@Override
	public String toString() {
		return "ID:" + getID() + " " + stationName;
	}
	
	

}
