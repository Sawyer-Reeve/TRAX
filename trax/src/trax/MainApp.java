package trax;

import java.util.ArrayList;
import java.util.Arrays;

public class MainApp {

	public static void main(String[] args) {
		//initializing, adding all the rail strings, then we can just use an iterator to generate an ID number,
		
	
		
		//Blue Line Stations
		ArrayList<String> BlueStationsStrings = new ArrayList<String>(Arrays.asList("Salt Lake Central Station",
				"Old Greektown Station",
				"Planetarium Station",
				"Arena Station",
				"Temple Square Station",
				"City Center Station",
				"Gallivan Plaza Station",
				"Courthouse Station",
				"600 South Station",
				"900 South Station",
				"Ballpark Station",
				"Central Pointe Station",
				"Millcreek Station",
				"Meadowbrook Station",
				"Murray North Station",
				"Murray Central Station",
				"Fashion Place West Station",
				"Midvale Fort Union Station",
				"Midvale Center Station",
				"Historic Sandy Station",
				"Sandy Expo Station",
				"Sandy Civic Center Station",
				"Crescent View Station",
				"Kimballs Lane Station",
				"Draper Town"));
		
		ArrayList<Station> BlueLineStations = new ArrayList<Station>();
		int count = 100;
		for (String s : BlueStationsStrings) {
			Station station = new Station(count, s);
			BlueLineStations.add(station);
			count++;
		}
		
		
		
		//Red Line Stations
		ArrayList<String> RedStationsStrings = new ArrayList<String>(Arrays.asList("U. Of U. Medical Center Station",
				"Fort Douglas Station University South Campus Station",
				"Stadium Station",
				"900 East Station",
				"Trolley Station",
				"Library Station",
				"Courthouse Station",
				"600 South Station",
				"900 South Station",
				"Ballpark Station",
				"Central Pointe Station",
				"Millcreek Station",
				"Meadowbrook Station",
				"Murray North Station",
				"Murray Central Station",
				"Fashion Place West Station",
				"Bingham Junction Station",
				"Historic Gardner Station",
				"West Jordan City Center Station",
				"2700 W Sugar Factory Rd Station",
				"Jordan Valley Station",
				"4800 W Old Bingham Hwy Station",
				"5600 W Old Bingham Hwy Station",
				"South Jordan Parkway Station",
				"Daybreak Parkway Station"));
		
		ArrayList<Station> RedLineStations = new ArrayList<Station>();
		count = 200;
		for (String s : RedStationsStrings) {
			Station station = new Station(count, s);
			BlueLineStations.add(station);
			count++;
		}
		
		//Green Line Stations
		ArrayList<String> GreenStationsStrings = new ArrayList<String>(Arrays.asList("West Valley Central Station",
				"Decker Lake Station",
				"Redwood Junction Station",
				"River Trail Station",
				"Central Pointe Station",
				"Ballpark Station",
				"900 South Station",
				"600 South Station",
				"Courthouse Station",
				"Gallivan Plaza Station",
				"City Center Station",
				"Temple Square Station",
				"Arena Station",
				"North Temple Bridge/Guadalupe",
				"Jackson/Euclid Station",
				"Fairpark Station",
				"Power Station",
				"1940 W North Temple Station",
				"Airport Station"));
		
		ArrayList<Station> GreenLineStations = new ArrayList<Station>();	
		count=300;
		for (String s : GreenStationsStrings) {
			Station station = new Station(count, s);
			GreenLineStations.add(station);
			count++;
		}
		
		
		
		//FrontRunner Stations
		ArrayList<String> FrontRunnerStrings = new ArrayList<String>(Arrays.asList("Ogden Station",
				"Roy Station",
				"Clearfield Station",
				"Layton Station",
				"Farmington Station",
				"Woods Cross Station",
				"North Temple Station",
				"Salt Lake Central Station",
				"Murray Central Station",
				"South Jordan Station",
				"Draper Station",
				"Lehi Station",
				"American Fork Station",
				"Vineyard Station",
				"Orem Central Station",
				"Provo Central Station"));
		
		
		ArrayList<Station> FrontRunnerStations = new ArrayList<Station>();	
		count=400;
		for (String s : FrontRunnerStrings) {
			Station station = new Station(count, s);
			FrontRunnerStations.add(station);
			count++;	
		}
		
		
		
		//print example
		for (Station s:FrontRunnerStations) {
			System.out.println(s.toString());
		}
		
		
	}
	
	
	
	

}
