package trax;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Sup, World (Swing)");
        JLabel label = new JLabel("Look at me, I'm a heckin' GUI!");
        
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // centers frame
        

        
        ImageIcon background=new ImageIcon("src/Resources/TRAX MAP.png");
        Image img=background.getImage();
        Image temp=img.getScaledInstance(800,800,Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        JLabel thumb = new JLabel();
        frame.add(thumb);
        thumb.setIcon(background);
        
        
        
        
     
        
    }
}
