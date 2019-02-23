package Poblacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import MenuPrincipal.MenuPrincipal;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRanking {

	private JFrame frame;
	JPanel panelCenter = new JPanel();
	JLabel[] labels;
	
	/**
	 * Create the application.
	 * @throws FileNotFoundException 
	 */
	public VentanaRanking() throws FileNotFoundException {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws FileNotFoundException 
	 */
	private void initialize() throws FileNotFoundException {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
		double windowWidth = screenSize.getWidth()/3;
		double windowHeight = screenSize.getHeight()/2;
			
		frame = new JFrame("Ranking");
	    frame.setBounds(100, 100, (int) windowWidth, (int) windowHeight);
	    frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("Ranking");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frame.getContentPane().add(lblTitle, BorderLayout.NORTH);
		
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(10, 0, 0, 0));
		
		JPanel panelSouth = new JPanel();
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnBack = new JButton("Volver a Simulador");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new PoblacionVentana();
			}
		});
		panelSouth.add(btnBack);
		
		JButton btnMenu = new JButton("Menu Principal");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MenuPrincipal();
			}
		});
		panelSouth.add(btnMenu);
		
		getLabelsRanks();
		addLabels();
		
		
	}
	
	private void getLabelsRanks() throws FileNotFoundException {
		File rankFile = new File("Ranking.txt");
		Scanner reader = new Scanner(rankFile);
		labels = new JLabel[10];
		int counter = 0;
		
		while(reader.hasNextLine()) {
			labels[counter] = new JLabel(reader.nextLine());
			counter++;
		}
		
		reader.close();
		
	}
	
	private void addLabels() {
		for (JLabel label : labels) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Tahoma", Font.PLAIN, 18));
			panelCenter.add(label, BorderLayout.CENTER);
		}
	}
}