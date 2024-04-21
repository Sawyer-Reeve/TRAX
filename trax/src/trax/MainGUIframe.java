package trax;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.stream.StreamSupport;

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
 * MainGUIframe is the main frame object that contains the control panel and the imagePanels, 
 * it is responsible for painting the pins and providing directions
 * @author Sawyer Reeve + Chris Darnell
 */
public class MainGUIframe extends JFrame implements ActionListener, ItemListener {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton submitButton;
	private static JComboBox<String> startDropDownMenu;
	private static JComboBox<String> destinationDropDownMenu;
	private JTextArea directions;
	private JLabel timelabel;
	private JLabel directionsLabel;
	private Double total_time = 0.0;
	private JLabel start;
	private JLabel destination;
	private static JPanel main;
	private ImagePanel imagePanel;
	private Queue<Station> transferStations;
	private Queue<RailLine> transfers;
	private Station[] transferStationArray;
	private static ST<String, String> pathOverlayList = FileIO.getPathList();
	private static Queue<String> currentPathList = new Queue<>();
	// Test Mode
	private static int demoStart = 1, demoDest = 0;
	private static boolean demoModeActive = false;
	
	
	/**
	 * Adds a next button that allows us to quickly iterate through all routes to 
	 * find potential bugs in the routing mechanism
	 */
	public static void testMode() {
		JButton testButton = new JButton("Next");
		testButton.setBounds(55, 200, 100, 30);
		main.add(testButton);
		
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println("Test Button Pressed");
				if (demoStart >= MainApp.allStationsStrings().length -1) {
					demoStart = -1; 
					demoDest++;
				}
				if (demoDest >= MainApp.allStationsStrings().length) {
					demoDest = 0;
				}
				startDropDownMenu.setSelectedIndex(++demoStart);
				destinationDropDownMenu.setSelectedIndex(demoDest);
			}
		});
	}

	/**
	 * Constructor - creates a GUI frame
	 */
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

		String[] all = MainApp.allStationsStrings(); // this takes the Strings from the hashmap to ensure no duplicates
		start = new JLabel("<HTML><b>Start:</b></HTML>");
		destination = new JLabel("<HTML><b>Destination:</b></HTML>");
		directionsLabel = new JLabel("<HTML><b>Directions:</b></HTML>");
		timelabel = new JLabel("<HTML><b>Estimated Time:</b></HTML>");
		submitButton = new JButton("Get Route");
		submitButton.addActionListener(this);
		directions = new JTextArea();

		startDropDownMenu = new JComboBox<String>(all);
		startDropDownMenu.addItemListener(this);
		destinationDropDownMenu = new JComboBox<String>(all);
		
		// set to the second item so it doesn't default to the same location as the start
		destinationDropDownMenu.setSelectedIndex(1);
		destinationDropDownMenu.addItemListener(this);

		main.add(start);
		main.add(destination);
		main.add(startDropDownMenu);
		main.add(destinationDropDownMenu);
		main.add(submitButton);
		main.add(directionsLabel);
		main.add(directions);
		main.add(timelabel);
		
		if (demoModeActive) testMode();

		panel.add(main, BorderLayout.WEST);

		start.setBounds(50, 20, 100, 30);
		startDropDownMenu.setBounds(45, 50, 280, 30);
		destination.setBounds(50, 110, 100, 30);
		destinationDropDownMenu.setBounds(45, 140, 280, 30);
		submitButton.setBounds(200, 200, 100, 30);
		directionsLabel.setBounds(50, 380, 100, 30);
		
		timelabel.setBounds(50, 620, 200, 30);
		
		directions.setBounds(50, 420, 250, 200);
		directions.setBackground(getBackground());
		directions.setWrapStyleWord(true);
		directions.setLineWrap(true);
		
		imagePanel = new ImagePanel();
		imagePanel.setPreferredSize(new Dimension(900, 800));
		
		panel.add(imagePanel);

	}

	/*
	 * Configures and displays the starting position map pin
	 */
	private void showStartPin() {
		Station start = MainApp.getName_Station_ST().get((String) startDropDownMenu.getSelectedItem());
		demoStart = startDropDownMenu.getSelectedIndex();
		imagePanel.setPinXY(1, start.getXcoord(), start.getYcoord());
		imagePanel.togglePin(1, true);
	}

	/*
	 * Configures and displays the destination position map pin
	 */
	private void showDestPin() {
		Station destination = MainApp.getName_Station_ST().get((String) destinationDropDownMenu.getSelectedItem());
		demoDest = destinationDropDownMenu.getSelectedIndex();
		imagePanel.setPinXY(2, destination.getXcoord(), destination.getYcoord());
		imagePanel.togglePin(2, true);
	}

	/*
	 * Hides the additional map pins denoting transfers between rail lines
	 */
	private void hideTransferPins() {
		imagePanel.togglePin(3, false);
		imagePanel.togglePin(4, false);
	}
	
	/*
	 * Configures the transfer between rail lines map pins according to the number of transfers needed
	 */
	private void showTransferPins() {
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

	/**
	 *repaints the Pins when the drop down menu is selected
	 */
	public void itemStateChanged(ItemEvent e) {
		if ((e.getStateChange() == ItemEvent.SELECTED)) {

			// Set the pin elements on the GUI
			showStartPin();
			showDestPin();
			hideTransferPins();

			imagePanel.repaint();
		}
	}

	// is is the action performed when the submit button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {

			Station start = MainApp.getName_Station_ST().get((String) startDropDownMenu.getSelectedItem()); // starting
			Station destination = MainApp.getName_Station_ST().get((String) destinationDropDownMenu.getSelectedItem());// ending

			RailLine startline = start.getRailLine();
			
			In in = new In("Resources/Graph.txt/");

			EdgeWeightedGraph g = new EdgeWeightedGraph(in);

			// ----------------------------
			transfers = new Queue<RailLine>();
			transferStations = new Queue<Station>();
			Queue<Edge> transferEdges = new Queue<Edge>();
			Queue<Integer> pathway = new Queue<Integer>();
			Queue<Integer> path = new Queue<>();

			System.out.println(MainApp.route(start, destination, g).toString());
			transfers.enqueue(start.getRailLine());
			Integer count = 0;
			Integer numEdges = 0;
			Integer startID = start.getID();
			path.enqueue(startID);
			Integer lastVert = startID;
			
			Iterable<Edge> route = MainApp.route(start, destination, g);	
			numEdges = (int)StreamSupport.stream(route.spliterator(), false).count();
		
			//iterates through the path and gets weights, and builds a path, due to the unreliability of either and other methods
			for (Edge a : route) {
				// calls the routing method from the main
				// which uses Dijkstra's algorithm to find the shortest path
				String s = a.toString();
				String[] separation = s.split(" ");
				currentPathList.enqueue(separation[0]);
				String[] vertices = separation[0].split("-");
				//String.split method is used to separate each vertex within the edge returned by the algorithm
				if (Integer.parseInt(vertices[0]) == lastVert) {
					pathway.enqueue(Integer.parseInt(vertices[1]));
					path.enqueue(Integer.parseInt(vertices[1]));
				} else {
					pathway.enqueue(Integer.parseInt(vertices[0]));
					path.enqueue(Integer.parseInt(vertices[0]));
				}
				for (Integer i : path)
					lastVert = i;

				// this checks for transfers in the pathing algorithm and ensures theyre not
				// happening on the start or end
				if (a.weight() == 1.1 && count != 0 && count != numEdges - 1) {
					transferEdges.enqueue(a);
				}

				// System.out.println(a);
				count++;
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
				// System.out.println(s.getStationName());
				if (destination.getStationName().equals(s.getStationName())) {
					transferStationArray = new Station[0];
				}
			}

			provideDirections(start, destination, transferStationArray);

			showStartPin();
			showDestPin();
			showTransferPins();

		} catch (IllegalArgumentException n) {
			directions.setText("Please Enter a Destination that is not the same as the start");
		}
	}
	
	/**
	 * getCurrentPathList
	 * @return returns a queue of the current paths
	 */
	public static Queue<String> getCurrentPathList() {
		return currentPathList;
	}
	
	/**
	 * @return returns a symbol table of edges and their respective edge highlights
	 */
	public static ST<String, String> getPathOverlays() {
		return pathOverlayList;
	}

	/*
	 * provideDirections displays a a text box of various text, depending on if
	 * there are transfers within the route that need to be taken
	 * 
	 * @param start starting station
	 * 
	 * @param destination destination station
	 * 
	 * @param transferSTationArray, array of transfer Stations
	 */
	private void provideDirections(Station start, Station destination, Station[] transferStationArray) {

		boolean startOnDestinationRail = false;
		/*boolean destinationOnStartRail = false;
		for (Station s : start.getRailLine().getStations()) {
			if (destination.getStationName().equals(s.getStationName())) {
				destinationOnStartRail = true;
			}

		}*/
		for (Station s : destination.getRailLine().getStations()) {
			if (start.getStationName().equals(s.getStationName())) {
				startOnDestinationRail = true;
			}

		}

		StringBuilder sb = new StringBuilder();
		if (transferStationArray.length == 0) {
			if (start.getStationName().equals(destination.getStationName())) {
				sb.append("Please Enter a Destination that is not the same as the start");

			} else if (startOnDestinationRail == true) {
				sb.append("1. From " + start.getStationName() + " take the " + destination.getRailLine().getName()
						+ " all the way to " + destination.getStationName());
			} else {
				sb.append("1. From " + start.getStationName() + " take the " + start.getRailLine().getName()
						+ " all the way to " + destination.getStationName());
			}
		}
		else if (transferStationArray.length == 1) {
			sb.append("1. From " + start.getStationName() + " take the " + start.getRailLine().getName() + " to "
					+ transferStationArray[0].getStationName() + "\n\n2. Then transfer onto "
					+ destination.getRailLine().getName() + " to " + destination.getStationName());
		} else if (transferStationArray.length == 2) {
			sb.append("1. From " + start.getStationName() + " take the " + start.getRailLine().getName() + " to "

					+ transferStationArray[0].getStationName() + "\n\n2. Then transfer onto the "
					+ transferStationArray[0].getRailLine().getName() + " to "
					+ transferStationArray[1].getStationName() + "\n\n3. Then take the "
					+ transferStationArray[1].getRailLine().getName() + " to " + destination.getStationName());
		} else {

			sb.append(/*
						 * "From " + start.getStationName() + " you will take the " +
						 * destination.getRailLine().getName() + " all the way to " +
						 * destination.getStationName()
						 */"if youre seeing this the code is broken");
		}

		total_time = Math.floor(MainApp.getTotal_Time());
		System.out.println("\nDirections:");
		System.out.println(sb.toString());
		directions.setText(sb.toString());
		System.out.println("\nEstimated time: " + total_time);
		timelabel.setText("<HTML><b>Estimated Time:</b> " + total_time + " minutes.</HTML>");
		total_time = 0.0;

	}

}
