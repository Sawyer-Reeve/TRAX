package trax;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sup, World (Swing)");
        JLabel label = new JLabel("Look at me, I'm a heckin' GUI!");
        frame.add(label);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }
}
