package trax;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * this might not have needed to be its own class, but I did have a couple of other classes previously for separate panels
 * before I condensed them
 */
public class MainGUIframe extends JFrame{
	
	MainGUIframe(){
	       //JLabel label = new JLabel("Look at me, I'm a heckin' GUI!");
	        JPanel panel = new JPanel(); //container panel, 
	        
	        this.setSize(1200, 800);
	        this.setVisible(true);
	        this.setLocationRelativeTo(null); // centers frame
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes program instead of hiding
	        this.setResizable(false);
	        this.setTitle("Sup, World (Swing)");
	        this.setContentPane(panel);
	        panel.setLayout(new BorderLayout());
	        
	        MainControlPanel main = new MainControlPanel();
	        panel.add(main,BorderLayout.WEST);
	        
	        //TODO: Better Map Image?
	        ImageIcon background=new ImageIcon("src/Resources/TRAX MAP.png"); // can probably use a better image for this, this is just what I made up real quick
	        Image img=background.getImage();
	        Image temp=img.getScaledInstance(700,800,Image.SCALE_SMOOTH); //scales the image, can be set to SCALE_FAST if its slow
	        background=new ImageIcon(temp);
	        JLabel backgroundlabel = new JLabel();
	        backgroundlabel.setHorizontalAlignment(JLabel.CENTER); //these two center the image
	        backgroundlabel.setVerticalAlignment(JLabel.CENTER);
	        panel.add(backgroundlabel);
	        backgroundlabel.setIcon(background);
	        
	        
	        this.revalidate();
	}
}
