package trax;

import java.awt.Point;

/**
 * Station is a class that is used to hold the data for each rail station along the Trax lines, it implements comparable
 * ..in order to see if two stations on different lines with the same name are indeed the same station(i.e. 600 S on green
 * .. line is the same as 600 S on red line)
 */
public class Station implements Comparable<Station>{
	
	private int ID;
	private String stationName;
	private RailLine line;
	private Point coordinates;
	private int x_coord;
	private int y_coord;
	
	/**
	 * @param id
	 * @param name
	 * @param line
	 */
	public Station(int id, String name, RailLine line) {
		this.ID = id;
		this.stationName = name;
		this.line = line;
	}

	/**
	 * @return
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @return
	 */
	public String getStationName() {
		return stationName;
	}

	/**
	 * @return
	 */
	public RailLine getRailLine() {
		return line;
	}

	/**
	 * @param coord
	 */
	public void setPoint(Point coord) {
		coordinates = coord;
		this.x_coord = (int) coord.getX();
		this.y_coord = (int) coord.getY();
	}

	/**
	 * @return
	 */
	public Point getPoint() {
		return coordinates;
	}

	/**
	 * @return
	 */
	public int getXcoord() {
		return x_coord;
	}

	/**
	 * @return
	 */
	public int getYcoord() {
		return y_coord;
	}

	@Override
	public String toString() {
		return "ID:" + getID() + " | Line: " + line.getName() + " | " + stationName;
	}

	@Override
	public int compareTo(Station o) {
		return this.getStationName().compareTo(o.getStationName());
	}
	
	

}
