package trax;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private String backgroundImg = "/Resources/TRAX MAP.png";
	private String startPinImg = "/Resources/PinDrop.png";
	private String endPinImg = "/Resources/PinDropBlue.png";
	private String transferPinImg = "/Resources/PinDropPurple.png";
	
	private int x1 = 0;
	private int y1 = 0;
	private int x2 = 0;
	private int y2 = 0;
	private int x3 = 0;
	private int y3 = 0;
	
	private boolean pin1Enabled = false;
	private boolean pin2Enabled = false;
	private boolean pin3Enabled = true;
	
	private int scaleFactor = 40;
	
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
		
        ImageIcon startPin = new ImageIcon(scaledImage(startPinImg, scaleFactor, -1));
        if (pin1Enabled) startPin.paintIcon(this, g, x1, y1);
        
        ImageIcon endPin = new ImageIcon(scaledImage(endPinImg, scaleFactor, -1));
        if (pin2Enabled) endPin.paintIcon(this, g, x2, y2);
        
        ImageIcon transferPin = new ImageIcon(scaledImage(transferPinImg, scaleFactor, -1));
        if (pin3Enabled) transferPin.paintIcon(this,  g, x3, y3);
        
	}
	
	/**
	 * 1 = start pin
	 * 2 = end pin
	 * 3 = transfer pin
	 * @param pin
	 * @param x
	 * @param y
	 */
	public void setPinXY(int pin, int x, int y) {
		x = x - scaleFactor + 20; y = y - scaleFactor + 7;
		switch (pin) {
			case 1: this.x1 = x; this.y1 = y; break;
			case 2: this.x2 = x; this.y2 = y; break;
			case 3: this.x3 = x; this.y3 = y; break;
		}
	}
	
	/**
	 * 1 = start pin
	 * 2 = end pin
	 * 3 = transfer pin
	 * @param pin
	 */
	public void enablePin(int pin) {
		switch (pin) {
			case 1: pin1Enabled = true; break;
			case 2: pin2Enabled = true; break;
			case 3: pin3Enabled = true; break;
		}
	}
	
	private Image scaledImage(String path, int x, int y) {
		ImageIcon temp = new ImageIcon(ImagePanel.class.getResource(path));
		Image image = temp.getImage();
		return image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
	}
	
}
