package trax;

import java.awt.Point;

/**
 * Station is a class that is used to hold the data for each rail station along the Trax lines, it implements comparable
 * ..in order to see if two stations on different lines with the same name are indeed the same station(i.e. 600 S on green
 * .. line is the same as 600 S on red line)
 */
public class Station implements Comparable<Station>{
	
	int ID;
	String stationName;
	RailLine line;
	Point coordinates;
	int x_coord;
	int y_coord;
	boolean greenLine;
	boolean redLine;
	boolean blueLine;
	boolean frontRunner;
	boolean sLine;
	
	public Station(int id, String name, RailLine line) {
		this.ID=id;
		this.stationName=name;
		this.line=line;
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
	public void setPoint(Point coord) {
		coordinates = coord;
		this.x_coord = (int) coord.getX();
		this.y_coord = (int) coord.getY();
		
	}
	public Point getPoint() {
		return coordinates;
	}
	public int getXcoord() {
		return x_coord;
	}
	public int getYcoord() {
		return y_coord;
	}
	public void setblueLineTrue() {
		blueLine=true;
	}
	public void setredLineTrue() {
		redLine=true;
	}
	public void setgreenLineTrue() {
		greenLine=true;
	}
	public void setFrontRunnerTrue() {
		frontRunner=true;
	}
	public void setSLineTrue() {
		sLine=true;
	}

	@Override
	public String toString() {
		return "ID:" + getID() + " | Line: " + line.getName() + " | " + stationName ;
	}

	@Override
	public int compareTo(Station o) {
		return this.getStationName().compareTo(o.getStationName());
	}
	
	

}
