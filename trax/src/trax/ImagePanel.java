package trax;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.princeton.cs.algs4.Draw;
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
	
	private int count = 0;

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
		
//		paintPath(g);
//		paintBuffered(g);
		
//		clearPaths(g);

//		Queue<String> edges = MainGUIframe.getCurrentPathList();
//		ST<String, String> lookup = MainGUIframe.getPathOverlays();
//		for (String s : edges) {
//			String fName = lookup.get(s);
//			String fPath = "/Resources/Path/" + lookup.get(s);
//			if (fName != null) {
//				ImageIcon path = new ImageIcon(ImagePanel.class.getResource(fPath));
//				path.paintIcon(this, g, 0, 0);
//			}
//		}
		
		paintPathsTest(g);
		
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
	
	private void clearPaths(Graphics g) {
		ImageIcon background = new ImageIcon(ImagePanel.class.getResource(backgroundImg));
		background.paintIcon(this, g, 0, 0);
//		while (!paths.isEmpty()) {
//			ImageIcon tmp = paths.dequeue();
//			
//		}
	}
	
	public void paintPathsTest(Graphics g) {
		ST<String, String> lookup = MainGUIframe.getPathOverlays();
		ST<String, ImageIcon> paths = new ST<>();
		Queue<String> edges = MainGUIframe.getCurrentPathList();
		
		for (String s : lookup) {
			paths.put(s, new ImageIcon(ImagePanel.class.getResource("/Resources/Path/" + lookup.get(s))));
		}
//		for (String s : edges) {
//			System.out.println(s);
//			if (paths.get(s) != null)
//				paths.get(s).paintIcon(this, g, 0, 0);
//		}

//		Queue<String> edges = new Queue<>();
//		if (count == 1) {
//			edges.enqueue("0-1");
//			edges.enqueue("1-2");
//		}
//		if (count == 2) {
//			edges.enqueue("1-2");
//			edges.enqueue("2-3");
//		}
		
		for (String s : edges) {
			paths.get(s).paintIcon(this, g, 0, 0);
//			switch (i) {
//	//			case 1: fr1.paintIcon(this, g, 0, 0); break;
//	//			case 2: fr2.paintIcon(this, g, 0, 0); break; 
//	//			case 3: fr3.paintIcon(this, g, 0, 0); break;
//	//			case 1: paths.get("70-71").paintIcon(this, g, 0, 0); break;
//	//			case 2: paths.get("71-72").paintIcon(this, g, 0, 0); break;
//	//			case 3: paths.get("72-73").paintIcon(this, g, 0, 0); break;
//			case 1: 			
//				bu1.paintIcon(this, g, 0, 0); break;
//			case 2: 			
//				bu2.paintIcon(this, g, 0, 0); break;
//			case 3: 
//				bu3.paintIcon(this, g, 0, 0); break;
//			default: break;
//			}
		}
		count++;
		if (count > 3) count = 0;
	}

	public void paintPath(Graphics g) {
		Queue<String> edges = MainGUIframe.getCurrentPathList();
		ST<String, String> lookup = MainGUIframe.getPathOverlays();
		for (String s : edges) {
			String testFile = lookup.get(s);
			String fPath = "/Resources/Path/" + lookup.get(s);
			if (testFile != null) {
				ImageIcon testPath = new ImageIcon(ImagePanel.class.getResource(fPath));
				testPath.paintIcon(this, g, 0, 0);
			}
		}
	}
	
	public void paintBuffered(Graphics g) {
		try {
			Queue<String> edges = MainGUIframe.getCurrentPathList();
			ST<String, String> lookup = MainGUIframe.getPathOverlays();
			
			for (String s : edges) {
				String fName = lookup.get(s);
				if (fName != null) {
					URL url = this.getClass().getResource("/Resources/Path/" + fName);
					BufferedImage img = ImageIO.read(url);
					Graphics2D g2d = (Graphics2D)g;
					g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
					g.drawImage(img, 0, 0, null);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Graphics2D g2d = 
	}

	/**
	 * 1 = start pin 2 = end pin 3 = transfer pin
	 * 
	 * @param pin
	 * @param x
	 * @param y
	 */
	public void setPinXY(int pin, int x, int y) {
		x = x - scaleFactor + 30;
		y = y - scaleFactor + 9;
		this.x[pin] = x;
		this.y[pin] = y;
	}

	/**
	 * 1 = start pin 2 = end pin 3 = transfer pin
	 * 
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
