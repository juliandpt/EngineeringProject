package Poblacion;

import java.awt.Dimension;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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


public class VentanaResultados {

	private JFrame frame;

	/**
	 * Launch the application.
	 */	

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public VentanaResultados() throws IOException {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	public void initialize() throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		double windowWidth = screenSize.getWidth()/3;
		double windowHeight = screenSize.getHeight()/2;
		
		frame = new JFrame("Tabla de Resultados");
		frame.setBounds(100, 100, (int) windowWidth, (int) windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblResults = new JLabel("Resultados");
		lblResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frame.getContentPane().add(lblResults, BorderLayout.NORTH);
		
		frame.getContentPane().add(new Operation().createTable(), BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnBk = new JButton("Atras");
		btnBk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new PoblacionVentana();
			}
		});
		panel.add(btnBk);
		
		JButton btnMainMenu = new JButton("Menu Principal");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MenuPrincipal();
			}
		});
		panel.add(btnMainMenu);
		
		JButton btnGetRanking = new JButton("Consultar Ranking");
		btnGetRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					new VentanaRanking();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnGetRanking);
		
	}
}
