package trax;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**
 * this might not have needed to be its own class, but I did have a couple of other classes previously for separate panels
 * before I condensed them
 */
@SuppressWarnings({ "serial" })
public class MainGUIframe extends JFrame implements ActionListener, ItemListener {
	JPanel panel;
	JButton submitButton;
	JComboBox<String> startDropDownMenu;
	JComboBox<String> destinationDropDownMenu;
	JTextArea directions;
	JLabel startPinLabel;
	JLabel endPinLabel;
	JLabel trasnferPinLabel;
	JLabel timelabel;
	JLabel directionsLabel;
	Double total_time = 0.0;
	JLabel start;
	JLabel destination;
	JPanel main;
	ImageIcon pin = new ImageIcon("src/resources/pinDrop.png");
	ImagePanel imagePanel;

	MainGUIframe() {
		panel = new JPanel();
		// panel.setPreferredSize(new Dimension(1200,800));
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(new BorderLayout());

		this.setSize(1200, 800);
		this.setLocationRelativeTo(null); // centers frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes program instead of hiding
		this.setResizable(true);
		this.setTitle("TRAX Ride-Assist");
		this.setContentPane(panel);
		this.setVisible(true);
		// this.setLayout(null);

		main = new JPanel();
		main.setPreferredSize(new Dimension(400, 800));
		main.setLayout(new FlowLayout());
		main.setLayout(new BorderLayout());

		// panel.add(main);

		String[] all = MainApp.allStationsStrings();// this takes the Strings from the hashmap to ensure no duplicates
		start = new JLabel("Start:");
		destination = new JLabel("Destination:");
		directionsLabel = new JLabel("Directions: ");
		timelabel = new JLabel("Estimated Time: ");
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		directions = new JTextArea();

		startDropDownMenu = new JComboBox<String>(all);
		startDropDownMenu.addItemListener(this);
		destinationDropDownMenu = new JComboBox<String>(all);
		destinationDropDownMenu.addItemListener(this);

		main.add(start);
		main.add(destination);
		main.add(startDropDownMenu);
		main.add(destinationDropDownMenu);
		main.add(submitButton);
		main.add(directionsLabel);
		main.add(directions);
		main.add(timelabel);

		panel.add(main, BorderLayout.WEST);

		start.setBounds(20, 0, 100, 30);
		startDropDownMenu.setBounds(20, 30, 200, 30);
		destination.setBounds(20, 90, 100, 30);
		destinationDropDownMenu.setBounds(20, 120, 200, 30);
		submitButton.setBounds(160, 160, 100, 30);
		directionsLabel.setBounds(20, 370, 100, 30);
		timelabel.setBounds(20, 600, 200, 30);

		directions.setBounds(20, 400, 250, 200);
		directions.setBackground(getBackground());
		directions.setWrapStyleWord(true);
		directions.setLineWrap(true);

		// do map stuff below here, panel stuff above
		imagePanel = new ImagePanel();
		panel.add(imagePanel);
		imagePanel.setPreferredSize(new Dimension(900, 800));

	}

	public void itemStateChanged(ItemEvent e) {
		if ((e.getStateChange() == ItemEvent.SELECTED)) {
			Station start = MainApp.getName_Station_ST().get((String) startDropDownMenu.getSelectedItem());
			//System.out.println(start.toString());
			imagePanel.setPinXY(1, start.getXcoord(), start.getYcoord());
			imagePanel.togglePin(1, true);

			// imagePanel.paintComponent(getGraphics());
			Station destination = MainApp.getName_Station_ST().get((String) destinationDropDownMenu.getSelectedItem());
			imagePanel.setPinXY(2, destination.getXcoord(), destination.getYcoord());
			imagePanel.togglePin(2, true);
			// startPinLabel.setVisible(true);
			// startPinLabel.setLocation(start.getXcoord(), start.getYcoord());
			// startPinLabel.setVisible(true);
			imagePanel.repaint();
		}
	}

	// is is the action performed when the submit button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {

		try {

			if (e.getSource() == submitButton) {

			}
			Station start = MainApp.getName_Station_ST().get((String) startDropDownMenu.getSelectedItem()); // starting
																											
			Station destination = MainApp.getName_Station_ST().get((String) destinationDropDownMenu.getSelectedItem());// ending
																														

			In in = new In(new File("src/Resources/GraphSawyer.txt/"));

			EdgeWeightedGraph g = new EdgeWeightedGraph(in);
			Queue<Integer> path = new Queue<Integer>();
			String s;
			Integer secondStation = null;
			Integer firstStation = null;
			int transfer_station = -1;
			int second_transfer_station = -1;

			// the following loop iterates through each edge in the created shortest route;
			// because the edge is returned as an iterable with 3 numbers, we use the split
			// method to seperate all three
			// numbers, it is returned as xx-yy z, where xx is the second station, yy is the
			// first, and z is the edge weight
			// or in this case the time taken to travel
			boolean no_transfer = true;
			boolean first_transfer = false;
			boolean second_transfer = false;
			for (Edge a : MainApp.route(start, destination, g)) { // calls the routing method from the main
				// which uses Dijkstras algorithm to find the shortest path

				/*
				 * firstStation= a.either(); secondStation = a.other(firstStation); total_time
				 * += a.weight(); path.enqueue(firstStation); System.out.print(a.toString());
				 */
				
				s = a.toString();
				
				String[] parts = s.split("-");
				firstStation = Integer.parseInt(parts[0]);
				String[] parts2 = parts[1].split("\\s+");
				secondStation = Integer.parseInt(parts2[0]); // takes the first half of the xx-xx string

				if (Double.parseDouble(parts2[1]) == 1.1 && (no_transfer)) { // transfers are given a weight of 1.1
					transfer_station = firstStation;
					no_transfer = false;
					first_transfer = true;
					total_time += Double.parseDouble(parts2[1]);
					continue;

				}

				if (first_transfer = true && (Double.parseDouble(parts2[1]) == 1.1) && second_transfer == false) {
					second_transfer_station = secondStation;
					second_transfer = true;
					total_time+=Double.parseDouble(parts2[1]);
					continue;
				}
				total_time += Double.parseDouble(parts2[1]);
				//System.out.print(s + " ");
			}	
		
			
			//checks to see if the algorithm is attempting to transfer at the start and very end by comparing station names
			if (transfer_station != -1) {
				if (start.getStationName().equals(MainApp.getID_Station_ST().get(transfer_station).getStationName())) {
					System.out.println(MainApp.getID_Station_ST().get(transfer_station).getStationName());
					transfer_station = -1;
					total_time -= 1.1;
				}
				
			}
			if (second_transfer_station != -1) {
				if (start.getStationName().equals(MainApp.getID_Station_ST().get(second_transfer_station).getStationName())) {
					second_transfer_station = -1;
					System.out.println(MainApp.getID_Station_ST().get(second_transfer_station).getStationName());
					total_time -= 1.1;
				}
				
			}
			
		
			
			provideDirections(start, destination, transfer_station,second_transfer_station);
			Station transfer = MainApp.getID_Station_ST().get(transfer_station);
			Station transfer2 = MainApp.getID_Station_ST().get(second_transfer_station);
			if (transfer_station != -1) {
				imagePanel.setPinXY(3,  transfer.getXcoord(), transfer.getYcoord());
				imagePanel.togglePin(3, true);
				
			}
			else {
				imagePanel.togglePin(4, false);
			}
			if (second_transfer_station !=-1) {
				imagePanel.setPinXY(4, transfer2.getXcoord(), transfer2.getYcoord());
				imagePanel.togglePin(4, true);
			}
			else {
				imagePanel.togglePin(4, false);			}
			imagePanel.repaint();



		}
		catch (IllegalArgumentException n) {
			directions.setText("Please Enter a Destination that is not the same as the start");
		}


	}
	/*
	 * provideDirections displays a a text box of various text, depending on if there are transfers within the route that
	 * need to be taken
	 * @param start 
	 * @param destination
	 * @param transfer_station
	 */
	private void provideDirections(Station start, Station destination, int transfer_station, int second_transfer_station) {
		Station firstTransfer = MainApp.getID_Station_ST().get(transfer_station);
		Station secondTransfer = MainApp.getID_Station_ST().get(second_transfer_station);
		StringBuilder sb = new StringBuilder();
		//case 1: start.getStationName().equals(destination.getStationName()))
		if (start.getStationName().equals(destination.getStationName())) {
			sb.append("Please Enter a Destination that is not the same as the start");
		}
		//case 2 transfer_station != -1 && second_transfer_station == -1
		else if (transfer_station != -1 && second_transfer_station == -1) {
			sb.append("Starting at " + start.getStationName() + ", you will want to take the " + start.getRailLine().getName());
			sb.append(" all the way to " + MainApp.getID_Station_ST().get(transfer_station).getStationName() + ". Then you will transfer and take the "
					+ destination.getRailLine().getName() + " to " + destination.getStationName());
		}
		//case 3 transfer_station !=-1 && second_trasnfer_station != -1;
		else if (transfer_station !=-1 && second_transfer_station !=-1) {
			sb.append("Starting at " + start.getStationName() + ", you will want to take the " + start.getRailLine().getName());
			sb.append(" all the way to " + firstTransfer.getStationName() + ". Then you will transfer and take the FrontRunner"
					 + " to " + secondTransfer.getStationName() + ". Finally "
					+ " you will take the " + destination.getRailLine().getName() + " to " + destination.getStationName());
		}
		//case 4 else
		else {
			sb.append("Starting at " + start.getStationName() + ", you will want to take the " + destination.getRailLine().getName());
			sb.append(" all the way to " + destination.getStationName());
		}
		total_time = Math.floor(total_time);
		directions.setText(sb.toString());
		timelabel.setText("Estimated Time: " + total_time + " minutes.");
		total_time=0.0;





	}








}
