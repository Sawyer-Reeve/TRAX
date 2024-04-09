package trax;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class backgroundPanel extends Panel{
	
	private static final long serialVersionUID = 3968129263909342412L;
	// The Image to store the background image in.
	 private Image backgroundImage;

	  // Some code to initialize the background image.
	  // Here, we use the constructor to load the image. This
	  // can vary depending on the use case of the panel.
	  public backgroundPanel(String fileName) throws IOException {
	    backgroundImage = ImageIO.read(new File(fileName));
	  }

	  public void paintComponent(Graphics g) {
	    super.paintComponents(g);

	    // Draw the background image.
	    g.drawImage(backgroundImage, 0, 0, this);
	  }
}