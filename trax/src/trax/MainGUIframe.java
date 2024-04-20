package trax;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;

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
import edu.princeton.cs.algs4.ST;

/**
 * this might not have needed to be its own class, but I did have a couple of other classes previously for separate panels
 * before I condensed them
 */
public class MainGUIframe extends JFrame implements ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;
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
	Queue<Station> transferStations;
	Queue<RailLine> transfers;
	Station[] transferStationArray;
	private static ST<String, String> pathOverlayList = FileIO.getPathList();
	private static Queue<String> currentPathList = new Queue<>();

	MainGUIframe() {
		panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(new BorderLayout());

		this.setSize(1200, 800);
		this.setLocationRelativeTo(null); // centers frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes program instead of hiding
		this.setResizable(true);
		this.setTitle("TRAX Ride-Assist");
		this.setContentPane(panel);
		this.setVisible(true);

		main = new JPanel();
		main.setPreferredSize(new Dimension(400, 800));
		// main.setLayout(new FlowLayout());
		main.setLayout(null);

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
		startDropDownMenu.setBounds(20, 30, 280, 30);
		destination.setBounds(20, 90, 100, 30);
		destinationDropDownMenu.setBounds(20, 120, 280, 30);
		submitButton.setBounds(160, 160, 100, 30);
		directionsLabel.setBounds(20, 370, 100, 30);
		timelabel.setBounds(20, 600, 200, 30);

		directions.setBounds(20, 400, 250, 200);
		directions.setBackground(getBackground());
		directions.setWrapStyleWord(true);
		directions.setLineWrap(true);

		imagePanel = new ImagePanel();
		buildImagePanel();
	}
	
	public void buildImagePanel() {
//		panel.remove(imagePanel);
//		imagePanel = new ImagePanel();
		panel.add(imagePanel);
		imagePanel.setPreferredSize(new Dimension(900, 800));
		panel.repaint();
//		panel.revalidate();
		imagePanel.repaint();
//		System.out.println("ImagePanel component count: " + imagePanel.getComponentCount());
		
	}
	
	public void clearImagePanel() {
		panel.remove(imagePanel);
//		if (imagePanel.getGraphics() != null) imagePanel.getGraphics().dispose();
//		imagePanel = new ImagePanel();
		panel.add(imagePanel);
//		imagePanel.getGraphics().create();
	}

	public void itemStateChanged(ItemEvent e) {
		if ((e.getStateChange() == ItemEvent.SELECTED)) {
			Station start = MainApp.getName_Station_ST().get((String) startDropDownMenu.getSelectedItem());
			
			clearImagePanel();
			imagePanel.setPinXY(1, start.getXcoord(), start.getYcoord());
			imagePanel.togglePin(1, true);
			Station destination = MainApp.getName_Station_ST().get((String) destinationDropDownMenu.getSelectedItem());
			imagePanel.setPinXY(2, destination.getXcoord(), destination.getYcoord());
			imagePanel.togglePin(2, true);
			imagePanel.repaint();
		}
	}

	// is is the action performed when the submit button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {

//			if (e.getSource() == submitButton) {
//
//		}
		
		
		
		Station start = MainApp.getName_Station_ST().get((String) startDropDownMenu.getSelectedItem()); // starting
		Station destination = MainApp.getName_Station_ST().get((String) destinationDropDownMenu.getSelectedItem());// ending

		RailLine startline = start.getRailLine();

		In in = new In(new File("src/Resources/Graph.txt/"));

		EdgeWeightedGraph g = new EdgeWeightedGraph(in);

		// ----------------------------
		transfers = new Queue<RailLine>();
		transferStations = new Queue<Station>();
		Queue<Edge> transferEdges = new Queue<Edge>();
		Queue<Integer> pathway = new Queue<Integer>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		System.out.println(MainApp.route(start, destination, g).toString());
		//System.out.println(MainApp.total_time);
		transfers.enqueue(start.getRailLine());
		Integer count = 0;
		Integer numEdges = 0;
		Integer startID = start.getID();
		path.add(startID);
		Integer lastvert = startID;
		for (Edge a : MainApp.route(start, destination, g)) {
			numEdges++;
		}
		
		
		for (Edge a : MainApp.route(start, destination, g)) {
			// calls the routing method from the main
			// which uses Dijkstras algorithm to find the shortest path
			String s = a.toString();
			String[] separation = s.split(" ");
			//XXX seperation[0] gives you each edge as xx-yy
			currentPathList.enqueue(separation[0]);
			String[] vertices = separation[0].split("-");
			if (Integer.parseInt(vertices[0])==lastvert) {
				pathway.enqueue(Integer.parseInt(vertices[1]));
				path.add(Integer.parseInt(vertices[1]));
			}
			else {
				pathway.enqueue(Integer.parseInt(vertices[0]));
				path.add(Integer.parseInt(vertices[0]));
			}
			lastvert=path.getLast();
			
			// this checks for transfers in the pathing algorithm
			if (a.weight() == 1.1 && count != 0 && count != numEdges - 1) {
				transferEdges.enqueue(a);
			}

			// System.out.println(a);
			count++;
		}
		for (Integer i : path) {
			System.out.println(i + " ");
		}
			RailLine current = start.getRailLine();
			while (transferEdges.size() != 0) {
				Edge currentEdge = transferEdges.dequeue();
				if (current != MainApp.getID_Station_ST().get(currentEdge.either()).getRailLine()) {
					current = MainApp.getID_Station_ST().get(currentEdge.either()).getRailLine();
					transferStations.enqueue(MainApp.getID_Station_ST().get(currentEdge.either()));
				} else {
					current = MainApp.getID_Station_ST().get(currentEdge.other(currentEdge.either())).getRailLine();
					transferStations.enqueue(MainApp.getID_Station_ST().get(currentEdge.other(currentEdge.either())));
				}
				transfers.enqueue(current);

			}

			transferStationArray = new Station[transferStations.size()];
			if (transferStations.size() > 0) {
				for (int i = 0; i < transferStations.size() + 1; i++) {
					transferStationArray[i] = transferStations.dequeue();
				}
			}

			for (Station s : startline.getStations()) {
				//System.out.println(s.getStationName());
				if (destination.getStationName().equals(s.getStationName())) {
					transferStationArray = new Station[0];
				}
			}
			
			provideDirections(start, destination, transferStationArray);
 
			transferPinPainting();
			
			
		} catch (IllegalArgumentException n) {
			directions.setText("Please Enter a Destination that is not the same as the start");
		}

	}
	
	public static Queue<String> getCurrentPathList() {
		return currentPathList;
	}
	
	public static ST<String, String> getPathOverlays() {
		return pathOverlayList;
	}

	private void transferPinPainting() {
		if (transferStationArray.length > 0) {
			imagePanel.setPinXY(3, transferStationArray[0].getXcoord(), transferStationArray[0].getYcoord());
			imagePanel.togglePin(3, true);

		} else {
			imagePanel.togglePin(3, false);
		}
		if (transferStationArray.length > 1) {
			imagePanel.setPinXY(4, transferStationArray[1].getXcoord(), transferStationArray[1].getYcoord());
			imagePanel.togglePin(4, true);
		} else {
			imagePanel.togglePin(4, false);
		}
		imagePanel.repaint();
	}

	/*
	 * provideDirections displays a a text box of various text, depending on if
	 * there are transfers within the route that need to be taken
	 * 
	 * @param start
	 * 
	 * @param destination
	 * 
	 * @param transfer_station
	 */
	private void provideDirections(Station start, Station destination,
			Station[] transferStationArray) {
			

		StringBuilder sb = new StringBuilder();

		if (start.getStationName().equals(destination.getStationName())) {
			sb.append("Please Enter a Destination that is not the same as the start");
		} 
		else if (start.getRailLine().equals(destination.getRailLine())) {
			sb.append("From " + start.getStationName() + " you will take the " + start.getRailLine().getName() + " all the way to " 
		+ destination.getStationName());
		}
		else if (transferStationArray.length==1) {
			
			sb.append("From " + start.getStationName() + " you will take the " + start.getRailLine().getName() + " to " + transferStationArray[0].getStationName()
			+ " then transfer onto " + destination.getRailLine().getName() + " to " + destination.getStationName());
		}
		else if (transferStationArray.length==2) {
			sb.append("From " + start.getStationName() + " you will take the " + start.getRailLine() + " to " + transferStationArray[0].getStationName()
			+ " then transfer onto " + transferStationArray[1].getStationName() + " and take the " + transferStationArray[1].getRailLine() + " to " + destination.getStationName());
		}
			else {
				sb.append("fuck"); // TODO
			}
			
		
		total_time = Math.floor(MainApp.getTotal_Time());
		System.out.println(sb.toString());
		directions.setText(sb.toString());
		timelabel.setText("Estimated Time: " + total_time + " minutes.");
		total_time = 0.0;

		
	}



}
