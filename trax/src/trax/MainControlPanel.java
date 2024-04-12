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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submitButton) {
			Station start =MainApp.getStationHashMap().get(startDropDownMenu.getSelectedItem());
			Station destination = MainApp.getStationHashMap().get(destinationDropDownMenu.getSelectedItem());
			In in = new In(new File("src/Resources/GraphSawyer.txt/"));
			
			EdgeWeightedGraph g = new EdgeWeightedGraph(in);
			ArrayList<Integer> path = new ArrayList<Integer>();
			String s;
			Integer secondStation=null;
			for (Edge a : MainApp.route(start,destination, g)) {
				s = a.toString();
				String[] parts = s.split("-");
				secondStation = Integer.parseInt(parts[0]);
				String[] parts2 = parts[1].split("\\s+");
				Integer firstStation = Integer.parseInt(parts2[0]); // takes the second half of the xx-xx string, only prints at end
				total_time += Double.parseDouble(parts2[1]);
				path.add(firstStation);
				System.out.print(s);
			}
			path.add(secondStation);
			System.out.println();
			System.out.println(path);
			System.out.println("Estimated time: " + total_time);
			System.out.println();
			for (Integer i : path) {
				MainApp.getRailIdHashmap().get(i);
			}
			
			
		}
		
	}

	
	
}
