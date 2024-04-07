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
		
		//iterates through and assigns an ID number for each Station and adds it to the list
		ArrayList<Station> BlueLineStations = new ArrayList<Station>();
		int count = 100;
		for (String s : BlueStationsStrings) {
			Station station = new Station(count, s);
			BlueLineStations.add(station);
			count++;
		}
		
		System.out.println(BlueLineStations.size());//our vertices
		System.out.println(BlueLineStations.size()-1);//edges, since the lines themselves are linear its just vertices-1;
		for (int i=0;i<BlueLineStations.size()-1;i++) {
			System.out.println(BlueLineStations.get(i).getID() + " " + BlueLineStations.get(i+1).getID());
		}
		System.out.println();
		
		
		
		
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
			RedLineStations.add(station);
			count++;
		}
		
		//TODO: write to text file, still need weights

		System.out.println(RedLineStations.size());//our vertices
		System.out.println(RedLineStations.size()-1);//edges, since the lines themselves are linear its just vertices-1;
		for (int i=0;i<RedLineStations.size()-1;i++) {
			System.out.println(RedLineStations.get(i).getID() + " " + RedLineStations.get(i+1).getID());
		}
		System.out.println();
		
		
		
		
		
		
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
		
		//TODO: write to text file, still need weights
		
		System.out.println(GreenLineStations.size());//our vertices
		System.out.println(GreenLineStations.size()-1);//edges, since the lines themselves are linear its just vertices-1;
		for (int i=0;i<GreenLineStations.size()-1;i++) {
			System.out.println(GreenLineStations.get(i).getID() + " " + GreenLineStations.get(i+1).getID());
		}
		System.out.println();
		
		
		
		
		
		
		
		
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
		
		
		
		
		//end of initialization for the Stations
		
		
		
		//print example
		for (Station s:FrontRunnerStations) {
			System.out.println(s.toString());
		}
		System.out.println();
		
		
		
		//TODO: write this to a text file, it is our vertices for frontRunner
		
		System.out.println(FrontRunnerStations.size());//our vertices
		System.out.println(FrontRunnerStations.size()-1);//edges, since the lines themselves are linear its just vertices-1;
		for (int i=0;i<FrontRunnerStations.size()-1;i++) {
			System.out.println(FrontRunnerStations.get(i).getID() + " " + FrontRunnerStations.get(i+1).getID());
			
		}
		
		
	}
	
	
	
	

}
