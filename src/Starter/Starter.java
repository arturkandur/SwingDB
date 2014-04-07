package Starter;
import javax.swing.JFrame;

import Graphics.DBFrame;

/**
 * Main class which run program and create object JFrame for create UI. 
 * @author KArt
 *
 */
public class Starter {

	public static void main(String[] args) {
		JFrame frame = new DBFrame(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}

}
