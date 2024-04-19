package trax;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**
 * MainControlPanel is the left side of the GUI that controls the drop down menu's and displays
 * @author sawyerreeve + Chris Darnell
 */
@SuppressWarnings("serial")
public class MainControlPanel extends JPanel /*implements ActionListener, ItemListener*/{
/*
	JButton submitButton;
	JComboBox<String> startDropDownMenu;
	JComboBox<String> destinationDropDownMenu;
	JTextArea directions;
	JLabel pinLabel;
	JLabel timelabel;
	Double total_time=0.0;
	
	*/
	
	/**
	 * panel constructor
	 */
	MainControlPanel(){
		this.setVisible(true);
		//this.setPreferredSize(new Dimension(300,800));
		this.setLayout(null);
		
	}
//
//		//could potentially use empty spots in the arrays to seperate the Raillines in a single drop down menu
//
//		String[] all = MainApp.allStationsStrings();//this takes the Strings from the hashmap to ensure no duplicates
//		JLabel start = new JLabel("Start:");
//		JLabel destination = new JLabel("Destination:");
//		JLabel directionsLabel = new JLabel("Directions: ");
//		timelabel = new JLabel("Estimated Time: ");
//		submitButton = new JButton("Submit");
//		submitButton.addActionListener(this);
//		
//
//		startDropDownMenu = new JComboBox<String>(all);
//		startDropDownMenu.addItemListener(this);
//		destinationDropDownMenu = new JComboBox<String>(all);
//		
//		
//		start.setBounds(0, 0, 100, 30);
//		startDropDownMenu.setBounds(0, 30, 200, 30);
//		destination.setBounds(0,90,100,30);
//		destinationDropDownMenu.setBounds(0, 120, 200, 30);
//		submitButton.setBounds(140, 160, 100, 30);
//		directionsLabel.setBounds(0,370,100,30);
//		timelabel.setBounds(0, 600, 200, 30);
//		
//		directions = new JTextArea();
//		directions.setBounds(0,400, 250, 200);
//		directions.setBackground(getBackground());
//		directions.setWrapStyleWord(true);
//		directions.setLineWrap(true);
//
//		
//		this.add(start);
//		this.add(destination);
//		this.add(startDropDownMenu);
//		this.add(destinationDropDownMenu);
//		this.add(submitButton);
//		this.add(directionsLabel);
//		this.add(directions);
//		this.add(timelabel);
//
//	}
//	
//	
//	
//	public void itemStateChanged(ItemEvent e) {
//	    if ((e.getStateChange() == ItemEvent.SELECTED)) {
//	    	Station start = MainApp.getName_Station_ST().get((String)startDropDownMenu.getSelectedItem());
//	        System.out.println(start.toString());
//	      
//	    }
//	} 
//	//is is the action performed when the submit button is clicked
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		
//		try {	
//
//			if (e.getSource()==submitButton) {
//				
//			}
//				Station start = MainApp.getName_Station_ST().get((String)startDropDownMenu.getSelectedItem()); //starting station
//				Station destination = MainApp.getName_Station_ST().get((String)destinationDropDownMenu.getSelectedItem());//ending station
//
//				In in = new In(new File("src/Resources/GraphSawyer.txt/"));
//
//				EdgeWeightedGraph g = new EdgeWeightedGraph(in);
//				Queue<Integer> path = new Queue<Integer>();
//				String s;
//				Integer secondStation=null;
//				Integer firstStation=null;
//				int transfer_station=-1;
//				
//
//				//the following loop iterates through each edge in the created shortest route;
//				//because the edge is returned as an iterable with 3 numbers, we use the split method to seperate all three
//				//numbers, it is returned as xx-yy z, where xx is the second station, yy is the first, and z is the edge weight
//				//or in this case the time taken to travel
//
//				for (Edge a : MainApp.route(start,destination, g)) { //calls the routing method from the main 
//					//which uses Dijkstras algorithm to find the shortest path
//					
//					/*firstStation= a.either();
//					secondStation = a.other(firstStation);
//					total_time += a.weight();
//					path.enqueue(firstStation);
//					System.out.print(a.toString());*/
//					
//					s = a.toString();
//					String[] parts = s.split("-");
//					firstStation = Integer.parseInt(parts[0]);
//					String[] parts2 = parts[1].split("\\s+");
//					secondStation = Integer.parseInt(parts2[0]); // takes the first half of the xx-xx string
//					
//					if (Double.parseDouble(parts2[1]) == 1.1) { //transfers are given a weight of 1.1
//						transfer_station = firstStation;
//					}
//					total_time += Double.parseDouble(parts2[1]);
//					path.enqueue(firstStation);//debug display
//					System.out.print(s + " ");
//				}	
//				path.enqueue(secondStation);
//				//checks to see if the algorithm is attempting to transfer at the start and very end by comparing station names
//				if (transfer_station != -1) {
//					if (start.getStationName().equals(MainApp.getID_Station_ST().get(transfer_station).getStationName())) {
//						transfer_station = -1;
//						total_time -= 1.1;
//					}
//					else if (destination.getStationName().equals(MainApp.getID_Station_ST().get(transfer_station).getStationName())){
//						transfer_station = -1;
//						total_time -= 1.1;
//					}
//				}
//				//debugging path
//			/*	
//				System.out.println("Transfer Station: " + transfer_station);
//
//				
//				path.enqueue(secondStation);//this is added after the loop, because each station appears twice in the iterable 69-68 68-67 67-66 etc..
//				System.out.println();
//				System.out.println(path);//debugging display
//				System.out.println("Estimated time: " + total_time);
//				System.out.println();
//				for (Integer i : path) {
//					System.out.println(	MainApp.getID_Station_ST().get(i).getStationName());
//				}
//				*/
//
//
//
//				provideDirections(start, destination, transfer_station);
//			//TODO: add pin functionality, I dont even remember how to overlay images anymore ill be honest,
//				//we can get the coordinates from the start, destination, and transfer stations
//			
//
//		}
//		catch (IllegalArgumentException n) {
//			directions.setText("Please Enter a Destination that is not the same as the start");
//		}
//
//		
//	}
//	public JButton getButton() {
//		return submitButton;
//	}
//	/**
//	 * provideDirections displays a a text box of various text, depending on if there are transfers within the route that
//	 * need to be taken
//	 * @param start 
//	 * @param destination
//	 * @param transfer_station
//	 */
//	private void provideDirections(Station start, Station destination, int transfer_station) {
//		StringBuilder sb = new StringBuilder();
//
//		if (start.getStationName().equals(destination.getStationName())) {
//			sb.append("Please Enter a Destination that is not the same as the start");
//		}
//		else if (transfer_station != -1) {
//			sb.append("Starting at " + start.getStationName() + ", you will want to take the " + start.getRailLine().getName());
//			sb.append(" all the way to " + MainApp.getID_Station_ST().get(transfer_station).getStationName() + ". Then you will transfer and take the "
//					+ destination.getRailLine().getName() + " to " + destination.getStationName());
//		}
//		
//		else {
//			sb.append("Starting at " + start.getStationName() + ", you will want to take the " + destination.getRailLine().getName());
//			sb.append(" all the way to " + destination.getStationName());
//		}
//
//		directions.setText(sb.toString());
//		timelabel.setText("Estimated Time: " + total_time + " minutes.");
//		total_time=0.0;
//		
//	}



}
