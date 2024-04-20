package trax;

import java.awt.Point;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;

/**
 * Methods for reading text files with configuration data
 * 
 * @author sawyerreeve + Chris Darnell
 */
public class FileIO {
	private static In stationList = new In("src/Resources/stationList.txt");
	private static In stationCoordList = new In("src/Resources/pointLocations.txt");
	private static In pathOverlayList = new In("src/Resources/pathOverlays.txt");

	/**
	 * Creates a Queue with string names from the stationList.txt text file
	 * 
	 * @param railLine Enumeration of rail lines e.g. RailLines.BLUE_LINE
	 * @return The Queued list of stations
	 */
	public static Queue<String> getStationList(RailLines railLine) {
		Queue<String> stations = new Queue<>();
		while (stationList.hasNextLine()) {
			String nextLine = stationList.readLine();
			if (nextLine.equals(railLine.name())) {
				nextLine = stationList.readLine();
				while (stationList.hasNextLine() & !nextLine.equals("###")) {
					stations.enqueue(nextLine);
					nextLine = stationList.readLine();
				}
				break;
			}
		}
		return stations;
	}

	/**
	 * @param railLine
	 * @return
	 */
	public static Queue<Point> getCoordList(RailLines railLine) {
		int x;
		int y;
		Queue<Point> coordinates = new Queue<>();
		while (stationCoordList.hasNextLine()) {
			String nextLine = stationCoordList.readLine();
			if (nextLine.equals(railLine.name())) {
				String[] coords = new String[2];
				nextLine = stationCoordList.readLine();
				while (stationCoordList.hasNextLine() & !nextLine.equals("###")) {
					coords = nextLine.split(",");
					x = Integer.parseInt(coords[0]);
					y = Integer.parseInt(coords[1]);
					coordinates.enqueue(new Point(x, y));
					nextLine = stationCoordList.readLine();
				}
				break;
			}
		}
		return coordinates;
	}
	
	/**
	 * @return
	 */
	public static ST<String, String> getPathList() {
		ST<String, String> st = new ST<>();
		while (pathOverlayList.hasNextLine()) {
			String nextLine[] = pathOverlayList.readLine().split(",");
			st.put(nextLine[0], nextLine[1]);
		}
		return st;
	}
	
}
