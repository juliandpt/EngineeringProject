package Conicas;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ConicasVentana {

	private JFrame frame;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConicasVentana() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Funcion Conica");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}

}
