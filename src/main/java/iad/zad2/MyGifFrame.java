package iad.zad2;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyGifFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
    JLabel imageLabel = new JLabel();

    public MyGifFrame() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            contentPane = (JPanel) getContentPane();
            setSize(new Dimension(900, 900));
            setTitle("Wykres");
            // add the image label
            ImageIcon ii = new ImageIcon("out.gif");
            getContentPane().setLayout(null);
            
            JPanel panel = new JPanel();
            panel.setBounds(10, 10, 800, 800);
            getContentPane().add(panel);
            panel.add(imageLabel);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setIcon(ii);
            // show it
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}