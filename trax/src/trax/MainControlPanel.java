package trax;

import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * I just mocked this up as a refresher, don't feel bad deleting all of it if you want to start from scratch
 */
@SuppressWarnings("serial")
public class MainControlPanel extends JPanel {

	MainControlPanel(){
		this.setVisible(true);
		this.setPreferredSize(new Dimension(400,800));
		this.setLayout(null);



		String[] blue = MainApp.getBlueLine().getStationArray();
		String[] red = MainApp.getRedLine().getStationArray();
		String[] green = MainApp.getGreenLine().getStationArray();
		String[] front = MainApp.getFrontRunner().getStationArray();
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
		
		JComboBox<String> blueBoxdestination = new JComboBox<String>(blue);
		JComboBox<String> greenBoxdestination = new JComboBox<String>(green);
		JComboBox<String> redBoxdestination = new JComboBox<String>(red);
		JComboBox<String> frontRunnerBoxdestination = new JComboBox<String>(front);
		
		
		//TODO: Decide layout
		start.setBounds(100, 0, 100, 30);
		blueLabelStart.setBounds(0, 30, 100, 30);
		blueBoxStart.setBounds(110, 30, 200, 30);
		redLabelStart.setBounds(0, 60, 100, 30);
		redBoxStart.setBounds(110, 60, 200, 30);
		greenLabelStart.setBounds(0, 90, 100, 30);
		greenBoxStart.setBounds(110, 90, 200, 30);
		frontLabelStart.setBounds(0, 120, 100, 30);
		frontRunnerStart.setBounds(110, 120, 200, 30);
		
		destination.setBounds(100,150,100,30);
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
	
}
