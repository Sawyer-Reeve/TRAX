package trax;

public class Station {
	int ID;
	String stationName;
	
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
