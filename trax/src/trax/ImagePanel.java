package trax;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private String backgroundImg = "/Resources/TRAX MAP.png";
	private String pinImg = "/Resources/PinDrop.png";
	
	private int x = 0;
	private int y = 0;
	
	/**
	 * Create the panel.
	 */
	public ImagePanel() {
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon background = new ImageIcon(ImagePanel.class.getResource(backgroundImg));
		background.paintIcon(this,  g, 0, 0);
		
		ImageIcon pin = new ImageIcon(ImagePanel.class.getResource(pinImg));
		Image image = pin.getImage();		
		Image temp = image.getScaledInstance(40, -1, Image.SCALE_SMOOTH);
		ImageIcon scaledPin = new ImageIcon(temp);
		scaledPin.paintIcon(this, g, x, y);
	}
	
	public void setPinXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
