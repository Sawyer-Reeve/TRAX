package trax;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
	Queue<Station> transferStations;
	Queue<RailLine> transfers;
	Station[] transferStationArray;

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

		imagePanel = new ImagePanel();
		panel.add(imagePanel);
		imagePanel.setPreferredSize(new Dimension(900, 800));

	}

	public void itemStateChanged(ItemEvent e) {
		if ((e.getStateChange() == ItemEvent.SELECTED)) {
			Station start = MainApp.getName_Station_ST().get((String) startDropDownMenu.getSelectedItem());

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

			if (e.getSource() == submitButton) {

			}
			Station start = MainApp.getName_Station_ST().get((String) startDropDownMenu.getSelectedItem()); // starting

			Station destination = MainApp.getName_Station_ST().get((String) destinationDropDownMenu.getSelectedItem());// ending

			In in = new In(new File("src/Resources/Graph.txt/"));

			EdgeWeightedGraph g = new EdgeWeightedGraph(in);

			// ----------------------------
			transfers = new Queue<RailLine>();
			transferStations = new Queue<Station>();
			Queue<Edge> transferEdges = new Queue<Edge>();

			System.out.println(MainApp.route(start, destination, g).toString());
			System.out.println(MainApp.total_time);
			transfers.enqueue(start.getRailLine());
			Integer count = 0;
			Integer numEdges = 0;
			for (Edge a : MainApp.route(start, destination, g)) {
				numEdges++;
			}
			for (Edge a : MainApp.route(start, destination, g)) {
				// calls the routing method from the main
				// which uses Dijkstras algorithm to find the shortest path

				// this checks for transfers in the pathing algorithm
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

			// System.out.println(transferStationArray.length);

			provideDirections(start, destination, transfers, transferStations);

			transferPinPainting();

		} catch (IllegalArgumentException n) {
			directions.setText("Please Enter a Destination that is not the same as the start");
		}

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
	private void provideDirections(Station start, Station destination, Queue<RailLine> transfers,
			Queue<Station> transferStations) {

		StringBuilder sb = new StringBuilder();

		if (start.getStationName().equals(destination.getStationName())) {
			sb.append("Please Enter a Destination that is not the same as the start");
		} else if (start.getRailLine() == MainApp.getGreenLine() && start.getRailLine() != destination.getRailLine()
				&& transferStationArray.length == 0) {

			sb.append("Starting at " + start.getStationName() + ", you will want to take the "
					+ destination.getRailLine().getName());
			sb.append(" all the way to " + destination.getStationName());

		}

		else if (transferStationArray.length == 0) {
			sb.append("Starting at " + start.getStationName() + ", you will want to take the "
					+ start.getRailLine().getName());
			sb.append(" all the way to " + destination.getStationName());
		}
		// case 1: start.getStationName().equals(destination.getStationName()))

		// case 2 transfer_station != -1 && second_transfer_station == -1
		else if (transferStationArray.length == 1) {
			sb.append("Starting at " + start.getStationName() + ", you will want to take the "
					+ start.getRailLine().getName());
			sb.append(" all the way to " + transferStationArray[0].getStationName()
					+ ". Then you will transfer and take the " + destination.getRailLine().getName() + " to "
					+ destination.getStationName());
		}

		else if (transferStationArray.length == 2) {
			sb.append("Starting at " + start.getStationName() + ", you will want to take the "
					+ start.getRailLine().getName());
			sb.append(" all the way to " + transferStationArray[0].getStationName()
					+ ". Then you will transfer and take the FrontRunner" + " to "
					+ transferStationArray[1].getStationName() + ". Finally " + " you will take the "
					+ destination.getRailLine().getName() + " to " + destination.getStationName());
		}
		// extra case that checks to see if both values are not in frontrunner

		total_time = Math.floor(MainApp.getTotal_Time());
		directions.setText(sb.toString());
		timelabel.setText("Estimated Time: " + total_time + " minutes.");
		total_time = 0.0;

	}



}
