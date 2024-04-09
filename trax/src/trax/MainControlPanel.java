package trax;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainControlPanel extends JPanel {

	MainControlPanel(){
		this.setVisible(true);
		JLabel blueLabel = new JLabel("Blue Stations:");
		this.add(blueLabel);
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.revalidate();
		
		//FIXME: call the stations from another class instead of putting them again in here
		String[] blueStations = {"Salt Lake Central Station",
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
				"Draper Town"};
		
			JComboBox<String> blueBoxStrings = new JComboBox<String>(blueStations);
			this.add(blueBoxStrings);
		
		
	}
	
}
