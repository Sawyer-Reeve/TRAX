package trax;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

/**
 * I just mocked this up as a refresher, don't feel bad deleting all of it if you want to start from scratch
 */
@SuppressWarnings("serial")
public class MainControlPanel extends JPanel implements ActionListener{

	JButton submitButton;
	JComboBox<String> startDropDownMenu;
	JComboBox<String> destinationDropDownMenu;
	Double total_time=0.0;
	
	//panel with directions
	MainControlPanel(){
		this.setVisible(true);
		this.setPreferredSize(new Dimension(300,800));
		this.setLayout(null);


		//could potentially use empty spots in the arrays to seperate the Raillines in a single drop down menu

		String[] all = MainApp.allStationsStrings();//this takes the Strings from the hashmap to ensure no duplicates
		JLabel start = new JLabel("Start:");
		JLabel destination = new JLabel("Destination:");
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);

		startDropDownMenu = new JComboBox<String>(all);
		destinationDropDownMenu = new JComboBox<String>(all);
		
		
		start.setBounds(0, 0, 100, 30);
		startDropDownMenu.setBounds(0, 30, 200, 30);
		destination.setBounds(0,90,100,30);
		destinationDropDownMenu.setBounds(0, 120, 200, 30);
		submitButton.setBounds(140, 160, 100, 30);

		
		this.add(start);
		this.add(destination);
		this.add(startDropDownMenu);
		this.add(destinationDropDownMenu);
		this.add(submitButton);
		

	}
	//this is the action performed when the submit button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submitButton) {
			Station start =MainApp.getStationHashMap().get(startDropDownMenu.getSelectedItem()); //starting station
			Station destination = MainApp.getStationHashMap().get(destinationDropDownMenu.getSelectedItem());//ending station
			In in = new In(new File("src/Resources/GraphSawyer.txt/"));
			
			EdgeWeightedGraph g = new EdgeWeightedGraph(in);
			ArrayList<Integer> path = new ArrayList<Integer>();
			String s;
			Integer secondStation=null;
			//the following loop iterates through each edge in the created shortest route;
			//because the edge is returned as an iterable with 3 numbers, we use the split method to seperate all three
			//numbers, it is returned as xx-yy z, where xx is the second station, yy is the first, and z is the edge weight
			//or in this case the time taken to travel
			
			for (Edge a : MainApp.route(start,destination, g)) { //calls the routing method from the main 
				//which uses Dijkstras algorithm to find the shortest path
				s = a.toString();
				String[] parts = s.split("-");
				secondStation = Integer.parseInt(parts[0]);
				String[] parts2 = parts[1].split("\\s+");
				Integer firstStation = Integer.parseInt(parts2[0]); // takes the second half of the xx-xx string, only prints at end
				total_time += Double.parseDouble(parts2[1]);
				path.add(firstStation);
				System.out.print(s);
			}
			
			path.add(secondStation);//this is added after the loop, because each station appears twice in the iterable 69-68 68-67 67-66 etc..
			System.out.println();
			System.out.println(path);//debugging display
			System.out.println("Estimated time: " + total_time);
			System.out.println();
			//debugging loop to display paths
			for (Integer i : path) {
				System.out.println(	MainApp.getRailIdHashmap().get(i));
			}
			//TODO: add panel to display directions, we can iterate through each number in the ArrayList path,
			//and keep track of the current and the last station's railLines, if there is a difference in raillines
			//then we know we need to transfer there at that index, we don't need to list every station, just the start
			//then when we iterate and hit a transfer, list that, and then the final destination
			
			//TODO: If we want we can add that pin functionality, I have no problem going through and getting
			//all of the coordinates for each station, it wouldn't take too long and I can just add it as a
			//class variable for Station
			
		}
		
	}

	
	
}
