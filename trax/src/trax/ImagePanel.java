package trax;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private String backgroundImg = "/Resources/background.png";
	private String startPinImg = "/Resources/PinDropGreen.png";
	private String endPinImg = "/Resources/PinDropBlue.png";
	private String transfer1PinImg = "/Resources/PinDropPurple.png";
	private String transfer2PinImg = "/Resources/PinDropOrange.png";

	private int[] x = new int[5];
	private int[] y = new int[5];
	private boolean[] pinEnabled = new boolean[5];

	private int scaleFactor = 60;
	
	/**
	 * Create the panel.
	 */
	public ImagePanel() {
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		ImageIcon background = new ImageIcon(ImagePanel.class.getResource(backgroundImg));
		background.paintIcon(this, g, 0, 0);
		
		paintPathHighlight(g);
		
		ImageIcon startPin = new ImageIcon(scaledImage(startPinImg, scaleFactor, -1));
		if (pinEnabled[1])
			startPin.paintIcon(this, g, x[1], y[1]);

		ImageIcon endPin = new ImageIcon(scaledImage(endPinImg, scaleFactor, -1));
		if (pinEnabled[2])
			endPin.paintIcon(this, g, x[2], y[2]);

		ImageIcon transferPin1 = new ImageIcon(scaledImage(transfer1PinImg, scaleFactor, -1));
		if (pinEnabled[3])
			transferPin1.paintIcon(this, g, x[3], y[3]);

		ImageIcon transferPin2 = new ImageIcon(scaledImage(transfer2PinImg, scaleFactor, -1));
		if (pinEnabled[4])
			transferPin2.paintIcon(this, g, x[4], y[4]);
		
	}

	/**
	 * paintPathHighlights paints over the path to be taken based upon the edges found in the generated Path
	 * @param g
	 */
	public void paintPathHighlight(Graphics g) {
		ST<String, String> lookup = MainGUIframe.getPathOverlays();
		ST<String, ImageIcon> paths = new ST<>();
		Queue<String> temp = MainGUIframe.getCurrentPathList();
		Queue<String> edges = new Queue<>();

		for (String s : lookup) {
			paths.put(s, new ImageIcon(ImagePanel.class.getResource("/Resources/Path/" + lookup.get(s))));
		}

		while (!temp.isEmpty()) {
			edges.enqueue(temp.dequeue());
		}

		for (String s : edges) {
			paths.get(s).paintIcon(this, g, 0, 0);
		}
	}

	/**
	 * 1 = start pin 2 = end pin 3 = transfer pin
	 * setPinXY sets the locations of the pins to be painted based off the coordinates of the Selected stations
	 * @param pin
	 * @param x x coord of pin
	 * @param y y coord of pin
	 */
	public void setPinXY(int pin, int x, int y) {
		x = x - scaleFactor + 30;
		y = y - scaleFactor + 9;
		this.x[pin] = x;
		this.y[pin] = y;
	}

	/**
	 * 1 = start pin 2 = end pin 3 = transfer pin
	 * togglePin activates the pins on or off based on the number of transfers
	 * and the selected start and stop points
	 * @param pin
	 */
	public void togglePin(int pin, boolean val) {
		pinEnabled[pin] = val;
	}

	private Image scaledImage(String path, int x, int y) {
		ImageIcon temp = new ImageIcon(ImagePanel.class.getResource(path));
		Image image = temp.getImage();
		return image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
	}

}
