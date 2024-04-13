package trax;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**
 * Methods for reading text files with configuration data
 * @author chrisdarnell
 */
public class FileIO {
	private static In stationList = new In("src/Resources/stationList.txt");

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
}
