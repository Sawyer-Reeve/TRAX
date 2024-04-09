package trax;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainApp {
	
	
    public static void main(String[] args) throws IOException {
    	RailInitialization();
    	new MainGUIframe();
    	
         
    }
    
    
    
    //TODO:not sure if this needs to be its own class or if it fits here
    public static void RailInitialization() {
    	//initialization of railLines and stations with ID's
		
    			//RailLines are initialized here(without the lists) in order to allow me to assign each of them to the stations 
    			//when they are created
    			RailLine GreenLine = new RailLine("Green Line");
    			RailLine BlueLine = new RailLine("Blue Line");
    			RailLine RedLine = new RailLine("Red Line");
    			RailLine FrontRunner = new RailLine("FrontRunner");
    			
    			
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
    						Station station = new Station(count, s, BlueLine);
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
    				Station station = new Station(count, s, RedLine);
    				RedLineStations.add(station);
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
    				Station station = new Station(count, s, GreenLine);
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
    				Station station = new Station(count, s, FrontRunner);
    				FrontRunnerStations.add(station);
    				count++;	
    			}
    			
    			FrontRunner.add(FrontRunnerStations);
    			BlueLine.add(BlueLineStations);
    			GreenLine.add(GreenLineStations);
    			RedLine.add(RedLineStations);
    			
    			
    			//end of initialization for the Stations and rails
    			
    			//--------------------------------------PRINT STATEMENTS--------------------------------------------------------
    			System.out.println(BlueLine);
    			System.out.println(RedLine);
    			System.out.println(GreenLine);
    			System.out.println(FrontRunner);
    			
    			
    			
    	/*		
    			//print example
    			for (Station s:FrontRunnerStations) {
    				System.out.println(s.toString());
    			}
    			System.out.println();
    			
    			
    			//for printing to txt file
    			printLinearRailGraphs(BlueLineStations);
    			System.out.println();

    			//for printing to txt file
    			printLinearRailGraphs(RedLineStations);
    			System.out.println();

    			//for printing to txt file
    			printLinearRailGraphs(GreenLineStations);
    			System.out.println();

    			//for printing to txt file
    			printLinearRailGraphs(FrontRunnerStations);
    			
    	*/

    }
    
    
    /**
	 * prints a linear graph in txt file form with a provided station list
	 * @param stations ArrayList of each station
	 */
	private static void printLinearRailGraphs(ArrayList<Station> stations) {
		
		//TODO: add functionality to write it to a text file + Merge with other graphs, or just do it manually
		System.out.println(stations.size());//our vertices
		System.out.println(stations.size()-1);//edges, since the lines themselves are linear its just vertices-1;
		for (int i=0;i<stations.size()-1;i++) {
			System.out.println(stations.get(i).getID() + " " + stations.get(i+1).getID());
			
		}
	}
}
