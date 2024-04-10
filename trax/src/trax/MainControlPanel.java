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
	
	//current panel
	MainControlPanel(){
		this.setVisible(true);
		this.setPreferredSize(new Dimension(400,800));
		this.setLayout(null);


		//could potentially use empty spots in the arrays to seperate the Raillines in a single drop down menu

		String[] all = MainApp.allStationsStrings();
		JLabel start = new JLabel("Start:");
		JLabel destination = new JLabel("Destination:");
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);

		startDropDownMenu = new JComboBox<String>(all);
		destinationDropDownMenu = new JComboBox<String>(all);
		
		
		start.setBounds(0, 0, 100, 30);
		startDropDownMenu.setBounds(0, 30, 200, 30);
		destination.setBounds(0,90,100,30); //150y for alternative
		destinationDropDownMenu.setBounds(0, 120, 200, 30);
		submitButton.setBounds(140, 160, 100, 30);

		
		this.add(start);
		this.add(destination);
		this.add(startDropDownMenu);
		this.add(destinationDropDownMenu);
		this.add(submitButton);
		

	}
	//alternative panel with separate menus for each line, could be a button for each line that pops open the relevant menu?
	//just pass any String argument s
	MainControlPanel(String s){
		String[] blue = MainApp.getBlueLine().getStationArray();
		String[] red = MainApp.getRedLine().getStationArray();
		String[] green = MainApp.getGreenLine().getStationArray();
		String[] front = MainApp.getFrontRunner().getStationArray();
		//String[] all = MainApp.allStationsStrings();
		JLabel start = new JLabel("Start:");

		JLabel blueLabelStart = new JLabel("Blue Stations:");
		JLabel redLabelStart = new JLabel("Red Stations:");
		JLabel greenLabelStart = new JLabel("Green Stations:");
		JLabel frontLabelStart = new JLabel("FrontRunner:");

		JLabel destination = new JLabel("Destination:");
		JLabel blueLabelDestination = new JLabel("Blue Stations:");
		JLabel redLabelDestination = new JLabel("Red Stations:");
		JLabel greenLabelDestination = new JLabel("Green Stations:");
		JLabel frontLabelDestination = new JLabel("FrontRunner:");

		JComboBox<String> blueBoxStart = new JComboBox<String>(blue);
		JComboBox<String> greenBoxStart = new JComboBox<String>(green);
		JComboBox<String> redBoxStart = new JComboBox<String>(red);
		JComboBox<String> frontRunnerStart = new JComboBox<String>(front);
		//JComboBox<String> allStart = new JComboBox<String>(all);

		JComboBox<String> blueBoxdestination = new JComboBox<String>(blue);
		JComboBox<String> greenBoxdestination = new JComboBox<String>(green);
		JComboBox<String> redBoxdestination = new JComboBox<String>(red);
		JComboBox<String> frontRunnerBoxdestination = new JComboBox<String>(front);
		//JComboBox<String> allDestination = new JComboBox<String>(all);

		start.setBounds(0, 0, 100, 30);
		blueLabelStart.setBounds(0, 30, 100, 30);
		blueBoxStart.setBounds(110, 30, 200, 30);
		redLabelStart.setBounds(0, 60, 100, 30);
		redBoxStart.setBounds(110, 60, 200, 30);
		greenLabelStart.setBounds(0, 90, 100, 30);
		greenBoxStart.setBounds(110, 90, 200, 30);
		frontLabelStart.setBounds(0, 120, 100, 30);
		frontRunnerStart.setBounds(110, 120, 200, 30);


		//allStart.setBounds(0, 30, 200, 30);
		destination.setBounds(0,90,100,30);
		//allDestination.setBounds(0, 120, 200, 30);
		blueLabelDestination.setBounds(0, 180, 100, 30);
		blueBoxdestination.setBounds(110, 180, 200, 30);
		redLabelDestination.setBounds(0, 210, 100, 30);
		redBoxdestination.setBounds(110, 210, 200, 30);
		greenLabelDestination.setBounds(0, 240, 100, 30);
		greenBoxdestination.setBounds(110, 240, 200, 30);
		frontLabelDestination.setBounds(0, 270, 100, 30);
		frontRunnerBoxdestination.setBounds(110, 270, 200, 30);


		this.add(start);
		this.add(destination);
		this.add(blueLabelStart);
		this.add(blueBoxStart);
		this.add(greenLabelStart);
		this.add(greenBoxStart);
		this.add(redLabelStart);
		this.add(redBoxStart);
		this.add(frontLabelStart);
		this.add(frontRunnerStart);
		this.add(blueBoxdestination);
		this.add(greenBoxdestination);
		this.add(redBoxdestination);
		this.add(frontRunnerBoxdestination);
		this.add(blueLabelDestination);
		this.add(redLabelDestination);
		this.add(frontLabelDestination);
		this.add(greenLabelDestination);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submitButton) {
			Station start =MainApp.getStationHashMap().get(startDropDownMenu.getSelectedItem());
			Station destination = MainApp.getStationHashMap().get(destinationDropDownMenu.getSelectedItem());
			In in = new In(new File("src/Resources/Graph.txt/"));
			
			EdgeWeightedGraph g = new EdgeWeightedGraph(in);
			ArrayList<Integer> path = new ArrayList<Integer>();
			String s;
			for (Edge a : MainApp.route(start,destination, g)) {
				s = a.toString();
				String[] parts = s.split("-");
				Integer part1 = Integer.parseInt(parts[0]);
				String[] parts2 = parts[1].split("\\s+");
				Integer part2 = Integer.parseInt(parts2[0]);
				total_time += Double.parseDouble(parts2[1]);
				path.add(part1);
				path.add(part2);
				System.out.print(s);
			}
			System.out.println();
			System.out.println(path);
			System.out.println("Estimated time: " + total_time);
			System.out.println();
			for (Integer i : path) {
				System.out.println(MainApp.getRailIdHashmap().get(i));
			}
			
			
		}
		
	}

	
	
}
