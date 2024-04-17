package trax;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * this might not have needed to be its own class, but I did have a couple of other classes previously for separate panels
 * before I condensed them
 */
@SuppressWarnings({ "serial" })
public class MainGUIframe extends JFrame{
	
	
	MainGUIframe(){
	        JPanel panel = new JPanel(); //container panel, 
	        
	        this.setSize(1200, 800);
	        this.setVisible(true);
	        this.setLocationRelativeTo(null); // centers frame
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes program instead of hiding
	        this.setResizable(true);
	        this.setTitle("TRAX Ride-Assist");
	        this.setContentPane(panel);
	        
	        MainControlPanel main = new MainControlPanel();
	        panel.add(main);
	        
	        
	        
	        
	        ImageIcon background=new ImageIcon("src/Resources/TRAX MAP.png"); 
	        Image img=background.getImage();
	        Image temp=img.getScaledInstance(790,800,Image.SCALE_SMOOTH); //scales the image, can be set to SCALE_FAST if its slow, must be less than 800 wide as 800+ pushes the image offscreen 
	        background=new ImageIcon(temp);
	        JLabel backgroundlabel = new JLabel();
	        backgroundlabel.setSize(new Dimension(900,800));
	        panel.add(backgroundlabel);
	        backgroundlabel.setIcon(background);
	        this.revalidate();
	        
	        //used to get co-ords, when clicking on station, prints out station coordinates
	/*        panel.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	int x=e.getX();
	        	    int y=e.getY();
	        	    System.out.println(x+","+y);
	            }

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
	        });
	   */     
	        
	}
	//attempt to paint a pin
/*	public void paintPin(Station s) throws IOException {
		File Pinimage = new File("src/Resources/PinImage.png");
		ImageIcon pimage = new ImageIcon(ImageIO.read(Pinimage));
		int x = s.getXcoord();
		int y = s.getYcoord();
		JLabel pinLabel = new JLabel(pimage);
		pinLabel.setBounds(x, y, 50, 40);
		this.add(pinLabel);
		this.revalidate();
		
		
	}*/
}
